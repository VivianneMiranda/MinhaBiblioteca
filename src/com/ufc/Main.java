package com.ufc;

import com.ufc.usuario.Aluno;

public class Main {

    public static void main(String[] args) {
        Aluno aluno = new Aluno("asdas", "eng");
        System.out.println(aluno.getCurso());
        aluno.setMulta(0.4);
        System.out.println(aluno.getMulta());
        Aluno aluno2 = new Aluno("dsds", "eng");
        System.out.println(aluno2.getMulta());
    }
}
