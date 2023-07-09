package com.ufc.livro.repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.excecao.LJCException;
import com.ufc.livro.repositorio.excecao.LNCException;

public class LivroRepositorio {

  private Vector<Livro> livros;

  public LivroRepositorio() {
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
    for (Livro livro : livros) {
      if (livro.getAutor().equals(autor)) {
        livrosDoAutor.add(livro);
      }
    }
    return livrosDoAutor;
  }

  public Livro buscarLivroPorISBN(String isbn) {
    for (Livro livro : livros) {
      if (livro.getISBN().equals(isbn)) {
        return livro;
      }
    }
    return null;
  }

  public boolean existe(String ISBN) {
    return ISBN != null && this.buscarLivroPorISBN(ISBN) != null;
  }

  private void serializar() {
    String pathDir = "./arquivo";
    File diretorio = new File(pathDir);
    if (!diretorio.isDirectory()) {
      diretorio.mkdir();
    }
    try {
      String pathArquivo = pathDir + "/" + "livros.bin";
      FileOutputStream gravador = new FileOutputStream(pathArquivo);
      ObjectOutputStream conversor = new ObjectOutputStream(gravador);
      conversor.writeObject(this.livros);
      conversor.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void desserializar() {
    String pathDir = "./arquivo";
    File diretorio = new File(pathDir);
    if (!diretorio.isDirectory()) {
      diretorio.mkdir();
    }

    try {
      String pathArquivo = pathDir + "/" + "livros.bin";
      FileInputStream leitor = new FileInputStream(pathArquivo);
      ObjectInputStream conversor = new ObjectInputStream(leitor);
      this.livros = (Vector<Livro>) conversor.readObject();
      for (Livro livro : this.livros) {
        this.livros.add(livro);
      }
      conversor.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

}
