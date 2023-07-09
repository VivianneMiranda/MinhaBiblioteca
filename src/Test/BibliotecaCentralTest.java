package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ufc.biblioteca.BibliotecaCentral;
import com.ufc.biblioteca.Emprestimo;
import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.LivroRepositorio;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.IRepositorioUsuario;
import com.ufc.usuario.repositorio.RepositorioUsuario;

public class BibliotecaCentralTest {
    private BibliotecaCentral biblioteca;

    @Before
    public void setUp() {
        Vector<Livro> livrosDisponiveis = new Vector<>();
        Vector<UsuarioAbstrato> usuariosRegistrados = new Vector<>();
        IRepositorioUsuario iRepositorioUsuario = new RepositorioUsuario();
        LivroRepositorio livroRepositorio = new LivroRepositorio();
        UsuarioAbstrato usuarioLogado = null;
        List<Emprestimo> emprestimos = new ArrayList<>();

        biblioteca = new BibliotecaCentral(livrosDisponiveis, usuariosRegistrados, iRepositorioUsuario, livroRepositorio, usuarioLogado, emprestimos);
    }

    @Test
    public void testEmprestarLivro() {
        Livro livro = new Livro("Livro Teste", "Autor Teste", "123456789", "Editora Teste", 2023, 5);
        Aluno aluno = new Aluno("aluno Teste", "aluno@teste.com", "123456", "123456789", "85985386441", "Engenharia");
        
        boolean resultado = biblioteca.EmprestarLivro(livro, aluno);
        
        Assert.assertTrue(resultado);
        Assert.assertEquals(1, biblioteca.getEmprestimos().size());
        Assert.assertEquals(4, livro.getQuantidadeDisponivel());
    }

    @Test
    public void testDevolverLivro() {
        Livro livro = new Livro("Livro Teste", "Autor Teste", "123456789", "Editora Teste", 2023, 5);
        Aluno aluno = new Aluno( "aluno Teste", "aluno@teste.com", "123456", "123456789", "85985386441", "Engenharia");
        Emprestimo emprestimo = new Emprestimo(livro, aluno);
        biblioteca.getEmprestimos().add(emprestimo);
        
        boolean resultado = biblioteca.devolverLivro(livro, aluno);
        
        Assert.assertTrue(resultado);
        Assert.assertEquals(0, biblioteca.getEmprestimos().size());
        Assert.assertEquals(5, livro.getQuantidadeDisponivel());
    }
}
