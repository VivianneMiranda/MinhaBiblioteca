package com.ufc.biblioteca;

import com.ufc.usuario.UsuarioAbstrato;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RepositorioEmprestimo {
    private Vector<Emprestimo> emprestimos;

    public RepositorioEmprestimo() {
        this.emprestimos = new Vector<Emprestimo>();
        desserializar();
    }

    public void cadastrarEmprestimo(Emprestimo emprestimo)  {
        this.emprestimos.add(emprestimo);
        serializar();
    }

    public void removerEmprestimo(Emprestimo emprestimo)  {
        this.emprestimos.remove(emprestimo);
        serializar();
    }

    public List<Emprestimo> listar() {
        System.out.println(new ArrayList<>(emprestimos));
        return new ArrayList<>(emprestimos);
    }

    public int tamanho() {

        return this.emprestimos.size();
    }

    private void serializar() {
        File diretorio = new File("./arquivo");
        if (!diretorio.isDirectory()) {
            diretorio.mkdir();
        }
        File arquivo = new File(diretorio, "emprestimo.bin");
        try {
            FileOutputStream gravador = new FileOutputStream(arquivo);
            ObjectOutputStream conversor = new ObjectOutputStream(gravador);
            conversor.writeObject(emprestimos);
            conversor.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void desserializar() {
        System.out.println("Entrando na função desserializar RepositorioEmprestimo");
        String pathDir = "./arquivo";
        File diretorio = new File(pathDir);

        if (!diretorio.isDirectory()) {
            if (diretorio.mkdir()) {
                System.out.println("Diretório criado com sucesso.");
            } else {
                System.out.println("Não foi possível criar o diretório.");
                return;
            }
        }

        String pathArquivo = pathDir + "/" + "emprestimo.bin";
        File arquivo = new File(pathArquivo);

        if (arquivo.length() == 0) {
            System.out.println("O arquivo emprestimo.bin está vazio.");
            return;
        }

        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo emprestimo.bin criado com sucesso.");
                } else {
                    System.out.println("Não foi possível criar o arquivo emprestimo.bin.");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        try {
            FileInputStream leitor = new FileInputStream(pathArquivo);
            ObjectInputStream conversor = new ObjectInputStream(leitor);
            Vector<Emprestimo> emprestimos = (Vector<Emprestimo>) conversor.readObject();
            for (Emprestimo emprestimo : emprestimos) {
                    this.cadastrarEmprestimo(emprestimo);
                    System.out.println("Adicionando Emprestimo - [Usuário: " + emprestimo.getUsuario().getNome() + "]");
            }
            conversor.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
