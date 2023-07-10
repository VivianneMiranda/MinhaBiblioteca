package com.ufc.biblioteca;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ufc.livro.Livro;
import com.ufc.usuario.Aluno;
import com.ufc.usuario.Funcionario;
import com.ufc.usuario.UsuarioAbstrato;

public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public double calcularMulta() {
        double valorMultaPorDia = 0.0;
        
        if (dataDevolucao.isBefore(LocalDate.now())) {
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucao, LocalDate.now());
            System.out.println("teste" + diasAtraso);
            
            if(usuario instanceof Funcionario){
                valorMultaPorDia = ((Funcionario) usuario).getMulta();
            }
            if(usuario instanceof Aluno){
                valorMultaPorDia = ((Aluno) usuario).getMulta();
            }
            
            return valorMultaPorDia * diasAtraso;
        }
        
        return 0.0;
    }
}
