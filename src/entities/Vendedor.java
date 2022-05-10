package entities;

import java.io.Serializable;

public class Vendedor extends FuncionarioAbs implements Funcionario, Serializable {
    private Double salario;
    private Double valorVendas;

    public Vendedor() {

    }

    public Vendedor(String nome, Integer id, Double salBase, Double valorVendas) {
        super(nome, id, salBase);
        this.valorVendas = valorVendas;
    }

    public Double getSalario() {
        return salario;
    }

    public Double getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(Double valorVendas) {
        this.valorVendas = valorVendas;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setSalario() {
        this.salario = calcSalario();
    }

    // Calcula o salário, unindo o salário base e o valor da comissão
    @Override
    public Double calcSalario() {
        return getSalBase() + comissao();
    }

    // Calcula o valor da comissão baseado no valor de vendas
    @Override
    public Double comissao() {
        return valorVendas * 0.05;
    }

    @Override
    public String toString() {
        return "Vendedor, " + getId() + ", " + getNome() + ", Comissão: R$ " + String.format("%.2f", comissao()) + ", Salário: R$" + String.format("%.2f", salario);
    }
}
