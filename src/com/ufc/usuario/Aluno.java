package com.ufc.usuario;

public class Aluno extends UsuarioAbstrato{
  protected String curso;
  private Double taxaMulta;

  public Aluno(String matricula, String curso) {
    super(matricula);
    this.curso = curso;
  }

  public void setMulta(Double valor){
    this.taxaMulta = valor;
  }

  public String getCurso(){
    return this.curso;
  }
}
