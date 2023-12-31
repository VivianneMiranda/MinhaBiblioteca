package com.ufc.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;

import com.ufc.biblioteca.BibliotecaCentral;
import com.ufc.biblioteca.Emprestimo;
import com.ufc.biblioteca.RepositorioEmprestimo;
import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.RepositorioLivro;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.RepositorioUsuario;
import com.ufc.usuario.repositorio.excecao.UJCException;

import java.awt.*;




public class MinhaBibliotecaGUI extends JFrame {

Color fundobotao = new Color(120,231,255);
Color fundojanela = new Color(224,255,255);
Color letra = new Color (0,0,0);
  
private RepositorioUsuario repoUsuarios;
private RepositorioLivro repoLivros; 
private RepositorioEmprestimo repoEmprestimos;
private BibliotecaCentral bCentral;
private String matriculaUsuario;

  public MinhaBibliotecaGUI() {
    
    repoUsuarios = new RepositorioUsuario();
    repoLivros = new RepositorioLivro();
    repoEmprestimos = new RepositorioEmprestimo();
    bCentral = new BibliotecaCentral(repoUsuarios, repoLivros, repoEmprestimos);

    JFrame janelaInicial = new JFrame("Minha Biblioteca");
    janelaInicial.setSize(500, 500);
    janelaInicial.setLayout(new BorderLayout());
    janelaInicial.getContentPane().setBackground(fundojanela);
    janelaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    
    int w = janelaInicial.getWidth();
    int h = janelaInicial.getHeight();
    
    JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(168, 108, 209)); // Cor lilás
                g.fillRect(100, 100, 200, 100); // Desenha o retângulo
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 20)); // Configura a fonte
                g.drawString("Minha Biblioteca", 125, 160); // Desenha a frase
            }
        };

        // Adiciona o painel à janela
       


   
    JLabel rotulo = new JLabel("Biblioteca Central");
               rotulo.setFont(new Font("Arial", Font.BOLD, 40));
               rotulo.setBounds(70, h/4, 400, 100);
               rotulo.setForeground(letra);
               
    

    JButton entrar = new JButton("Entrar");
    entrar.setBounds(180, 220, 120, 40);
    entrar.setFont(new Font("Arial", Font.BOLD, 20));
    entrar.setForeground(letra);
    entrar.setBorder(null);
    entrar.setBackground(fundobotao);
    entrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janelaInicial.dispose();
      abrirjanela2();
      

      }
    });

   
    janelaInicial.getContentPane().setLayout(null);
    janelaInicial.add(entrar);
    janelaInicial.add(rotulo);

    //janelaInicial.add(panel);
    //panel.add(entrar,BorderLayout.CENTER);

    janelaInicial.setVisible(true);
  }







  
  // acervo e cadastre-se
  public void abrirjanela2() {

    JFrame janela2 = new JFrame("janela 2");
    janela2.setSize(500, 500);
    janela2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela2.getContentPane().setBackground(fundojanela);

    JButton opicaoacervo = new JButton("Acervo");
    opicaoacervo.setBounds(80, 200, 150, 40);
    opicaoacervo.setForeground(letra);
    opicaoacervo.setBackground(fundobotao);
    opicaoacervo.setFont(new Font("Arial", Font.BOLD, 20));
    opicaoacervo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela2.dispose();
        abrirjanela8();
      }
    });

    JButton opicaocadastre = new JButton("Cadastre-se");
    opicaocadastre.setBounds(250, 200, 150, 40);
    opicaocadastre.setForeground(letra);
    opicaocadastre.setBackground(fundobotao);
    opicaocadastre.setFont(new Font("Arial", Font.BOLD, 20));
    opicaocadastre.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela2.dispose();
        abrirjanela3();
      }
    });

    janela2.getContentPane().setLayout(null);
    janela2.getContentPane().add(opicaoacervo);
    janela2.getContentPane().add(opicaocadastre);

    janela2.setVisible(true);
  }

  // aluno ou funcionario
  public void abrirjanela3() {
    JFrame janela3 = new JFrame("janela 3");
    janela3.setSize(500,500);
    janela3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela3.getContentPane().setBackground(fundojanela);

    JLabel comando = new JLabel("Você é Aluno ou Funcionario ?");
    comando.setFont(new Font("Arial", Font.BOLD, 30));
    comando.setBounds(30, 80, 500, 100);
    comando.setForeground(letra);

    janela3.add(comando);

    JButton opcaoaluno = new JButton("Aluno");
    opcaoaluno.setBounds(80, 200, 150, 40);
    opcaoaluno.setForeground(letra);
    opcaoaluno.setBackground(fundobotao);
    opcaoaluno.setFont(new Font("Arial", Font.BOLD, 20));
    opcaoaluno.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela3.dispose();
        abrirjanela4();
      }
    });

    JButton opcaofuncionario = new JButton("Funcionario");
    opcaofuncionario.setBounds(250, 200, 150, 40);
    opcaofuncionario.setForeground(letra);
    opcaofuncionario.setBackground(fundobotao);
    opcaofuncionario.setFont(new Font("Arial", Font.BOLD, 20));
    opcaofuncionario.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela3.dispose();
        abrirjanela6();
      }
    });

    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela3.dispose();
      abrirjanela2();
        

      }
    });
    janela3.getContentPane().add(voltar);
    janela3.getContentPane().setLayout(null);
    janela3.getContentPane().add(opcaoaluno);
    janela3.getContentPane().add(opcaofuncionario);

    janela3.setVisible(true);
  }







  

  public void abrirjanela4() {
    JFrame janela4 = new JFrame("Formulário de Cadastro");
    janela4.setSize(500,500);
    janela4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela4.getContentPane().setBackground(fundojanela);
    janela4.setLayout(null);

    JLabel comando = new JLabel("Preencha o formulário abaixo:");
    comando.setFont(new Font("Arial", Font.BOLD, 25));
    comando.setBounds(50, -10, 400, 100);

    janela4.add(comando);

    JLabel rotuloIdentidade = new JLabel("Identidade:");
    rotuloIdentidade.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloIdentidade.setBounds(50, 70, 200, 20);
    JLabel rotuloNome = new JLabel("Nome:");
    rotuloNome.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloNome.setBounds(50, 110, 100, 20);
    JLabel rotuloMatricula = new JLabel("Matrícula:");
    rotuloMatricula.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloMatricula.setBounds(50, 150, 100, 20);
    JLabel rotuloSenha = new JLabel("Senha:");
    rotuloSenha.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloSenha.setBounds(50, 190, 100, 20);
    JLabel rotuloEmail = new JLabel("E-mail:");
    rotuloEmail.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloEmail.setBounds(50, 230, 100, 20);
    JLabel rotuloTelefone = new JLabel("Telefone:");
    rotuloTelefone.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloTelefone.setBounds(50, 270, 100, 20);
    JLabel rotuloCurso = new JLabel("Curso:");
    rotuloCurso.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloCurso.setBounds(50, 310, 100, 20);

    JTextField campoIdentidade = new JTextField();
    campoIdentidade.setBounds(170, 70, 300, 30);
    JTextField campoNome = new JTextField();
    campoNome.setBounds(170, 110, 300, 30);
    JTextField campoMatricula = new JTextField();
    campoMatricula.setBounds(170, 150, 300, 30);
    JTextField campoSenha = new JTextField();
    campoSenha.setBounds(170, 190, 300, 30);
    JTextField campoEmail = new JTextField();
    campoEmail.setBounds(170, 230, 300, 30);
    JTextField campoTelefone = new JTextField();
    campoTelefone.setBounds(170, 270, 300, 30);
    JTextField campoCurso = new JTextField();
    campoCurso.setBounds(170, 310, 300, 30);

    JButton botaoEnviar = new JButton("Enviar");
    botaoEnviar.setBounds(340, 380, 120, 40);
    botaoEnviar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoEnviar.setForeground(letra);
    botaoEnviar.setBackground(fundobotao);
    botaoEnviar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se todos os campos estão preenchidos
        if (campoIdentidade.getText().isEmpty() || campoNome.getText().isEmpty() ||
            campoMatricula.getText().isEmpty() || campoSenha.getText().isEmpty() ||
            campoEmail.getText().isEmpty() || campoTelefone.getText().isEmpty() ||
            campoCurso.getText().isEmpty()) {
          JOptionPane.showMessageDialog(janela4, "Por favor, preencha todos os campos.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          try {
            UsuarioAbstrato aluno = new Aluno(campoNome.getText(), campoEmail.getText(), campoSenha.getText(), campoMatricula.getText(), campoTelefone.getText(), campoCurso.getText());
            repoUsuarios.cadastrar(aluno);
          janela4.dispose();
          abrirjanela5();
          } catch (UJCException error) {
              JOptionPane.showMessageDialog(janela4, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
          
        }
      }
    });

    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela4.dispose();
      abrirjanela3();
        

      }
    });
    janela4.getContentPane().add(voltar);
    janela4.add(rotuloIdentidade);
    janela4.add(rotuloNome);
    janela4.add(rotuloMatricula);
    janela4.add(rotuloSenha);
    janela4.add(rotuloEmail);
    janela4.add(rotuloTelefone);
    janela4.add(rotuloCurso);
    janela4.add(campoIdentidade);
    janela4.add(campoNome);
    janela4.add(campoMatricula);
    janela4.add(campoSenha);
    janela4.add(campoEmail);
    janela4.add(campoTelefone);
    janela4.add(campoCurso);
    janela4.add(botaoEnviar);

    janela4.setVisible(true);
  }





  
  public void abrirjanela5() {

    JFrame janela5 = new JFrame("");
    janela5.setSize(500,500);
    janela5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela5.getContentPane().setBackground(fundojanela);

    JLabel msgaluno = new JLabel("Cadastro realizado com sucesso!");
    msgaluno.setFont(new Font("Arial", Font.BOLD, 28));
    msgaluno.setBounds(25, 120, 500, 100);
    msgaluno.setForeground(letra);

    janela5.add(msgaluno);

    JButton botaoacervofuncionarios = new JButton("Clique aqui para o login");
    botaoacervofuncionarios.setBounds(20, 220, 450, 30);
    botaoacervofuncionarios.setForeground(letra);
    botaoacervofuncionarios.setBackground(fundobotao);
    botaoacervofuncionarios.setFont(new Font("Arial", Font.BOLD, 15));
    botaoacervofuncionarios.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        janela5.dispose();
        abrirjanela19();
      }
    });

    janela5.getContentPane().setLayout(null);
    janela5.getContentPane().add(botaoacervofuncionarios);

    janela5.setVisible(true);
  }








  
  public void abrirjanela6() {
    JFrame janela6 = new JFrame("Formulário de Cadastro");
    janela6.setSize(500,500);
    janela6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela6.setLayout(null);

    JLabel comando = new JLabel("Preencha o formulário abaixo:");
    comando.setFont(new Font("Arial", Font.BOLD, 25));
    comando.setBounds(50, -10, 400, 100);


    janela6.add(comando);

     JLabel rotuloIdentidade = new JLabel("Identidade:");
    rotuloIdentidade.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloIdentidade.setBounds(50, 70, 200, 20);
    JLabel rotuloNome = new JLabel("Nome:");
    rotuloNome.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloNome.setBounds(50, 110, 100, 20);
    JLabel rotuloMatricula = new JLabel("Matrícula:");
    rotuloMatricula.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloMatricula.setBounds(50, 150, 100, 20);
    JLabel rotuloSenha = new JLabel("Senha:");
    rotuloSenha.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloSenha.setBounds(50, 190, 100, 20);
    JLabel rotuloEmail = new JLabel("E-mail:");
    rotuloEmail.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloEmail.setBounds(50, 230, 100, 20);
    JLabel rotuloTelefone = new JLabel("Telefone:");
    rotuloTelefone.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloTelefone.setBounds(50, 270, 100, 20);
    JLabel rotuloDepartamento = new JLabel("Departamento");
    rotuloDepartamento.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloDepartamento.setBounds(50, 310, 100, 20);

    JTextField campoIdentidade = new JTextField();
    campoIdentidade.setBounds(170, 70, 300, 30);
    JTextField campoNome = new JTextField();
    campoNome.setBounds(170, 110, 300, 30);
    JTextField campoMatricula = new JTextField();
    campoMatricula.setBounds(170, 150, 300, 30);
    JTextField campoSenha = new JTextField();
    campoSenha.setBounds(170, 190, 300, 30);
    JTextField campoEmail = new JTextField();
    campoEmail.setBounds(170, 230, 300, 30);
    JTextField campoTelefone = new JTextField();
    campoTelefone.setBounds(170, 270, 300, 30);
    JTextField campoDepartamento = new JTextField();
    campoDepartamento.setBounds(170, 310, 300, 30);

    JButton botaoEnviar = new JButton("Enviar");
    botaoEnviar.setBounds(340, 380, 120, 40);
    botaoEnviar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoEnviar.setForeground(letra);
    botaoEnviar.setBackground(fundobotao);
    botaoEnviar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se todos os campos estão preenchidos
        if (campoIdentidade.getText().isEmpty() || campoNome.getText().isEmpty() ||
            campoMatricula.getText().isEmpty() || campoSenha.getText().isEmpty() ||
            campoEmail.getText().isEmpty() || campoTelefone.getText().isEmpty() ||
            campoDepartamento.getText().isEmpty()) {
          JOptionPane.showMessageDialog(janela6, "Por favor, preencha todos os campos.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          try {
            UsuarioAbstrato funcionario = new Funcionario(campoNome.getText(), campoEmail.getText(), campoSenha.getText(), campoMatricula.getText(), campoTelefone.getText(), campoDepartamento.getText());
            repoUsuarios.cadastrar(funcionario);
          janela6.dispose();
          abrirjanela7();
          } catch (UJCException error) {
              JOptionPane.showMessageDialog(janela6, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
          
        }
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela6.dispose();
      abrirjanela3();
        

      }
    });
    janela6.getContentPane().add(voltar);
    janela6.add(rotuloIdentidade);
    janela6.add(rotuloNome);
    janela6.add(rotuloMatricula);
    janela6.add(rotuloSenha);
    janela6.add(rotuloEmail);
    janela6.add(rotuloTelefone);
    janela6.add(rotuloDepartamento);
    janela6.add(campoIdentidade);
    janela6.add(campoNome);
    janela6.add(campoMatricula);
    janela6.add(campoSenha);
    janela6.add(campoEmail);
    janela6.add(campoTelefone);
    janela6.add(campoDepartamento);
    janela6.add(botaoEnviar);

    janela6.setVisible(true);
  }







  
  public void abrirjanela7() {

    JFrame janela7 = new JFrame("");
    janela7.setSize(500,500);
    janela7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela7.getContentPane().setBackground(fundojanela);


    JLabel msgfuncionario = new JLabel("Cadastro realizado com sucesso!");
    msgfuncionario.setFont(new Font("Arial", Font.BOLD, 28));
    msgfuncionario.setBounds(25, 120, 500, 100);
    msgfuncionario.setForeground(letra);

    janela7.add(msgfuncionario);

    JButton botaofuncionario = new JButton("Clique aqui para ser redirecionado ao login");
    botaofuncionario.setBounds(0, 220, 490, 30);
    botaofuncionario.setForeground(letra);
    botaofuncionario.setBackground(fundobotao);
    botaofuncionario.setFont(new Font("Arial", Font.BOLD, 15));
    botaofuncionario.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        janela7.dispose();
        abrirjanela9();
      }
    });

    janela7.getContentPane().setLayout(null);
    janela7.getContentPane().add(botaofuncionario);

    janela7.setVisible(true);
  }








  
  public void abrirjanela8() {
    JFrame janela8 = new JFrame("janela 8");
    janela8.setSize(500,500);
    janela8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela8.getContentPane().setBackground(fundojanela);

  


      JLabel comando = new JLabel("Você é Aluno ou Funcionario ?");
    comando.setFont(new Font("Arial", Font.BOLD, 30));
    comando.setBounds(30, 80, 500, 100);
    comando.setForeground(letra);

    janela8.add(comando);

    JButton opcaoaluno = new JButton("Aluno");
    opcaoaluno.setBounds(80, 200, 150, 40);
    opcaoaluno.setForeground(letra);
    opcaoaluno.setBackground(fundobotao);
    opcaoaluno.setFont(new Font("Arial", Font.BOLD, 20));
    opcaoaluno.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela8.dispose();
        abrirjanela19();
      }
    });

    JButton opcaofuncionario = new JButton("Funcionario");
    opcaofuncionario.setBounds(250, 200, 150, 40);
    opcaofuncionario.setForeground(letra);
    opcaofuncionario.setBackground(fundobotao);
    opcaofuncionario.setFont(new Font("Arial", Font.BOLD, 20));
    opcaofuncionario.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela8.dispose();
        abrirjanela9();
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela8.dispose();
      abrirjanela2();
        

      }
    });
    janela8.getContentPane().add(voltar);
    janela8.getContentPane().setLayout(null);
    janela8.getContentPane().add(opcaoaluno);
    janela8.getContentPane().add(opcaofuncionario);

    janela8.setVisible(true);
  }







  
  public void abrirjanela9() {
    JFrame janela9 = new JFrame("Login");
    janela9.setSize(500,500);
    janela9.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela9.setLayout(null);
    janela9.getContentPane().setBackground(fundojanela);

    JLabel rotuloMatricula = new JLabel("Matrícula:");
    rotuloMatricula.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloMatricula.setBounds(40, 140, 100, 20);

    JTextField campoMatricula = new JTextField();
    campoMatricula.setBounds(160, 140, 300, 30);

    JLabel rotuloSenha = new JLabel("Senha:");
    rotuloSenha.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloSenha.setBounds(40, 190, 100, 20);

    JPasswordField campoSenha = new JPasswordField();
    campoSenha.setBounds(160, 190, 300, 30);

    JButton botaoLogin = new JButton("Login");
    botaoLogin.setBounds(280, 320, 150, 40);
    botaoLogin.setForeground(letra);
    botaoLogin.setBackground(fundobotao);
    botaoLogin.setFont(new Font("Arial", Font.BOLD, 20));
    botaoLogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String matricula = campoMatricula.getText();
        char[] senhaChars = campoSenha.getPassword();
        String senha = new String(senhaChars);

        if (matricula.isEmpty() || senha.isEmpty()) {
          JOptionPane.showMessageDialog(janela9, "Por favor, preencha todos os campos.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          

          try{
            Boolean login = bCentral.login(matricula, senha);
            if(login) matriculaUsuario = matricula;
            String type = repoUsuarios.buscar(matricula).getTipo();
            
            if( !type.equals("funcionario")){
              JOptionPane.showMessageDialog(janela9, "Este usuário não exite!",
              "Erro", JOptionPane.ERROR_MESSAGE);
              return;
            }

            janela9.dispose();
          abrirjanela10();
          }catch(Exception error){
            JOptionPane.showMessageDialog(janela9, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
          
        }
      }
    });

    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela9.dispose();
      abrirjanela8();
        

      }
    });
    janela9.getContentPane().add(voltar);
    janela9.add(rotuloMatricula);
    janela9.add(campoMatricula);
    janela9.add(rotuloSenha);
    janela9.add(campoSenha);
    janela9.add(botaoLogin);

    janela9.setVisible(true);
  }







//acervo de funcionarios
  public void abrirjanela10() {
    JFrame janela10 = new JFrame("Acervo de funcionarios");
    janela10.setSize(500,500);
    janela10.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela10.getContentPane().setBackground(fundojanela);


    JLabel comando = new JLabel("Acervo de Funcionarios");
    comando.setFont(new Font("Arial", Font.BOLD, 40));
    comando.setBounds(20, 80, 500, 100);
    comando.setForeground(letra);


    janela10.add(comando);

    JButton cadastrarl = new JButton("Cadastrar livro");
    cadastrarl.setBounds(50, 200, 180, 40);
    cadastrarl.setForeground(letra);
    cadastrarl.setBackground(fundobotao);
    cadastrarl.setFont(new Font("Arial", Font.BOLD, 20));
    cadastrarl.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela10.dispose();
        abrirjanela11();
      }
    });

    JButton buscar = new JButton("Buscar livro");
    buscar.setBounds(250, 200, 180, 40);
    buscar.setForeground(letra);
    buscar.setBackground(fundobotao);
    buscar.setFont(new Font("Arial", Font.BOLD, 20));
    buscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela10.dispose();
        abrirjanela13();
      }
    });
JButton voltar = new JButton("Logout");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela10.dispose();
          abrirjanela9();
            
    
          }
        });
        janela10.getContentPane().add(voltar);
    janela10.getContentPane().setLayout(null);
    janela10.getContentPane().add(cadastrarl);
    janela10.getContentPane().add(buscar);

    janela10.setVisible(true);
  }



  



   public void abrirjanela11() {
        JFrame janela11 = new JFrame("Cadastro de Livro");
        janela11.setSize(500, 500);
        janela11.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela11.setLayout(null);
        janela11.getContentPane().setBackground(fundojanela);

        JLabel comando = new JLabel("Cadastro de livro:");
        comando.setFont(new Font("Arial", Font.BOLD, 30));
        comando.setBounds(30, -10, 400, 100);

        janela11.add(comando);

        JLabel rotuloId = new JLabel("Id:");
        rotuloId.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloId.setBounds(30, 70, 200, 20);

        JTextField campoId = new JTextField();
        campoId.setBounds(220, 70, 250, 30);

        JLabel rotuloTitulo = new JLabel("Título:");
        rotuloTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloTitulo.setBounds(30, 110, 200, 20);

        JTextField campoTitulo = new JTextField();
        campoTitulo.setBounds(220, 110, 250, 30);

        JLabel rotuloAutor = new JLabel("Autor:");
        rotuloAutor.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloAutor.setBounds(30, 150, 100, 20);

        JTextField campoAutor = new JTextField();
        campoAutor.setBounds(220, 150, 250, 30);

        JLabel rotuloISBN = new JLabel("ISBN:");
        rotuloISBN.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloISBN.setBounds(30, 190, 100, 20);

        JTextField campoISBN = new JTextField();
        campoISBN.setBounds(220, 190, 250, 30);

        JLabel rotuloEditora = new JLabel("Editora:");
        rotuloEditora.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloEditora.setBounds(30, 230, 100, 20);

        JTextField campoEditora = new JTextField();
        campoEditora.setBounds(220, 230, 250, 30);

        JLabel rotuloAnoPublicacao = new JLabel("Ano de publicação:");
        rotuloAnoPublicacao.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloAnoPublicacao.setBounds(30, 270, 170, 20);

        JTextField campoAnoPublicacao = new JTextField();
        campoAnoPublicacao.setBounds(220, 270, 250, 30);

        JLabel rotuloQuantidadeDisponivel = new JLabel("Quantidade Disponível:");
        rotuloQuantidadeDisponivel.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloQuantidadeDisponivel.setBounds(30, 310, 200, 20);

        JTextField campoQuantidadeDisponivel = new JTextField();
        campoQuantidadeDisponivel.setBounds(220, 310, 250, 30);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(280, 360, 180, 40);
        botaoCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
        botaoCadastrar.setForeground(letra);
        botaoCadastrar.setBackground(fundobotao);

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obter os valores dos campos de texto
                    String titulo = campoTitulo.getText();
                    String autor = campoAutor.getText();
                    String isbn = campoISBN.getText();
                    String editora = campoEditora.getText();
                    String anoPublicacaoString = campoAnoPublicacao.getText();
                    String quantidadeDisponivelString = campoQuantidadeDisponivel.getText();

                    // Verificar se os campos obrigatórios estão preenchidos
                    if (isbn.isEmpty() || anoPublicacaoString.isEmpty() || quantidadeDisponivelString.isEmpty()) {
                        JOptionPane.showMessageDialog(janela11, "Por favor, preencha todos os campos obrigatórios.",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Converter valores para os tipos corretos
                    int AnoPublicacao = Integer.parseInt(anoPublicacaoString);
                    int quantidadeDisponivel = Integer.parseInt(quantidadeDisponivelString);

                    // Realizar ação de cadastro aqui...
                    try {
                      Livro livro = new Livro(titulo, autor, isbn, editora, AnoPublicacao, quantidadeDisponivel);
                      bCentral.adicionarLivro(livro);
                    } catch (Exception error) {
                      JOptionPane.showMessageDialog(janela11, error.getMessage(),
                                "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    janela11.dispose();
                    abrirjanela12();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(janela11, "Por favor, preencha os campos numéricos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela11.dispose();
      abrirjanela10();
        

      }
    });
    janela11.getContentPane().add(voltar);
        janela11.add(rotuloId);
        janela11.add(campoId);
        janela11.add(rotuloTitulo);
        janela11.add(campoTitulo);
        janela11.add(rotuloAutor);
        janela11.add(campoAutor);
        janela11.add(rotuloISBN);
        janela11.add(campoISBN);
        janela11.add(rotuloEditora);
        janela11.add(campoEditora);
        janela11.add(rotuloAnoPublicacao);
        janela11.add(campoAnoPublicacao);
        janela11.add(rotuloQuantidadeDisponivel);
        janela11.add(campoQuantidadeDisponivel);
        janela11.add(botaoCadastrar);

        // Exibe a janela11
        janela11.setVisible(true);
    }



  public void abrirjanela12() {

    JFrame janela12 = new JFrame("Cadastro de livro realizado com sucesso!");
    janela12.setSize(500,500);
    janela12.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela12.getContentPane().setBackground(fundojanela);


    JLabel msgaluno = new JLabel("Cadastro de livro realizado com sucesso!");
    msgaluno.setFont(new Font("Arial", Font.BOLD, 24));
    msgaluno.setBounds(10, 120, 500, 100);
    msgaluno.setForeground(letra);
    janela12.add(msgaluno);

    JButton botaoacervofuncionarios = new JButton("Clique aqui para ser redirecionado ao acervo de funcionarios");
    botaoacervofuncionarios.setBounds(0, 220, 480, 30);
    botaoacervofuncionarios.setForeground(letra);
    botaoacervofuncionarios.setBackground(fundobotao);
    botaoacervofuncionarios.setFont(new Font("Arial", Font.BOLD, 15));
    botaoacervofuncionarios.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        janela12.dispose();
        abrirjanela10();
      }
    });

    janela12.getContentPane().setLayout(null);
    janela12.getContentPane().add(botaoacervofuncionarios);

    janela12.setVisible(true);
  }








  public void abrirjanela13() {

    JFrame janela13 = new JFrame("buscar por:");
    janela13.setSize(500,500);
    janela13.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela13.getContentPane().setBackground(fundojanela);

    JLabel comando = new JLabel("Buscar por:");
    comando.setFont(new Font("Arial", Font.BOLD, 30));
    comando.setBounds(20, 80, 500, 100);
    comando.setForeground(letra);


    janela13.add(comando);

    JButton portitulo = new JButton("Titulo");
    portitulo.setBounds(40, 200, 120, 40);
    portitulo.setForeground(letra);
    portitulo.setBackground(fundobotao);
    portitulo.setFont(new Font("Arial", Font.BOLD, 20));
    portitulo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela13.dispose();
        abrirjanela15();
    
      }
    });

  
    JButton porautor = new JButton("Autor");
    porautor.setBounds(180, 200, 120, 40);
    porautor.setForeground(letra);
    porautor.setBackground(fundobotao);
    porautor.setFont(new Font("Arial", Font.BOLD, 20));
    porautor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela13.dispose();
        abrirjanela14();
        
      }
    });

   JButton porisbn = new JButton("INBS");
    porisbn.setBounds(320, 200, 120, 40);
    porisbn.setForeground(letra);
    porisbn.setBackground(fundobotao);
    porisbn.setFont(new Font("Arial", Font.BOLD, 20));
    porisbn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela13.dispose();
        abrirjanela16();
        
      }
    });

    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela13.dispose();
      abrirjanela10();
        

      }
    });
    janela13.getContentPane().add(voltar);
    janela13.getContentPane().setLayout(null);
    janela13.getContentPane().add(portitulo);
    janela13.getContentPane().add(porautor);
    janela13.getContentPane().add(porisbn);

    janela13.setVisible(true);
  }








  public void abrirjanela14() {
    JFrame janela14 = new JFrame("Buscar");
    janela14.setSize(500,500);
    janela14.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela14.setLayout(null);
    janela14.getContentPane().setBackground(fundojanela);
    

    JLabel rotuloAutor = new JLabel("Autor:");
    rotuloAutor.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloAutor.setBounds(50, 130, 100, 20);

    JTextField campoAutor = new JTextField();
    campoAutor.setBounds(120, 130, 300, 30);

    JButton botaoBuscar = new JButton("Buscar");
    botaoBuscar.setBounds(170, 250, 120, 40);
    botaoBuscar.setForeground(letra);
    botaoBuscar.setBackground(fundobotao);
    botaoBuscar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String autor = campoAutor.getText();

        if (autor.isEmpty()) {
          JOptionPane.showMessageDialog(janela14, "Por favor, preencha o campo acima.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          
          try {
          List<Livro> livros = repoLivros.buscarLivroPorAutor(autor);
          if (livros.isEmpty()) {JOptionPane.showMessageDialog(janela14, "O autor não tem livros cadastrados no sistema!",
              "Erro", JOptionPane.ERROR_MESSAGE);
            return;
            }
          janela14.dispose();
          abrirjanela30(livros);
          } catch (Exception error) {
            JOptionPane.showMessageDialog(janela14, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
          // Aqui você pode implementar a lógica de autenticação
       
          
        }
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela14.dispose();
      abrirjanela13();
        

      }
    });
    janela14.getContentPane().add(voltar);
    janela14.add(rotuloAutor);
    janela14.add(campoAutor);
    janela14.add(botaoBuscar);

    janela14.setVisible(true);
  }






  public void abrirjanela15() {
    JFrame janela15 = new JFrame("Buscar");
    janela15.setSize(500,500);
    janela15.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela15.setLayout(null);
    janela15.getContentPane().setBackground(fundojanela);

    JLabel rotuloTitulo = new JLabel("Titulo:");
    rotuloTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloTitulo.setBounds(50, 130, 100, 20);

    JTextField campoTitulo = new JTextField();
    campoTitulo.setBounds(150, 130, 300, 30);

     JButton botaoBuscar = new JButton("Buscar");
    botaoBuscar.setBounds(170, 250, 120, 40);
    botaoBuscar.setForeground(letra);
    botaoBuscar.setBackground(fundobotao);
    botaoBuscar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String titulo = campoTitulo.getText();

        if (titulo.isEmpty()) {
          JOptionPane.showMessageDialog(janela15, "Por favor, preencha o campo acima.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          
          try {
            Livro livro = repoLivros.buscarLivroPorTitulo(titulo);
            if(livro == null){
               JOptionPane.showMessageDialog(janela15, "Livro não encontrado!",
              "Erro", JOptionPane.ERROR_MESSAGE);
              return;
            }
          janela15.dispose();
          abrirjanela17(livro);
          } catch (Exception error) {
            JOptionPane.showMessageDialog(janela15, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela15.dispose();
      abrirjanela13();
        

      }
    });
    janela15.getContentPane().add(voltar);
    janela15.add(rotuloTitulo);
    janela15.add(campoTitulo);
    janela15.add(botaoBuscar);

    janela15.setVisible(true);
  }






  

public void abrirjanela16() {
    JFrame janela16 = new JFrame("Buscar");
    janela16.setSize(500,500);
    janela16.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela16.setLayout(null);
    janela16.getContentPane().setBackground(fundojanela); 

    JLabel rotuloISBN = new JLabel("ISBN:");
    rotuloISBN.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloISBN.setBounds(50, 130, 100, 20);

    JTextField campoISBN = new JTextField();
    campoISBN.setBounds(150, 130, 300, 30);


     JButton botaoBuscar = new JButton("Buscar");
    botaoBuscar.setBounds(170, 250, 120, 40);
    botaoBuscar.setForeground(letra);
    botaoBuscar.setBackground(fundobotao);
    botaoBuscar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String isbn = campoISBN.getText();
      

        if (isbn.isEmpty()) {
          JOptionPane.showMessageDialog(janela16, "Por favor, preencha o campo acima.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          
          try {
           Livro livro = repoLivros.buscarLivroPorISBN(isbn);
            if(livro == null){
               JOptionPane.showMessageDialog(janela16, "Livro não encontrado!",
              "Erro", JOptionPane.ERROR_MESSAGE);
              return;
            }
          janela16.dispose();
          abrirjanela17(livro);
          } catch (Exception error) {
            JOptionPane.showMessageDialog(janela16, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela16.dispose();
      abrirjanela13();
        

      }
    });
    janela16.getContentPane().add(voltar);
    janela16.add(rotuloISBN);
    janela16.add(campoISBN);
    janela16.add(botaoBuscar);

    janela16.setVisible(true);
  }





  
  
  public void abrirjanela17(Livro livro){
        JFrame janela17 = new JFrame("Detalhes do Livro");
        janela17.setSize(500,500);
        janela17.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela17.setLayout(null);
        janela17.getContentPane().setBackground(fundojanela);


        JButton remover = new JButton("Remover livro");
        remover.setBounds(280, 400, 180, 40);
        remover.setFont(new Font("Arial", Font.BOLD, 20));
        remover.setForeground(letra);
        remover.setBackground(fundobotao);
        remover.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

          try {
            repoLivros.remover(livro.getISBN());
            janela17.dispose();
          abrirjanela18();
          } catch (Exception error) {
            JOptionPane.showMessageDialog(janela17, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
        
        }
    });

    JLabel rotuloTitulo = new JLabel("Título:");
    rotuloTitulo.setFont(new Font("Arial", Font.BOLD, 16));
    rotuloTitulo.setBounds(30, 110, 200, 20);

    JTextField campoTitulo = new JTextField();
    campoTitulo.setBounds(220, 110, 250, 30);

    JLabel rotuloAutor = new JLabel("Autor:");
    rotuloAutor.setFont(new Font("Arial", Font.BOLD, 16));
    rotuloAutor.setBounds(30, 150, 100, 20);

    JTextField campoAutor = new JTextField();
    campoAutor.setBounds(220, 150, 250, 30);

    JLabel rotuloISBN = new JLabel("ISBN:");
    rotuloISBN.setFont(new Font("Arial", Font.BOLD, 16));
    rotuloISBN.setBounds(30, 190, 100, 20);

    JTextField campoISBN = new JTextField();
    campoISBN.setBounds(220, 190, 250, 30);

    JLabel rotuloEditora = new JLabel("Editora:");
    rotuloEditora.setFont(new Font("Arial", Font.BOLD, 16));
    rotuloEditora.setBounds(30, 230, 100, 20);

    JTextField campoEditora = new JTextField();
    campoEditora.setBounds(220, 230, 250, 30);


    JLabel rotuloAnoPublicacao = new JLabel("Ano de publicação:");
    rotuloAnoPublicacao.setFont(new Font("Arial", Font.BOLD, 16));
    rotuloAnoPublicacao.setBounds(30, 310, 170, 20);

    JTextField campoAnoPublicacao = new JTextField();
    campoAnoPublicacao.setBounds(220, 310, 250, 30);

    JLabel rotuloQuantidadeDisponivel = new JLabel("Quantidade Disponível:");
    rotuloQuantidadeDisponivel.setFont(new Font("Arial", Font.BOLD, 16));
    rotuloQuantidadeDisponivel.setBounds(30, 350, 200, 20);

    JTextField campoQuantidadeDisponivel = new JTextField();
    campoQuantidadeDisponivel.setBounds(220, 350, 250, 30);

        // Definir valores dos campos de texto
        /* campoId.setText("ID"); */
        campoTitulo.setText(livro.getTitulo());
        campoAutor.setText(livro.getAutor());
        campoISBN.setText(livro.getISBN());
        /* campoEmail.setText("exemplo@email.com"); */
        campoEditora.setText(livro.getEditora());
        campoAnoPublicacao.setText(String.valueOf(livro.getAnoPublicacao()));
        /* campoQuantidadeTotal.setText(livro.get); */
        campoQuantidadeDisponivel.setText(String.valueOf(livro.getQuantidadeDisponivel()));


        JButton voltar = new JButton("voltar");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela17.dispose();
          abrirjanela13();
            
    
          }
        });
        janela17.getContentPane().add(voltar);
        
        janela17.add(rotuloTitulo);
        janela17.add(campoTitulo);
        janela17.add(rotuloAutor);
        janela17.add(campoAutor);
        janela17.add(rotuloISBN);
        janela17.add(campoISBN);
        
        janela17.add(rotuloEditora);
        janela17.add(campoEditora);
        
        janela17.add(rotuloQuantidadeDisponivel);
        janela17.add(campoQuantidadeDisponivel);
        janela17.getContentPane().add(remover);

        janela17.setVisible(true);
    }







  public void abrirjanela18() {

    JFrame janela18 = new JFrame("");
    janela18.setSize(500,500);
    janela18.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela18.getContentPane().setBackground(fundojanela);

    JLabel mensagem = new JLabel("Livro removido com sucesso!");
    mensagem.setFont(new Font("Arial", Font.BOLD, 28));
    mensagem.setBounds(40, 120, 500, 100);
    mensagem.setForeground(letra);
    janela18.add(mensagem);

    JButton botaoacervofuncionarios = new JButton("Clique aqui para ser redirecionado ao acervo de funcionarios");
    botaoacervofuncionarios.setBounds(0, 220, 490, 30);
    botaoacervofuncionarios.setForeground(letra);
    botaoacervofuncionarios.setBackground(fundobotao);
    botaoacervofuncionarios.setFont(new Font("Arial", Font.BOLD, 15));
    botaoacervofuncionarios.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        janela18.dispose();
        abrirjanela10();
      }
    });

    janela18.getContentPane().setLayout(null);
    janela18.getContentPane().add(botaoacervofuncionarios);

    janela18.setVisible(true);
  }






  public void abrirjanela19() {
    JFrame janela19 = new JFrame("Login");
    janela19.setSize(500,500);
    janela19.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela19.setLayout(null);
    janela19.getContentPane().setBackground(fundojanela);

    JLabel rotuloMatricula = new JLabel("Matrícula:");
    rotuloMatricula.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloMatricula.setBounds(40, 140, 100, 20);

    JTextField campoMatricula = new JTextField();
    campoMatricula.setBounds(160, 140, 300, 30);

    JLabel rotuloSenha = new JLabel("Senha:");
    rotuloSenha.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloSenha.setBounds(40, 190, 100, 20);

    JPasswordField campoSenha = new JPasswordField();
    campoSenha.setBounds(160, 190, 300, 30);

    JButton botaoLogin = new JButton("Login");
    botaoLogin.setBounds(280, 320, 150, 40);
    botaoLogin.setForeground(letra);
    botaoLogin.setBackground(fundobotao);
    botaoLogin.setFont(new Font("Arial", Font.BOLD, 20));
    botaoLogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String matricula = campoMatricula.getText();
        char[] senhaChars = campoSenha.getPassword();
        String senha = new String(senhaChars);

        if (matricula.isEmpty() || senha.isEmpty()) {
          JOptionPane.showMessageDialog(janela19, "Por favor, preencha todos os campos.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          
          try{
            Boolean login = bCentral.login(matricula, senha);
            if(login) matriculaUsuario = matricula;
            String type = repoUsuarios.buscar(matricula).getTipo();
            
      
            if(type.equals("funcionario")){
              JOptionPane.showMessageDialog(janela19, "Este usuário não exite!",
              "Erro", JOptionPane.ERROR_MESSAGE);
              return;
            }
          janela19.dispose();
          abrirjanela20();
          }catch(Exception error){
            JOptionPane.showMessageDialog(janela19, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela19.dispose();
      abrirjanela8();
        

      }
    });
    janela19.getContentPane().add(voltar);
    janela19.add(rotuloMatricula);
    janela19.add(campoMatricula);
    janela19.add(rotuloSenha);
    janela19.add(campoSenha);
    janela19.add(botaoLogin);

    janela19.setVisible(true);
  }




  
  //acervo de alunos
  public void abrirjanela20() {
    JFrame janela20 = new JFrame("Acervo de alunos");
    janela20.setSize(500,500);
    janela20.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela20.getContentPane().setBackground(fundojanela);


    JLabel comando = new JLabel("Acervo de alunos");
    comando.setFont(new Font("Arial", Font.BOLD, 40));
    comando.setBounds(70, 80, 500, 100);
    comando.setForeground(letra);

    janela20.add(comando);

    JButton buscar = new JButton("Buscar livro");
    buscar.setBounds(250, 200, 180, 40);
    buscar.setForeground(letra);
    buscar.setBackground(fundobotao);
    buscar.setFont(new Font("Arial", Font.BOLD, 20));
    buscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela20.dispose();
        abrirjanela21();
      }
    });

    JButton devolver = new JButton("Devolver livro");
    devolver.setBounds(50, 200, 180, 40);
    devolver.setForeground(letra);
    devolver.setBackground(fundobotao);
    devolver.setFont(new Font("Arial", Font.BOLD, 20));
    devolver.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        UsuarioAbstrato user = repoUsuarios.buscar(matriculaUsuario);
        List<Emprestimo> emprestimosDoUser = repoEmprestimos.getEmprestimosPorUsuario(user);
        if(emprestimosDoUser.isEmpty()) {
          JOptionPane.showMessageDialog(janela20, "Este usuário não tem emprestimos feitos.",
              "Erro", JOptionPane.ERROR_MESSAGE);
              return;
        }

        janela20.dispose();
        abrirjanela27(emprestimosDoUser);
        
      }
    });
JButton voltar = new JButton("Logout");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela20.dispose();
          abrirjanela19();
            
    
          }
        });
        janela20.getContentPane().add(voltar);
    janela20.getContentPane().setLayout(null);
    janela20.getContentPane().add(buscar);
    janela20.getContentPane().add(devolver);

    janela20.setVisible(true);
  }






  
public void abrirjanela21() {

    JFrame janela21 = new JFrame("buscar por:");
    janela21.setSize(500,500);
    janela21.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela21.getContentPane().setBackground(fundojanela);

    JLabel comando = new JLabel("Buscar por:");
    comando.setFont(new Font("Arial", Font.BOLD, 30));
    comando.setBounds(20, 80, 500, 100);
    comando.setForeground(letra);


    janela21.add(comando);

    JButton portitulo = new JButton("Titulo");
    portitulo.setBounds(40, 200, 120, 40);
    portitulo.setForeground(letra);
    portitulo.setBackground(fundobotao);
    portitulo.setFont(new Font("Arial", Font.BOLD, 20));
    portitulo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela21.dispose();
        abrirjanela22();
    
      }
    });

    JButton porautor = new JButton("Autor");
    porautor.setBounds(180, 200, 120, 40);
    porautor.setForeground(letra);
    porautor.setBackground(fundobotao);
    porautor.setFont(new Font("Arial", Font.BOLD, 20));
    porautor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela21.dispose();
        abrirjanela23();
        
      }
    });

    JButton porisbn = new JButton("INBS");
    porisbn.setBounds(320, 200, 120, 40);
    porisbn.setForeground(letra);
    porisbn.setBackground(fundobotao);
    porisbn.setFont(new Font("Arial", Font.BOLD, 20));
    porisbn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        janela21.dispose();
        abrirjanela24();
        
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela21.dispose();
      abrirjanela20();
        

      }
    });
    janela21.getContentPane().add(voltar);
    janela21.getContentPane().setLayout(null);
    janela21.getContentPane().add(portitulo);
    janela21.getContentPane().add(porautor);
    janela21.getContentPane().add(porisbn);

    janela21.setVisible(true);
  }






  public void abrirjanela22() {
    JFrame janela22 = new JFrame("Buscar");
    janela22.setSize(500,500);
    janela22.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela22.setLayout(null);
    janela22.getContentPane().setBackground(fundojanela);

    JLabel rotuloTitulo = new JLabel("Titulo:");
    rotuloTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloTitulo.setBounds(50, 130, 100, 20);

    JTextField campoTitulo = new JTextField();
    campoTitulo.setBounds(150, 130, 300, 30);

     JButton botaoBuscar = new JButton("Buscar");
    botaoBuscar.setBounds(170, 250, 120, 40);
    botaoBuscar.setForeground(letra);
    botaoBuscar.setBackground(fundobotao);
    botaoBuscar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String titulo = campoTitulo.getText();

        if (titulo.isEmpty()) {
          JOptionPane.showMessageDialog(janela22, "Por favor, preencha o campo acima.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          
          try {
            Livro livro = repoLivros.buscarLivroPorTitulo(titulo);
            if(livro == null){
               JOptionPane.showMessageDialog(janela22, "Livro não encontrado!",
              "Erro", JOptionPane.ERROR_MESSAGE);
              return;
            }
              janela22.dispose();
              abrirjanela25(livro);
          /* janela15.dispose();
          abrirjanela17(livro); */
          } catch (Exception error) {
            JOptionPane.showMessageDialog(janela22, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }

        }
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela22.dispose();
      abrirjanela21();
        

      }
    });
    janela22.getContentPane().add(voltar);
   
    janela22.add(rotuloTitulo);
    janela22.add(campoTitulo);
    janela22.add(botaoBuscar);

    janela22.setVisible(true);
  }






  

public void abrirjanela23() {
    JFrame janela23 = new JFrame("Buscar");
    janela23.setSize(500,500);
    janela23.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela23.setLayout(null);
    janela23.getContentPane().setBackground(fundojanela);

    JLabel rotuloAutor = new JLabel("Autor:");
    rotuloAutor.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloAutor.setBounds(50, 130, 100, 20);

    JTextField campoAutor = new JTextField();
    campoAutor.setBounds(120, 130, 300, 30);

    JButton botaoBuscar = new JButton("Buscar");
    botaoBuscar.setBounds(170, 250, 120, 40);
    botaoBuscar.setForeground(letra);
    botaoBuscar.setBackground(fundobotao);
    botaoBuscar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String autor = campoAutor.getText();

        if (autor.isEmpty()) {
          JOptionPane.showMessageDialog(janela23, "Por favor, preencha o campo acima.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          
          try {
            List<Livro> livros = repoLivros.buscarLivroPorAutor(autor);
            if (livros.isEmpty()) {JOptionPane.showMessageDialog(janela23, "O autor não tem livros cadastrados no sistema!",
                "Erro", JOptionPane.ERROR_MESSAGE);
              return;
              }
            janela23.dispose();
            abrirjanela31(livros);
            } catch (Exception error) {
              JOptionPane.showMessageDialog(janela23, error.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela23.dispose();
      abrirjanela21();
        

      }
    });
    janela23.getContentPane().add(voltar);
   
    janela23.add(rotuloAutor);
    janela23.add(campoAutor);
    janela23.add(botaoBuscar);

    janela23.setVisible(true);
  }
  



   public void abrirjanela24() {
    JFrame janela24 = new JFrame("Buscar");
    janela24.setSize(500,500);
    janela24.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela24.setLayout(null);
    janela24.getContentPane().setBackground(fundojanela);

    JLabel rotuloISBN = new JLabel("ISBN:");
    rotuloISBN.setFont(new Font("Arial", Font.BOLD, 20));
    rotuloISBN.setBounds(50, 130, 100, 20);

    JTextField campoISBN = new JTextField();
    campoISBN.setBounds(150, 130, 300, 30);


     JButton botaoBuscar = new JButton("Buscar");
    botaoBuscar.setBounds(170, 250, 120, 40);
    botaoBuscar.setForeground(letra);
    botaoBuscar.setBackground(fundobotao);
    botaoBuscar.setFont(new Font("Arial", Font.BOLD, 20));
    botaoBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Verifica se os campos estão preenchidos
        String isbn = campoISBN.getText();
      

        if (isbn.isEmpty()) {
          JOptionPane.showMessageDialog(janela24, "Por favor, preencha o campo acima.",
              "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          
          try {
            Livro livro = repoLivros.buscarLivroPorISBN(isbn);
             if(livro == null){
                JOptionPane.showMessageDialog(janela24, "Livro não encontrado!",
               "Erro", JOptionPane.ERROR_MESSAGE);
               return;
             }
           janela24.dispose();
           abrirjanela25(livro);
           } catch (Exception error) {
             JOptionPane.showMessageDialog(janela24, error.getMessage(),
               "Erro", JOptionPane.ERROR_MESSAGE);
           }

        }
      }
    });
    JButton voltar = new JButton("voltar");
    voltar.setBounds(10, 420, 50, 20);
    voltar.setFont(new Font("Arial", Font.BOLD, 10));
    voltar.setForeground(letra);
    voltar.setBorder(null);
    voltar.setBackground(fundobotao);
    voltar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      janela24.dispose();
      abrirjanela21();
        

      }
    });
    janela24.getContentPane().add(voltar);
   
    janela24.add(rotuloISBN);
    janela24.add(campoISBN);
    janela24.add(botaoBuscar);

    janela24.setVisible(true);
  }







  public void abrirjanela25(Livro livro){
        JFrame janela25 = new JFrame("Detalhes do Livro");
        janela25.setSize(500,500);
        janela25.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela25.setLayout(null);
        janela25.getContentPane().setBackground(fundojanela);


        JButton remover = new JButton("Pegar livro emprestado");
        remover.setBounds(150, 400, 300, 40);
        remover.setFont(new Font("Arial", Font.BOLD, 20));
        remover.setForeground(letra);
        remover.setBackground(fundobotao);
        remover.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        try {
          Emprestimo emprestimo = new Emprestimo(livro, repoUsuarios.buscar(matriculaUsuario));
          repoEmprestimos.cadastrarEmprestimo(emprestimo);
        } catch (Exception error) {
          JOptionPane.showMessageDialog(janela25, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
        }
        janela25.dispose();
        abrirjanela26();
        }
    });

       

    JLabel rotuloTitulo = new JLabel("Título:");
    rotuloTitulo.setFont(new Font("Arial", Font.BOLD, 16));
    rotuloTitulo.setBounds(30, 110, 200, 20);

    JTextField campoTitulo = new JTextField();
    campoTitulo.setBounds(220, 110, 250, 30);
        campoTitulo.setEditable(false);

        JLabel rotuloAutor = new JLabel("Autor:");
        rotuloAutor.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloAutor.setBounds(30, 150, 100, 20);

        JTextField campoAutor = new JTextField();
        campoAutor.setBounds(220, 150, 250, 30);
        campoAutor.setEditable(false);

        JLabel rotuloISBN = new JLabel("ISBN:");
        rotuloISBN.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloISBN.setBounds(30, 190, 100, 20);

        JTextField campoISBN = new JTextField();
        campoISBN.setBounds(220, 190, 250, 30);
        campoISBN.setEditable(false);

        JLabel rotuloEditora = new JLabel("Editora:");
        rotuloEditora.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloEditora.setBounds(30, 230, 100, 20);

        JTextField campoEditora = new JTextField();
        campoEditora.setBounds(220, 230, 250, 30);
        campoEditora.setEditable(false);

        JLabel rotuloAnoPublicacao = new JLabel("Ano de publicação:");
        rotuloAnoPublicacao.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloAnoPublicacao.setBounds(30, 270, 170, 20);

        JTextField campoAnoPublicacao = new JTextField();
        campoAnoPublicacao.setBounds(220, 270, 250, 30);
        campoAnoPublicacao.setEditable(false);

        JLabel rotuloQuantidadeDisponivel = new JLabel("Quantidade Disponível:");
        rotuloQuantidadeDisponivel.setFont(new Font("Arial", Font.BOLD, 16));
        rotuloQuantidadeDisponivel.setBounds(30, 310, 200, 20);

        JTextField campoQuantidadeDisponivel = new JTextField();
        campoQuantidadeDisponivel.setBounds(220, 310, 250, 30);
        campoQuantidadeDisponivel.setEditable(false);

        // Definir valores dos campos de texto
/*         campoId.setText("id"); */
        campoTitulo.setText(livro.getTitulo());
        campoAutor.setText(livro.getAutor());
        campoISBN.setText(livro.getISBN());
        /* campoEmail.setText(livro.get); */
        campoEditora.setText(livro.getEditora());
        campoAnoPublicacao.setText(Integer.toString(livro.getAnoPublicacao()));
/*         campoQuantidadeTotal.setText("10"); */
        campoQuantidadeDisponivel.setText(Integer.toString(livro.getQuantidadeDisponivel()));
        JButton voltar = new JButton("voltar");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela25.dispose();
          abrirjanela21();
            
    
          }
        });
        janela25.getContentPane().add(voltar);
      
        janela25.add(rotuloTitulo);
        janela25.add(campoTitulo);
        janela25.add(rotuloAutor);
        janela25.add(campoAutor);
        janela25.add(rotuloISBN);
        janela25.add(campoISBN);
        
        janela25.add(rotuloEditora);
        janela25.add(campoEditora);
        
        janela25.add(rotuloQuantidadeDisponivel);
        janela25.add(campoQuantidadeDisponivel);
        janela25.getContentPane().add(remover);

        janela25.setVisible(true);
    }





   public void abrirjanela26() {

    JFrame janela26 = new JFrame("");
    janela26.setSize(500,500);
    janela26.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela26.getContentPane().setBackground(fundojanela);

    JLabel mensagem = new JLabel("Livro emprestado com sucesso!");
    mensagem.setFont(new Font("Arial", Font.BOLD, 28));
    mensagem.setBounds(40, 120, 500, 100);
    mensagem.setForeground(letra);

    janela26.add(mensagem);

    JButton botaoacervofuncionarios = new JButton("Clique aqui para ser redirecionado ao acervo de alunos");
    botaoacervofuncionarios.setBounds(0, 220, 490, 30);
    botaoacervofuncionarios.setForeground(letra);
    botaoacervofuncionarios.setBackground(fundobotao);
    botaoacervofuncionarios.setFont(new Font("Arial", Font.BOLD, 15));

    botaoacervofuncionarios.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        janela26.dispose();
        abrirjanela20();
      }
    });

    janela26.getContentPane().setLayout(null);
    janela26.getContentPane().add(botaoacervofuncionarios);

    janela26.setVisible(true);
  }








  

public void abrirjanela27(List<Emprestimo> listaEmprestimos) {

        JFrame janela27 = new JFrame("Devolução de Livro");
        janela27.setSize(500, 500);
        janela27.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela27.setLayout(null);
        janela27.getContentPane().setBackground(fundojanela);

        JLabel rotuloSelecao = new JLabel("Selecione o livro a ser devolvido:");
        rotuloSelecao.setFont(new Font("Arial", Font.BOLD, 20));
        rotuloSelecao.setBounds(120, 90, 300, 30);

        JComboBox<String> comboBoxLivros = new JComboBox<String>();
        comboBoxLivros.setBounds(100, 140, 300, 30);
        for (Emprestimo emprestimo : listaEmprestimos) {
          comboBoxLivros.addItem(emprestimo.getLivro().getTitulo());
        }

        JButton botaoDevolver = new JButton("Devolver");
        botaoDevolver.setBounds(190, 220, 120, 40);
        botaoDevolver.setForeground(letra);
        botaoDevolver.setBackground(fundobotao);
        botaoDevolver.setFont(new Font("Arial", Font.BOLD, 20));        botaoDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String livroSelecionadoString = (String) comboBoxLivros.getSelectedItem();
                Livro livroSelecionado = repoLivros.buscarLivroPorTitulo(livroSelecionadoString);
                UsuarioAbstrato user = repoUsuarios.buscar(matriculaUsuario);

              Emprestimo emprestimo = repoEmprestimos.getEmprestimoPorUsuarioELivro(user, livroSelecionado);
                if (livroSelecionado != null) {
                    

                    janela27.dispose();
                    abrirjanela28(emprestimo);
                }
            }
        });
        JButton voltar = new JButton("voltar");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela27.dispose();
          abrirjanela20();
            
    
          }
        });
        janela27.getContentPane().add(voltar);
    
        janela27.add(rotuloSelecao);
        janela27.add(comboBoxLivros);
        janela27.add(botaoDevolver);

        janela27.setVisible(true);
    }








  public void abrirjanela28(Emprestimo emprestimo){
        JFrame janela28 = new JFrame("Detalhes do Livro");
        janela28.setSize(500,500);
        janela28.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela28.setLayout(null);
        janela28.getContentPane().setBackground(fundojanela);


        JButton devolver = new JButton("Devolver livro");
        devolver.setBounds(190, 270, 150, 40);
        devolver.setForeground(letra);
        devolver.setBackground(fundobotao);
        devolver.setFont(new Font("Arial", Font.BOLD, 15));
        devolver.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            repoEmprestimos.removerEmprestimo(emprestimo);
          } catch (Exception error) {
            JOptionPane.showMessageDialog(janela28, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
        
        janela28.dispose();
        abrirjanela29();
        }
    }); 

        JButton paydevolver = new JButton("Pagar multa e devolver livro");
        paydevolver.setBounds(140, 330, 250, 40);
        paydevolver.setForeground(letra);
        paydevolver.setBackground(fundobotao);
        paydevolver.setFont(new Font("Arial", Font.BOLD, 15));
        paydevolver.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            repoEmprestimos.removerEmprestimo(emprestimo);
          } catch (Exception error) {
            JOptionPane.showMessageDialog(janela28, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
          }
        
        janela28.dispose();
        abrirjanela29();
        }
    });

        JLabel rotulodataemprestimo = new JLabel("data emprestimo:");
        rotulodataemprestimo.setFont(new Font("Arial", Font.BOLD, 20));
        rotulodataemprestimo.setBounds(50, 30, 100, 20);

        JTextField dataemprestimo = new JTextField();
        dataemprestimo.setBounds(170, 30, 300, 30);
        dataemprestimo.setEditable(false); // Impede a edição do campo

        JLabel rotulodatadevolucao = new JLabel("data devolução:");
        rotulodatadevolucao.setFont(new Font("Arial", Font.BOLD, 20));
        rotulodatadevolucao.setBounds(50, 60, 100, 20);

        JTextField datadevolucao = new JTextField();
        datadevolucao.setBounds(170, 60, 300, 30);
        datadevolucao.setEditable(false);

        JLabel rotuloatraso = new JLabel("atraso:");
        rotuloatraso.setFont(new Font("Arial", Font.BOLD, 20));
        rotuloatraso.setBounds(50, 90, 100, 20);

        JTextField atraso = new JTextField();
        atraso.setBounds(170, 90, 300, 30);
        atraso.setEditable(false);

        JLabel rotulomulta = new JLabel("multa:");
        rotulomulta.setFont(new Font("Arial", Font.BOLD, 20));
        rotulomulta.setBounds(50, 120, 100, 20);

        JTextField multa = new JTextField();
        multa.setBounds(170, 120, 300, 30);
        multa.setEditable(false);

        
        // linkar valores dos campos de texto
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataEmprestimoFormatada = emprestimo.getDataEmprestimo().format(formatter);
        String dataEmprestimoDevolucao = emprestimo.getDataDevolucao().format(formatter);
        


        dataemprestimo.setText(dataEmprestimoFormatada);
        datadevolucao.setText(dataEmprestimoDevolucao);


        atraso.setText(String.valueOf(emprestimo.calcularDiasAtraso()));
        multa.setText(String.valueOf(emprestimo.calcularMulta()));
        
        JButton voltar = new JButton("voltar");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela28.dispose();
          abrirjanela20();
            
    
          }
        });
        janela28.getContentPane().add(voltar);
    
        janela28.add(dataemprestimo);
        janela28.add(dataemprestimo);
        janela28.add(datadevolucao);
        janela28.add(datadevolucao);
        janela28.add(atraso);
        janela28.add(atraso);
        janela28.add(multa);
        janela28.add(multa);
        janela28.getContentPane().add(devolver);
        janela28.getContentPane().add(paydevolver);

        janela28.setVisible(true);
    }






  public void abrirjanela29() {

    JFrame janela29 = new JFrame("");
    janela29.setSize(500,500);
    janela29.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela29.getContentPane().setBackground(fundojanela);

    JLabel mensagem = new JLabel("Livro devolvido com sucesso!");
    mensagem.setFont(new Font("Arial", Font.BOLD, 20));
    mensagem.setBounds(40, 120, 500, 100);
    mensagem.setForeground(letra);

    janela29.add(mensagem);

    JButton botaoacervofuncionarios = new JButton("Clique aqui para ser redirecionado ao acervo de alunos");
    botaoacervofuncionarios.setBounds(0, 220, 490, 30);
    botaoacervofuncionarios.setForeground(letra);
    botaoacervofuncionarios.setBackground(fundobotao);
    botaoacervofuncionarios.setFont(new Font("Arial", Font.BOLD, 15));
    botaoacervofuncionarios.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        janela29.dispose();
        abrirjanela20();
      }
    });

    janela29.getContentPane().setLayout(null);
    janela29.getContentPane().add(botaoacervofuncionarios);

    janela29.setVisible(true);
  }







  public void abrirjanela30(List<Livro> livros){ 
    

  JFrame janela30 = new JFrame("Selecione o livro buscado:");
        janela30.setSize(500, 500);
        janela30.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela30.setLayout(null);
        janela30.getContentPane().setBackground(fundojanela);

        JLabel rotuloSelecao = new JLabel("Selecione o livro buscado:");
        rotuloSelecao.setFont(new Font("Arial", Font.BOLD, 20));
        rotuloSelecao.setBounds(50, 30, 300, 30);

        JComboBox<String> comboBoxLivros = new JComboBox<String>();
        comboBoxLivros.setBounds(50, 60, 300, 20);
        for (Livro livro : livros) {
        comboBoxLivros.addItem(livro.getTitulo());
        }


        JButton botao = new JButton("Buscar");
        botao.setBounds(190, 220, 120, 40);
        botao.setForeground(letra);
        botao.setBackground(fundobotao);
        botao.setFont(new Font("Arial", Font.BOLD, 20));
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String livroSelecionadoString = (String) comboBoxLivros.getSelectedItem();
                Livro livroSelecionado = repoLivros.buscarLivroPorTitulo(livroSelecionadoString);
                if (livroSelecionado != null) {
                    try {
                      janela30.dispose();
                    abrirjanela17(livroSelecionado);
                    } catch (Exception error) {
                      JOptionPane.showMessageDialog(janela30, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
        JButton voltar = new JButton("voltar");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela30.dispose();
          abrirjanela13();
            
    
          }
        });
        janela30.getContentPane().add(voltar);
        janela30.add(rotuloSelecao);
        janela30.add(comboBoxLivros);
        janela30.add(botao);

        janela30.setVisible(true);
      }

      public void abrirjanela31(List<Livro> livros){ 
    

  JFrame janela31 = new JFrame("Selecione o livro buscado:");
        janela31.setSize(500, 500);
        janela31.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela31.setLayout(null);
        janela31.getContentPane().setBackground(fundojanela);

        JLabel rotuloSelecao = new JLabel("Selecione o livro buscado:");
        rotuloSelecao.setFont(new Font("Arial", Font.BOLD, 20));
        rotuloSelecao.setBounds(50, 30, 300, 30);

        JComboBox<String> comboBoxLivros = new JComboBox<String>();
        comboBoxLivros.setBounds(50, 60, 300, 20);
        for (Livro livro : livros) {
        comboBoxLivros.addItem(livro.getTitulo());
        }


        JButton botao = new JButton("Buscar");
        botao.setBounds(150, 100, 120, 40);
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String livroSelecionadoString = (String) comboBoxLivros.getSelectedItem();
                Livro livroSelecionado = repoLivros.buscarLivroPorTitulo(livroSelecionadoString);
                UsuarioAbstrato user = repoUsuarios.buscar(matriculaUsuario);
                Emprestimo emprestimo = repoEmprestimos.getEmprestimoPorUsuarioELivro(user, livroSelecionado);
                if (livroSelecionado != null) {
                    try {
                      repoEmprestimos.removerEmprestimo(emprestimo);
                      janela31.dispose();
                    abrirjanela25(livroSelecionado);
                    } catch (Exception error) {
                      JOptionPane.showMessageDialog(janela31, error.getMessage(),
              "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
        JButton voltar = new JButton("voltar");
        voltar.setBounds(10, 420, 50, 20);
        voltar.setFont(new Font("Arial", Font.BOLD, 10));
        voltar.setForeground(letra);
        voltar.setBorder(null);
        voltar.setBackground(fundobotao);
        voltar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          janela31.dispose();
          abrirjanela21();
            
    
          }
        });
        janela31.getContentPane().add(voltar);
        janela31.add(rotuloSelecao);
        janela31.add(comboBoxLivros);
        janela31.add(botao);

        janela31.setVisible(true);
      }

 public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new MinhaBibliotecaGUI());
  }
}