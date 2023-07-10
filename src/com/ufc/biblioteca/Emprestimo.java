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
        this.dataDevolucao = LocalDate.now().minusDays(5);
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
        this.dataDevolucao = LocalDate.now().minusDays(5);
    }

    public long calcularDiasAtraso() {
        if (dataDevolucao.isBefore(dataEmprestimo)) {
            return ChronoUnit.DAYS.between(dataDevolucao, LocalDate.now());
        } else {
            return 0;
        }
    }

    public double calcularMulta() {
        double valorMultaPorDia = 0.0;

        if (calcularDiasAtraso()>0) {
            if(usuario instanceof Funcionario){
                valorMultaPorDia = ((Funcionario) usuario).getMulta();
            }
            if(usuario instanceof Aluno){
                valorMultaPorDia = ((Aluno) usuario).getMulta();
            }
            
            return valorMultaPorDia * calcularDiasAtraso();
        }
        
        return 0.0;
    }
}
