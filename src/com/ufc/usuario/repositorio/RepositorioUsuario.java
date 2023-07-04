package com.ufc.usuario.repositorio;
import java.util.Vector;

import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.excecao.UJCException;

public class RepositorioUsuario {
  private Vector<UsuarioAbstrato> usuarios;

  public RepositorioUsuario(){
    this.usuarios = new Vector<UsuarioAbstrato>();
  }

  public void cadastrar(UsuarioAbstrato usuario) throws UJCException{
    if(this.existe(usuario.getMatricula())){
      throw new UJCException(usuario.getMatricula());
    }

    this.usuarios.add(usuario);
  }

  
  public UsuarioAbstrato procurar(String matricula) {
    for (UsuarioAbstrato usuario : this.usuarios) {
      if (usuario.getMatricula().equals(matricula)) {
        return usuario;
      }
    }
    return null;
  }

  public boolean existe(String matricula) {
    return matricula != null && this.procurar(matricula) != null;
  }
}
