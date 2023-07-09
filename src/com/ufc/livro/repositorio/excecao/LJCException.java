package com.ufc.livro.repositorio.excecao;

public class LJCException extends Exception{
  private String ISBN;
  
  public LJCException(String ISBN){
    super("Exceção de Livro Já Cadastrado [ISBN: " + ISBN +"]");
    this.ISBN = ISBN;
  }

  public String getISBN(){
    return this.ISBN;
  }
}