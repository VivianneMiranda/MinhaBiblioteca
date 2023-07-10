package com.ufc.usuario;

import com.ufc.usuario.excecao.VNException;

import java.io.Serializable;

public class Aluno extends UsuarioAbstrato implements Serializable {
  private static final long serialVersionUID = 1L;

  private String tipo;
  private String curso;
  private Double taxaMulta = 0.2;

  public Aluno(String nome, String email, String senha, String matricula, String telefone, String curso) {
    super(nome,email,senha,matricula,telefone);
    this.curso = curso;
    this.tipo = "aluno";
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

  public void setCurso(String curso) {
    this.curso = curso;
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
