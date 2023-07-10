package com.ufc;

import com.ufc.biblioteca.BibliotecaCentral;
import com.ufc.biblioteca.Emprestimo;
import com.ufc.biblioteca.RepositorioEmprestimo;
import com.ufc.gui.MinhaBibliotecaGUI;
import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.RepositorioLivro;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.IRepositorioUsuario;
import com.ufc.usuario.repositorio.RepositorioUsuario;
import org.junit.Assert;


class Main {
    public static void main(String[] args) {
        try {
            new MinhaBibliotecaGUI();
            //testaSerializacao();
            //testaDesserializacao();
            /* testaEmprestimo(); */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testaSerializacao() throws Exception {
        IRepositorioUsuario repositorioUsuario = new RepositorioUsuario();
        UsuarioAbstrato usuario1 = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "1234567890", "85985386441", "Engenharia");
        UsuarioAbstrato usuario2 = new Funcionario(" funcionario teste", "funcionario@teste.com", "147852", "987654321", "123456789","DETI");

        //repositorioUsuario.remover("1234567890");
        //repositorioUsuario.remover("987654321");
        repositorioUsuario.cadastrar(usuario1);
        repositorioUsuario.cadastrar(usuario2);

        RepositorioLivro livroRepositorio = new RepositorioLivro();
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "123456789", "Editora Teste", 2023, 5);

        //livroRepositorio.remover("123456789");
        livroRepositorio.cadastrar(livro);
    }

    public static void testaDesserializacao() throws Exception {
        IRepositorioUsuario repositorioUsuario = new RepositorioUsuario();
        RepositorioLivro livroRepositorio = new RepositorioLivro();

        UsuarioAbstrato usuarioAbstrato = repositorioUsuario.buscar("1234567890");
        Assert.assertEquals(" aluno Teste", usuarioAbstrato.getNome());
        System.out.println("Usuario: " + usuarioAbstrato.getTipo() + usuarioAbstrato.getNome() + usuarioAbstrato.getMatricula());

        Livro livro = livroRepositorio.buscarLivroPorISBN("123456789");
        System.out.println("Livro: " + livro.getTitulo() + livro.getISBN());
        Assert.assertEquals("Editora Teste", livro.getEditora());


    }

    public static void testaEmprestimo() throws Exception {
        IRepositorioUsuario repositorioUsuario = new RepositorioUsuario();
        RepositorioLivro livroRepositorio = new RepositorioLivro();
        RepositorioEmprestimo repositorioEmprestimo = new RepositorioEmprestimo();
        BibliotecaCentral biblioteca = new BibliotecaCentral(repositorioUsuario, livroRepositorio, repositorioEmprestimo);

        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "123456789", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "123456", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        boolean resultado = biblioteca.EmprestarLivro(livro, usuario);
        Assert.assertTrue(resultado);
        Assert.assertEquals(4, livro.getQuantidadeDisponivel());
        System.out.println("lala "+new Emprestimo(livro, usuario));
        repositorioEmprestimo.listar();
        Assert.assertTrue(repositorioEmprestimo.listar().contains(new Emprestimo(livro, usuario)));
    }

}
