package com.ufc.biblioteca.excecao;

public class ENException extends Exception{
  private String ISBN;
  private String matricula;
  
  public ENException(String ISBN, String matricula){
    super("Exceção de Livro Não Emprestado para o Usuário [ISBN: " + ISBN + "Matricula: " + matricula +"]");
    this.ISBN = ISBN;
    this.matricula = matricula;
  }

  public String getISBN(){
    return this.ISBN;
  }

  public String getMatricula(){
    return this.matricula;
  }
}