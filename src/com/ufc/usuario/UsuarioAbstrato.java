package com.ufc.usuario;

import java.io.Serializable;
import java.util.Base64;

public abstract class UsuarioAbstrato implements Serializable {
  private static final long serialVersionUID = 1L;

  protected String tipo;
  protected String nome;
  protected String matricula;
  protected String senha;
  protected String email;
  protected String telefone;

  public UsuarioAbstrato(String nome, String email, String senha, String matricula, String telefone) {
    setEmail(email);
    setNome(nome);
    setSenha(senha);
    setMatricula(matricula);
    setTelefone(telefone);
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMatricula() {
    return this.matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    byte[] senhaBytes = senha.getBytes();
    String senhaCodificada = Base64.getEncoder().encodeToString(senhaBytes);
    this.senha = senhaCodificada;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public boolean verificarSenha(String senha) {
    byte[] senhaArmazenadaBytes = Base64.getDecoder().decode(this.senha);
    String senhaArmazenada = new String(senhaArmazenadaBytes);
    return senha.equals(senhaArmazenada);
  }

  public abstract void setTipo(String tipo);

  public String getTipo(){
    return this.tipo;
  }
}
