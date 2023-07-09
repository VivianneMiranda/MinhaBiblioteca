package com.ufc.biblioteca;

import java.time.LocalDate;

import com.ufc.livro.Livro;
import com.ufc.usuario.UsuarioAbstrato;

public class Emprestimo {
    private Livro livro;
    private UsuarioAbstrato usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, UsuarioAbstrato usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public UsuarioAbstrato getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
