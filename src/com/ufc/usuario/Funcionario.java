package com.ufc.usuario;

import com.ufc.usuario.excecao.VNException;

public class Funcionario extends UsuarioAbstrato{
  private String departamento;
  private Double taxaMulta = 0.5;

  public Funcionario(String matricula, String departamento) {
    super(matricula);
    this.departamento = departamento;
  }

  public void setMulta(Double valor) throws VNException{
    if (valor < 0.0){
      throw new VNException(this.matricula, valor);
    }
      this.taxaMulta = valor;
  }

    public String getDepartamento(){
    return this.departamento;
  }

  public Double getMulta(){
    return this.taxaMulta;
  }
}
