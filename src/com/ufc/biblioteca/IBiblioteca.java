package com.ufc.biblioteca;

import com.ufc.biblioteca.excecao.CIException;
import com.ufc.biblioteca.excecao.EIException;
import com.ufc.livro.Livro;
import com.ufc.livro.repositorio.excecao.LJCException;
import com.ufc.livro.repositorio.excecao.LNCException;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;

import java.util.Collection;
import java.util.List;

public interface IBiblioteca {

    public boolean login(String matricula, String senha) throws CIException;

    public void adicionarLivro(Livro livro) throws LJCException;

    public void removerLivro(String ISBN) throws LNCException;

    public void adicionarUsuario(UsuarioAbstrato usuario) throws UJCException;

    public void removerUsuario(String matricula) throws UNCException;

    public boolean EmprestarLivro(Livro livro, UsuarioAbstrato usuario);

    public boolean devolverLivro(Livro livro, UsuarioAbstrato usuario);

    public double ConsultarMulta(UsuarioAbstrato usuario);

}
