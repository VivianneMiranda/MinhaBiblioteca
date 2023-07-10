package com.ufc.biblioteca;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import com.ufc.biblioteca.excecao.CIException;
import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.LivroRepositorio;
import com.ufc.livro.repositorio.excecao.LJCException;
import com.ufc.livro.repositorio.excecao.LNCException;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.IRepositorioUsuario;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;

public class BibliotecaCentral implements IBiblioteca {
    private Vector<Livro> livrosDisponiveis;
    private Vector<UsuarioAbstrato> usuariosRegistrados;
    private IRepositorioUsuario iRepositorioUsuario;
    private LivroRepositorio livroRepositorio;
    private UsuarioAbstrato usuarioLogado;
    private List<Emprestimo> emprestimos;

    public BibliotecaCentral(Vector<Livro> livrosDisponiveis, Vector<UsuarioAbstrato> usuariosRegistrados,
            IRepositorioUsuario iRepositorioUsuario, LivroRepositorio livroRepositorio, UsuarioAbstrato usuarioLogado,
            List<Emprestimo> emprestimos) {
        this.livrosDisponiveis = livrosDisponiveis;
        this.usuariosRegistrados = usuariosRegistrados;
        this.iRepositorioUsuario = iRepositorioUsuario;
        this.livroRepositorio = livroRepositorio;
        this.usuarioLogado = null;
        this.emprestimos = emprestimos;
    }

    public boolean login(String matricula, String senha) throws CIException {
        for (UsuarioAbstrato usuario : usuariosRegistrados) {
            if (usuario.getMatricula().equals(matricula) && usuario.verificarSenha(senha)) {
                usuarioLogado = usuario;
                return true;
            }
        }
        throw new CIException("Credenciais inválidas. Por favor, verifique sua matricula e senha.");
    }

    public void logout() {
        usuarioLogado = null;
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado != null;
    }

    public void adicionarLivro(Livro livro) throws LJCException {
        if (isUsuarioLogado() && usuarioLogado.getTipo().equals("funcionario")) {
            livroRepositorio.cadastrar(livro);
        }
    }

    public void removerLivro(String ISBN) throws LNCException {
        if (isUsuarioLogado() && usuarioLogado.getTipo().equals("funcionario")) {
            livroRepositorio.remover(ISBN);
        }
    }

    public void adicionarFuncionario(Funcionario usuario) throws UJCException {
        iRepositorioUsuario.cadastrar(usuario);
    }

    public void adicionarAluno(Aluno usuario) throws UJCException {
        iRepositorioUsuario.cadastrar(usuario);
    }

    public void removerUsuario(String matricula) throws UNCException {
        iRepositorioUsuario.remover(matricula);
    }

    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    public boolean EmprestarLivro(Livro livro, UsuarioAbstrato usuario){
        if (!livrosDisponiveis.contains(livro)) {
            System.out.println("O livro não está disponível para empréstimo.");
            return false;
        }

        if (!usuariosRegistrados.contains(usuario)) {
            System.out.println("O usuário não está registrado na biblioteca.");
            return false;
        }

        if (!livro.podeSerEmprestado()) {
            System.out.println("Não é possível emprestar o livro. Quantidade disponível é zero.");
            return false;
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        emprestimos.add(emprestimo);
        livro.efetuarEmprestimo();
        
        return true;
    }

    public boolean devolverLivro(Livro livro, UsuarioAbstrato usuario) {
        Emprestimo emprestimoEncontrado = null;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getUsuario().equals(usuario)) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if (emprestimoEncontrado == null) {
            System.out.println("O livro não está emprestado por esse usuário.");
            return false;
        }

        emprestimoEncontrado.setDataDevolucao(LocalDate.now());
        livro.efetuarDevolucao();
        emprestimos.remove(emprestimoEncontrado);

        return true;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public double ConsultarMulta(UsuarioAbstrato usuario) {
        double multaTotal = 0;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario)) {
                double multa = emprestimo.calcularMulta();
                multaTotal += multa;
            }
        }

        return multaTotal;
    }

}
