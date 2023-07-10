package com.ufc.livro.repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.excecao.LJCException;
import com.ufc.livro.repositorio.excecao.LNCException;

public class RepositorioLivro {
  private Vector<Livro> livros;

  public RepositorioLivro() {
    this.livros = new Vector<Livro>();
    desserializar();
  }

  public void cadastrar(Livro livro) throws LJCException {
    if (this.existe(livro.getISBN())) {
      throw new LJCException(livro.getISBN());
    }

    this.livros.add(livro);
    serializar();
  }

  public void remover(String ISBN) throws LNCException {
    if (!this.existe(ISBN)) {
      throw new LNCException(ISBN);
    }
    this.livros.remove(this.buscarLivroPorISBN(ISBN));
    serializar();
  }

  public Livro buscarLivroPorTitulo(String titulo) {
    for (Livro livro : livros) {
      if (livro.getTitulo().equals(titulo)) {
        return livro;
      }
    }
    return null;
  }

  public List<Livro> buscarLivroPorAutor(String autor) {
    List<Livro> livrosDoAutor = new ArrayList<>();
    for (Livro livro : this.livros) {
      if (livro.getAutor().equals(autor)) {
        livrosDoAutor.add(livro);
      }
    }
    return livrosDoAutor;
  }

  public Livro buscarLivroPorISBN(String isbn) {
    for (Livro livro : this.livros) {
      if (livro.getISBN().equals(isbn)) {
        return livro;
      }
    }
    return null;
  }

  public boolean existe(String ISBN) {

    return ISBN != null && this.buscarLivroPorISBN(ISBN) != null;
  }

  public List<Livro> listar() {
    return new ArrayList<>(livros);
  }

  private void serializar() {
    File diretorio = new File("./arquivo");
    if (!diretorio.isDirectory()) {
      diretorio.mkdir();
    }
    File arquivo = new File(diretorio, "livros.bin");
    try {
      FileOutputStream gravador = new FileOutputStream(arquivo);
      ObjectOutputStream conversor = new ObjectOutputStream(gravador);
      conversor.writeObject(livros);
      conversor.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void desserializar() {
    System.out.println("Entrando na função desserializar LivroRepositorio");
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

    String pathArquivo = pathDir + "/" + "livros.bin";
    File arquivo = new File(pathArquivo);

    if (arquivo.length() == 0) {
      System.out.println("O arquivo livros.bin está vazio.");
      return;
    }

    if (!arquivo.exists()) {
      try {
        if (arquivo.createNewFile()) {
          System.out.println("Arquivo livros.bin criado com sucesso.");
        } else {
          System.out.println("Não foi possível criar o arquivo livros.bin.");
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
      Vector<Livro> livros = (Vector<Livro>) conversor.readObject();
      for (Livro livro : livros) {
        String ISBN = livro.getISBN();
        if (existe(ISBN)) {
          System.out.println(livro.getISBN() + " existe");
        } else {
          this.cadastrar(livro);
          System.out.println("Adicionando livro - [ISBN: " + livro.getISBN() + "]");
        }
      }
      conversor.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (LJCException e) {
      e.printStackTrace();
    }
  }

}
