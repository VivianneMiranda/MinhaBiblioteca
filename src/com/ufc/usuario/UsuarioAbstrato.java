package com.ufc.usuario;

import java.io.Serializable;
import java.util.Date;

public abstract class UsuarioAbstrato implements Serializable{
  protected Long id;
  protected String nome;
  protected String matricula;
  protected String senha;
  protected String email;
  protected String telefone;

  public UsuarioAbstrato(String nome, String email, String senha, String matricula, String telefone){
    setEmail(email);
    setNome(nome);
    setSenha(senha);
    setMatricula(matricula);
    setTelefone(telefone);
    this.id = createId();
  }

  public String getNome(){
    return this.nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public String getMatricula(){
    return this.matricula;
  }

  public void setMatricula(String matricula){
    this.matricula = matricula;
  }

public String getSenha(){
    return this.senha;
  }

  public void setSenha(String senha){
    this.senha = senha;
  }

public String getEmail(){
    return this.email;
  }

  public void setEmail(String email){
    this.email = email;
  }
    
  public String getTelefone(){
    return this.telefone;
  }

  public void setTelefone(String telefone){
    this.telefone = telefone;
  }

  private Long createId(){
    Date Date=new Date();
    long milliSeconds = Date.getTime();
    /* String strLong = Long.toString(milliSeconds); */
    return milliSeconds;
  }
}
