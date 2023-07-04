package com.ufc.usuario.repositorio.excecao;

public class UJCException extends Exception{
  private String matricula;
  public UJCException(String matricula){
    super("Exceção de Usuário Já Cadastrado [Matricula: " + matricula +"]");
    this.matricula = matricula;
  }

  public String getMatricula(){
    return this.matricula;
  }
}
