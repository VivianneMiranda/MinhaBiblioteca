package com.ufc.usuario;

public class Aluno extends UsuarioAbstrato{
  private String curso;
  private Double taxaMulta = 0.2;

  public Aluno(String matricula, String curso) {
    super(matricula);
    this.curso = curso;
  }

  public void setMulta(Double valor){
    if (valor < 0.0){

    }else{
      this.taxaMulta = valor;
    }
  }

  public String getCurso(){
    return this.curso;
  }

  public Double getMulta(){
    return this.taxaMulta;
  }
}
