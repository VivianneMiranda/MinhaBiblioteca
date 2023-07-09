package com.ufc.usuario;

import com.ufc.usuario.excecao.VNException;

public class Funcionario extends UsuarioAbstrato{
  private String tipo;
  private String departamento;
  private Double taxaMulta = 0.5;

  public Funcionario(String nome, String email, String senha, String matricula, String telefone, String departamento) {
    super(nome,email,senha,matricula,telefone);
    this.departamento = departamento;
    this.tipo = "funcionario";
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

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
}
