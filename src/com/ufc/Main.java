package com.ufc;

import java.io.IOException;

import com.ufc.gui.MinhaBibliotecaGUI;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;
import com.ufc.usuario.excecao.VNException;
import com.ufc.usuario.repositorio.RepositorioUsuario;
import com.ufc.usuario.repositorio.excecao.UJCException;
import com.ufc.usuario.repositorio.excecao.UNCException;

public class Main {

    public static void main(String[] args) throws VNException, UJCException, UNCException, IOException {
        RepositorioUsuario repo = new RepositorioUsuario();
        new MinhaBibliotecaGUI();
        Aluno aluno = new Aluno("null", "null", "null", "null", "null", "null");
        repo.cadastrar(aluno);

    }
}
