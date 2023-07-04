package com.ufc.usuario.excecao;

public class VNException extends Exception {
  private String matricula;
  private double valor;

  public VNException(String matricula, double valor) {
    super("Exceção de Valor Negativo [Matricula: " + matricula + ", Valor: " + valor + "]");
    this.matricula = matricula;
    this.valor = valor;
  }

  public String getMatricula() {
    return this.matricula;
  }

  public double getValor() {
    return this.valor;
  }
}