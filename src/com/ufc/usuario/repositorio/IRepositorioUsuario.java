package com.ufc.usuario.repositorio;

import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;

import java.util.List;

public interface IRepositorioUsuario {
  public void cadastrar(UsuarioAbstrato usuario) throws UJCException;

  public void remover(String matricula) throws UNCException;

  public void atualizarInformacoes(String matricula, String nome, String email, String senha, String telefone)
          throws UNCException;

  public UsuarioAbstrato buscar(String matricula);

  public UsuarioAbstrato recuperar(String matricula) throws UJCException;

  public boolean existe(String matricula);

  public List<UsuarioAbstrato> listar();

  public int tamanho();
}