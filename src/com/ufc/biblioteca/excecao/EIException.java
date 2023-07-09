package com.ufc.biblioteca.excecao;

public class EIException extends Exception{
  private String titulo;
  
  public EIException(String titulo){
    super("Exceção de Exemplar Indisponível [Título: " + titulo +"]");
    this.titulo = titulo;
  }

  public String getTitulo(){
    return this.titulo;
  }
}
