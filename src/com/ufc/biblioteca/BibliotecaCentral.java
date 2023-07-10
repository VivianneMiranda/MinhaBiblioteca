package com.ufc.biblioteca;

import com.ufc.biblioteca.excecao.CIException;
import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.RepositorioLivro;
import com.ufc.livro.repositorio.excecao.LJCException;
import com.ufc.livro.repositorio.excecao.LNCException;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.IRepositorioUsuario;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;

import java.time.LocalDate;

public class BibliotecaCentral implements IBiblioteca {
    private IRepositorioUsuario repositorioUsuario;
    private RepositorioLivro livroRepositorio;
    private RepositorioEmprestimo repositorioEmpretismo;

    public BibliotecaCentral(IRepositorioUsuario repositorioUsuario, RepositorioLivro livroRepositorio, RepositorioEmprestimo repositorioEmpretismo) {
        this.repositorioUsuario = repositorioUsuario;
        this.livroRepositorio = livroRepositorio;
        this.repositorioEmpretismo = repositorioEmpretismo;
    }

    public boolean login(String matricula, String senha) throws CIException {
        for (UsuarioAbstrato usuario : this.repositorioUsuario.listar()) {
            if (usuario.getMatricula().equals(matricula) && usuario.verificarSenha(senha)) {
                return true;
            }
        }
        throw new CIException("Credenciais inválidas. Por favor, verifique sua matricula e senha.");
    }

    public void adicionarLivro(Livro livro) throws LJCException {
            this.livroRepositorio.cadastrar(livro);
    }

    public void removerLivro(String ISBN) throws LNCException {
            this.livroRepositorio.remover(ISBN);
    }

    public void adicionarUsuario(UsuarioAbstrato usuario) throws UJCException {
        this.repositorioUsuario.cadastrar(usuario);
    }

    public void removerUsuario(String matricula) throws UNCException {
        this.repositorioUsuario.remover(matricula);
    }

    public boolean EmprestarLivro(Livro livro, UsuarioAbstrato usuario){
        if (!this.livroRepositorio.listar().contains(livro)) {
            System.out.println("O livro não está disponível para empréstimo.");
            return false;
        }

        if (!this.repositorioUsuario.listar().contains(usuario)) {
            System.out.println("O usuário não está registrado na biblioteca.");
            return false;
        }

        if (!livro.podeSerEmprestado()) {
            System.out.println("Não é possível emprestar o livro. Quantidade disponível é zero.");
            return false;
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        this.repositorioEmpretismo.cadastrarEmprestimo(emprestimo);
        livro.efetuarEmprestimo();
        
        return true;
    }

    public boolean devolverLivro(Livro livro, UsuarioAbstrato usuario) {
        Emprestimo emprestimoEncontrado = null;
        for (Emprestimo emprestimo : this.repositorioEmpretismo.listar()) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getUsuario().equals(usuario)) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if (emprestimoEncontrado == null) {
            System.out.println("O livro não está emprestado para esse usuário.");
            return false;
        }

        emprestimoEncontrado.setDataDevolucao(LocalDate.now());
        livro.efetuarDevolucao();
        this.repositorioEmpretismo.removerEmprestimo(emprestimoEncontrado);

        return true;
    }

    public double ConsultarMulta(UsuarioAbstrato usuario) {
        double multaTotal = 0;

        for (Emprestimo emprestimo : this.repositorioEmpretismo.listar()) {
            if (emprestimo.getUsuario().equals(usuario)) {
                double multa = emprestimo.calcularMulta();
                multaTotal += multa;
            }
        }

        return multaTotal;
    }

}
