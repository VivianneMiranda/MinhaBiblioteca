package com.ufc.livro.repositorio.excecao;

public class LNCException extends Exception{
  private String ISBN;
  
  public LNCException(String ISBN){
    super("Exceção de Livro Não Encontrado [ISBN: " + ISBN +"]");
    this.ISBN = ISBN;
  }

  public String getISBN(){
    return this.ISBN;
  }
}