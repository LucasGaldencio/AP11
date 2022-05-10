package repositorio;

import entities.Funcionario;
import exceptions.IdNaoEncontradoException;

import java.io.*;
import java.util.ArrayList;

public class Repositorio implements Serializable {
    private ArrayList<Funcionario> repositorio;
    private static String arquivo = "folhadepagamento.dat";

    public Repositorio() {
        this.repositorio = new ArrayList<>();
    }

    public ArrayList<Funcionario> getRepositorio() {
        return repositorio;
    }

    // Pesquisa e retorna o funcionário desejado
    public Funcionario consultar(int id) throws IdNaoEncontradoException {
        for (Funcionario f : repositorio) {
            if (f.getId() == id) {
                return f;
            }
        }
        throw new IdNaoEncontradoException("ID não localizado!");
    }

    public void adicionar(Funcionario funcionario) { // Adiciona usuário ao repositório
        repositorio.add(funcionario);
    }

    // Salva o repositório no arquivo
    public void salvarRepositorio() throws IOException {
        FileOutputStream fout = new FileOutputStream(arquivo);
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        oout.writeObject(this);
        oout.close();
    }

    // Carrega o repositório do arquivo
    public static Repositorio carregarRepositorio() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(arquivo);
        ObjectInputStream oin = new ObjectInputStream(fin);
        Repositorio rep = (Repositorio) oin.readObject();
        oin.close();
        return rep;
    }
}
