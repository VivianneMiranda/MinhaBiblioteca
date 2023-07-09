package com.ufc.usuario.repositorio.excecao;

public class UNCException extends Exception{
  private String matricula;
  public UNCException(String matricula){
    super("Exceção de Usuário Não Encontrado [Matricula: " + matricula +"]");
    this.matricula = matricula;
  }

  public String getMatricula(){
    return this.matricula;
  }
}
