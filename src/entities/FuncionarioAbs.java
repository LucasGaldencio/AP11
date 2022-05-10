package entities;

import java.io.Serializable;

public abstract class FuncionarioAbs implements Serializable {
    private String nome;
    private Integer id;
    private Double salBase;

    public FuncionarioAbs() {

    }

    public FuncionarioAbs(String nome, Integer id, Double salBase) {
        this.nome = nome;
        this.id = id;
        this.salBase = salBase;
    }

    public Integer getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSalBase() {
        return salBase;
    }

    public void setSalBase(Double salBase) {
        this.salBase = salBase;
    }
}
