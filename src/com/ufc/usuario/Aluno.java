package com.ufc.usuario;

import com.ufc.usuario.excecao.VNException;

public class Aluno extends UsuarioAbstrato{
  private String curso;
  private Double taxaMulta = 0.2;

  public Aluno(String matricula, String curso) {
    super(matricula);
    this.curso = curso;
  }

  public void setMulta(Double valor) throws VNException{
    if (valor < 0.0){
      throw new VNException(this.matricula, valor);
    }
    
      this.taxaMulta = valor;

  }

  public String getCurso(){
    return this.curso;
  }

  public Double getMulta(){
    return this.taxaMulta;
  }
}
