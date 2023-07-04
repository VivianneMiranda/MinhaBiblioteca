package com.ufc;

import com.ufc.usuario.Aluno;
import com.ufc.usuario.excecao.VNException;

public class Main {

    public static void main(String[] args) throws VNException {
        Aluno aluno = new Aluno("guilherme","teste@teste.com","asas", "28288-x", "919921912", "comp");
        System.out.println(aluno.getCurso());
        aluno.setMulta(0.4);
        System.out.println(aluno.getMulta());
    }
}
