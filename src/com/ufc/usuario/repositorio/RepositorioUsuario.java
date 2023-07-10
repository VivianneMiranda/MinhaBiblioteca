package com.ufc.usuario.repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;

public class RepositorioUsuario implements IRepositorioUsuario {
  private Vector<UsuarioAbstrato> usuarios;

  public RepositorioUsuario() {
    this.usuarios = new Vector<UsuarioAbstrato>();
    desserializar();
  }

  public void cadastrar(UsuarioAbstrato usuario) throws UJCException {
    if (this.existe(usuario.getMatricula())) {
      throw new UJCException(usuario.getMatricula());
    }

    this.usuarios.add(usuario);
    serializar();
  }

  public void remover(String matricula) throws UNCException {
    if (!this.existe(matricula)) {
      throw new UNCException(matricula);
    }
    this.usuarios.remove(this.buscar(matricula));
    serializar();
  }

  public void atualizarInformacoes(String matricula, String nome, String email, String senha, String telefone)
      throws UNCException {
    if (!this.existe(matricula)) {
      throw new UNCException(matricula);
    }
    for (UsuarioAbstrato usuario : this.usuarios) {
      if (usuario.getMatricula().equals(matricula)) {
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTelefone(telefone);
        System.out.printf(
            "Informações atualizadas:\nNome: %s,\nEmail:%s,\nTelefone:%s,\nSenha: Por questões de seguranção não será exibida",
            nome, email, telefone);
      }
    }
  }

  public UsuarioAbstrato buscar(String matricula) {
    for (UsuarioAbstrato usuario : this.usuarios) {
      if (usuario.getMatricula().equals(matricula)) {
        return usuario;
      }
    }
    return null;
  }

  public boolean existe(String matricula) {
    return matricula != null && this.buscar(matricula) != null;
  }

  public UsuarioAbstrato recuperar(String matricula) throws UJCException {
    if (!this.existe(matricula)) {
      throw new UJCException(matricula);
    }

    return this.buscar(matricula);
  }

    private void serializar() {
    File diretorio = new File("./arquivo");
    if (!diretorio.isDirectory()) {
      diretorio.mkdir();
    }
    File arquivo = new File(diretorio, "usuarios.bin");
    try {
      FileOutputStream gravador = new FileOutputStream(arquivo);
      ObjectOutputStream conversor = new ObjectOutputStream(gravador);
      conversor.writeObject(usuarios);
      conversor.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void desserializar() {
    String pathDir = "./arquivo";
    File diretorio = new File(pathDir);

    if (!diretorio.isDirectory()) {
        if (diretorio.mkdir()) {
            System.out.println("Diretório criado com sucesso.");
        } else {
            System.out.println("Não foi possível criar o diretório.");
            return;
        }
    }

    String pathArquivo = pathDir + "/" + "usuarios.bin";
    File arquivo = new File(pathArquivo);

    if (arquivo.length() == 0) {
      System.out.println("O arquivo usuarios.bin está vazio.");
      return;
  }

    if (!arquivo.exists()) {
        try {
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo usuarios.bin criado com sucesso.");
            } else {
                System.out.println("Não foi possível criar o arquivo usuarios.bin.");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    try {
        FileInputStream leitor = new FileInputStream(pathArquivo);
        ObjectInputStream conversor = new ObjectInputStream(leitor);
        Vector<UsuarioAbstrato> usuarios = (Vector<UsuarioAbstrato>) conversor.readObject();
        for (UsuarioAbstrato usuario : usuarios) {
            String matriculaUsuario = usuario.getMatricula();
            if (existe(matriculaUsuario)) {
                System.out.println(usuario.getMatricula() + " existe");
            } else {
                this.cadastrar(usuario);
                System.out.println("ok");
            }
        }
        conversor.close();
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (UJCException e) {
        e.printStackTrace();
    }
}

}
