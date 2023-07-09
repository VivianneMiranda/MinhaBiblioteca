package com.ufc.livro;

import java.io.Serializable;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String titulo;
    private String autor;
    private String ISBN;
    private String editora;
    private int anoPublicacao;
    private int quantidadeDisponivel;

    public Livro(String titulo, String autor, String ISBN, String editora, int anoPublicacao, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public boolean podeSerEmprestado() {
        if (quantidadeDisponivel <= 0 ) {
            System.out.println("Não há exemplares disponíveis deste livro.");
            return false;  
        }
        return true;
    }

    public void efetuarEmprestimo(){
        quantidadeDisponivel--;
        System.out.println("Livro emprestado com sucesso.");
    }

    public void efetuarDevolucao() {
        quantidadeDisponivel++;
        System.out.println("Livro devolvido com sucesso.");
    }
}
