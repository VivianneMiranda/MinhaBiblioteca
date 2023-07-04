package com.ufc.usuario;

public class Funcionario extends UsuarioAbstrato{
  protected String departamento;
  private Double taxaMulta = 0.5;

  public Funcionario(String matricula, String departamento) {
    super(matricula);
    this.departamento = departamento;
  }

  public void setMulta(Double valor){
    if (valor < 0.0){

    }else{
      this.taxaMulta = valor;
    }
    
  }

    public String getDepartamento(){
    return this.departamento;
  }
}
