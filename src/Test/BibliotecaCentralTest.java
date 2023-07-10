package Test;

import com.ufc.biblioteca.BibliotecaCentral;
import com.ufc.biblioteca.Emprestimo;
import com.ufc.biblioteca.RepositorioEmprestimo;
import com.ufc.biblioteca.excecao.CIException;
import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.RepositorioLivro;
import com.ufc.livro.repositorio.excecao.LJCException;
import com.ufc.livro.repositorio.excecao.LNCException;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.IRepositorioUsuario;
import com.ufc.usuario.repositorio.RepositorioUsuario;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BibliotecaCentralTest {
    private BibliotecaCentral biblioteca;
    private IRepositorioUsuario repositorioUsuario;
    private RepositorioLivro livroRepositorio;
    private RepositorioEmprestimo repositorioEmprestimo;

    @Before
    public void setUp() {
        repositorioUsuario = new RepositorioUsuario();
        livroRepositorio = new RepositorioLivro();
        repositorioEmprestimo = new RepositorioEmprestimo();
        biblioteca = new BibliotecaCentral(repositorioUsuario, livroRepositorio, repositorioEmprestimo);
    }

    @Test
    public void testLogin_CredenciaisCorretas() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "123", "85985386441", "Engenharia");
        repositorioUsuario.cadastrar(usuario);
        
        try {
            boolean resultado = biblioteca.login("123", "123456");
            Assert.assertTrue(resultado);
        } catch (CIException e) {
            Assert.fail("Não deveria lançar exceção");
        }
    }

    @Test
    public void testLogin_MatriculaIncorreta() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "1234", "85985386441", "Engenharia");
        repositorioUsuario.cadastrar(usuario);
        try {
            boolean resultado = biblioteca.login("456", "123456");
            Assert.fail("Deveria lançar exceção");
        } catch (CIException e) {
            Assert.assertEquals("Credenciais inválidas. Por favor, verifique sua matricula e senha.", e.getMessage());
        }
    }

    @Test
    public void testLogin_SenhaIncorreta() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "12345", "85985386441", "Engenharia");
        repositorioUsuario.cadastrar(usuario);
        
        try {
            boolean resultado = biblioteca.login("12345", "654321");
            Assert.fail("Deveria lançar exceção");
        } catch (CIException e) {
            Assert.assertEquals("Credenciais inválidas. Por favor, verifique sua matricula e senha.", e.getMessage());
        }
    }

    @Test
    public void testAdicionarLivro() throws Exception{
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "123", "Editora Teste", 2023, 5);
        
        try {
            biblioteca.adicionarLivro(livro);
            Assert.assertEquals(livro, livroRepositorio.buscarLivroPorISBN("123"));
        } catch (LJCException e) {
            Assert.fail("Não deveria lançar exceção");
        }
    }

    @Test
    public void testRemoverLivro() throws Exception{
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "1234", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        
        try {
            biblioteca.removerLivro("1234");
            Assert.assertNull(livroRepositorio.buscarLivroPorISBN("1234"));
        } catch (LNCException e) {
            Assert.fail("Não deveria lançar exceção");
        }
    }

    @Test
    public void testAdicionarUsuario() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "123456", "85985386441", "Engenharia");

        try {
            biblioteca.adicionarUsuario(usuario);
            Assert.assertTrue(repositorioUsuario.listar().contains(usuario));
        } catch (UJCException e) {
            Assert.fail("Não deveria lançar exceção");
        }
    }

    @Test
    public void testRemoverUsuario() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "1234567", "85985386441", "Engenharia");
        repositorioUsuario.cadastrar(usuario);
        try {
            biblioteca.removerUsuario("1234567");
            Assert.assertFalse(repositorioUsuario.listar().contains(usuario));
        } catch (UNCException e) {
            Assert.fail("Não deveria lançar exceção");
        }
    }
    
    @Test
    public void testEmprestarLivro() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "12345678", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "12345", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        boolean resultado = biblioteca.EmprestarLivro(livro, usuario);
        Assert.assertTrue(resultado);
        Assert.assertEquals(4, livro.getQuantidadeDisponivel());
        //Assert.assertTrue(repositorioEmprestimo.listar().contains(new Emprestimo(livro, usuario)));
    }

    @Test
    public void testEmprestarLivro_LivroIndisponivel() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "123456789", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "123456", "Editora Teste", 2023, 0);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        boolean resultado = biblioteca.EmprestarLivro(livro, usuario);
        Assert.assertFalse(resultado);
        Assert.assertEquals(0, livro.getQuantidadeDisponivel());
        Assert.assertFalse(repositorioEmprestimo.listar().contains(new Emprestimo(livro, usuario)));
    }

    @Test
    public void testEmprestarLivro_UsuarioNaoRegistrado() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "1234567890", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "1234567", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);

        boolean resultado = biblioteca.EmprestarLivro(livro, usuario);
        Assert.assertFalse(resultado);
        Assert.assertEquals(5, livro.getQuantidadeDisponivel());
        Assert.assertFalse(repositorioEmprestimo.listar().contains(new Emprestimo(livro, usuario)));
    }

    @Test
    public void testDevolverLivro_LivroNaoEmprestado() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "12345678901", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "12345678", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        boolean resultado = biblioteca.devolverLivro(livro, usuario);
        Assert.assertFalse(resultado);
    }

    @Test
    public void testDevolverLivro_LivroEmprestado() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "123456789012", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "123456789", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        biblioteca.EmprestarLivro(livro, usuario);
        boolean resultado = biblioteca.devolverLivro(livro, usuario);
        Assert.assertEquals(5, livro.getQuantidadeDisponivel());
        Assert.assertTrue(resultado);
    }

    @Test
    public void testConsultarMulta_SemMulta() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "1234567890123", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "1234567890", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        double multa = biblioteca.ConsultarMulta(usuario);
        Assert.assertEquals(0.0, multa, 0.01);
    }

    @Test
    public void testConsultarMulta_ComMulta_Aluno() throws Exception{
        UsuarioAbstrato usuario = new Aluno("aluno Teste", "aluno@teste.com", "123456", "12345678901234", "85985386441", "Engenharia");
        Livro livro = new Livro(" Livro Teste", "Autor Teste", "12345678901", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        biblioteca.EmprestarLivro(livro, usuario);

        Emprestimo emprestimo = repositorioEmprestimo.listar().get(0);
        //emprestimo.setDataDevolucao(LocalDate.now().minusDays(5)); // Definindo uma data de devolução passada (5 dias de atraso)

        double multaEsperada = 5 * 0.2; // 5 dias de atraso x valor da multa diária aluno
        double multa = biblioteca.ConsultarMulta(usuario);
        System.out.println(ChronoUnit.DAYS.between(LocalDate.now().minusDays(5), LocalDate.now()));

        Assert.assertEquals(multaEsperada, multa, 0.01);
    }

    @Test
    public void testConsultarMulta_ComMulta_Funcionario() throws Exception{
        UsuarioAbstrato usuario = new Funcionario("Funcionario Teste", "funcionario@teste.com", "123456", "12345678901234852", "85985386441", "Deti");
        Livro livro = new Livro("Livro Teste", "Autor Teste", "12345678901026", "Editora Teste", 2023, 5);
        livroRepositorio.cadastrar(livro);
        repositorioUsuario.cadastrar(usuario);

        biblioteca.EmprestarLivro(livro, usuario);

        Emprestimo emprestimo = repositorioEmprestimo.listar().get(0);
        //emprestimo.setDataDevolucao(LocalDate.now().minusDays(5)); // Definindo uma data de devolução passada (5 dias de atraso)

        double multaEsperada = 5 * 0.5; // 5 dias de atraso x valor da multa diária aluno
        double multa = biblioteca.ConsultarMulta(usuario);
        System.out.println(ChronoUnit.DAYS.between(LocalDate.now().minusDays(5), LocalDate.now()));

        Assert.assertEquals(multaEsperada, multa, 0.01);
    }

    @Test
    public void testGetEmprestimosPorUsuario_SemEmprestimos() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "123456789012345", "85985386441", "Engenharia");
        repositorioUsuario.cadastrar(usuario);

        List<Emprestimo> emprestimos = repositorioEmprestimo.getEmprestimosPorUsuario(usuario);
        Assert.assertTrue(emprestimos.isEmpty());
    }

    @Test
    public void testGetEmprestimosPorUsuario_ComEmprestimos() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "1234567890123456", "85985386441", "Engenharia");
        Livro livro1 = new Livro("Livro1 Teste", "Autor teste", "123456789012", "Editora X", 2023, 5);
        Livro livro2 = new Livro("Livro2 Teste", "Autor teste", "0987654321", "Editora Y", 2023, 3);
        repositorioUsuario.cadastrar(usuario);
        livroRepositorio.cadastrar(livro1);
        livroRepositorio.cadastrar(livro2);

        Emprestimo emprestimo1 = new Emprestimo(livro1, usuario);
        Emprestimo emprestimo2 = new Emprestimo(livro2, usuario);

        repositorioEmprestimo.cadastrarEmprestimo(emprestimo1);
        repositorioEmprestimo.cadastrarEmprestimo(emprestimo2);

        List<Emprestimo> emprestimos = repositorioEmprestimo.getEmprestimosPorUsuario(usuario);
        Assert.assertEquals(2, emprestimos.size());
        Assert.assertTrue(emprestimos.contains(emprestimo1));
        Assert.assertTrue(emprestimos.contains(emprestimo2));
    }

    @Test
    public void testGetEmprestimoPorUsuarioELivro_EmpréstimoExistente() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "12345678901234568654", "85985386441", "Engenharia");
        Livro livro1 = new Livro("Livro1 Teste", "Autor teste", "123456789012784", "Editora X", 2023, 5);
        Livro livro2 = new Livro("Livro2 Teste", "Autor teste", "09876543216224", "Editora Y", 2023, 3);
        repositorioUsuario.cadastrar(usuario);
        livroRepositorio.cadastrar(livro1);
        livroRepositorio.cadastrar(livro2);

        Emprestimo emprestimo = new Emprestimo(livro1, usuario);
        repositorioEmprestimo.cadastrarEmprestimo(emprestimo);

        Emprestimo resultado = repositorioEmprestimo.getEmprestimoPorUsuarioELivro(usuario, livro1);
        Assert.assertNotNull(resultado);
        Assert.assertEquals(emprestimo, resultado);
    }

    @Test
    public void testGetEmprestimoPorUsuarioELivro_EmpréstimoInexistente() throws Exception{
        UsuarioAbstrato usuario = new Aluno(" aluno Teste", "aluno@teste.com", "123456", "5561564", "85985386441", "Engenharia");
        Livro livro1 = new Livro("Livro1 Teste", "Autor teste", "748", "Editora X", 2023, 5);
        repositorioUsuario.cadastrar(usuario);
        livroRepositorio.cadastrar(livro1);

        Emprestimo resultado = repositorioEmprestimo.getEmprestimoPorUsuarioELivro(usuario, livro1);
        Assert.assertNull(resultado);
    }
}