package com.ufc.usuario;

import java.util.Date;

public abstract class UsuarioAbstrato {
  protected Long id;
  protected String nome;
  protected String matricula;
  protected String senha;
  protected String email;
  protected String telefone;

  public UsuarioAbstrato(String matricula){
    this.matricula = matricula;
    this.id = createId();
  }

  public void atualizarInformacoes(String nome, String email, String senha, String matricula, String telefone){
    this.email = email;
    this.nome = nome;
    this.senha = senha;
    this.matricula = matricula;
    this.telefone = telefone;
  }

  private Long createId(){
    Date Date=new Date();
    long milliSeconds = Date.getTime();
    String strLong = Long.toString(milliSeconds);
    System.out.println(strLong);
    return milliSeconds;
  }
}
