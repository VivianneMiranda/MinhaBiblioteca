package com.ufc.usuario.repositorio;

import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;

public interface IRepositorioUsuario {
  public void cadastrarFuncionario(Funcionario usuario) throws UJCException;

  public void cadastrarAluno(Aluno usuario) throws UJCException;

  public void remover(String matricula) throws UNCException;

  public void atualizarInformacoes(String matricula, String nome, String email, String senha, String telefone)
      throws UNCException;

  public UsuarioAbstrato buscar(String matricula);

  public boolean existe(String matricula);
}