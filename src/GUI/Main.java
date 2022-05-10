package GUI;

import entities.Funcionario;
import entities.Vendedor;
import exceptions.IdNaoEncontradoException;
import repositorio.Repositorio;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String menu = " -- GERENCIADOR DA FOLHA DE PAGAMENTO -- \n" +
                      "INSIRA UMA OPÇÃO ABAIXO: \n" +
                      "1 - Cadastrar um pagamento;\n" +
                      "2 - Consultar um pagamento pelo ID do funcionario;\n" +
                      "3 - Imprimir toda a folha de pagamento;\n" +
                      "4 - Encerrar programa.";

        Repositorio repositorio = null;

        // A seguir, se inicializa o repositório. Carregando se já houver um existente ou criando um novo se não existir.
        try {
            repositorio = Repositorio.carregarRepositorio();
        } catch (IOException e) {
            System.out.println("Falha ao carregar repositório!\n" +
                              "Criando um novo repositório...");
            repositorio = new Repositorio();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // A seguir, o menu:
        while(true) {
            System.out.println(menu);
            int opcao = sc.nextInt();
            sc.nextLine();
            if (opcao == 1) {
                System.out.println("~Cadastrando um novo pagamento.");
                System.out.println("Insira os dados do pagamento:");
                System.out.print("Nome do funcionário: ");
                String nome = sc.nextLine();
                System.out.print("ID do funcionário: ");
                int id = sc.nextInt();
                System.out.print("Salário base do funcionário: ");
                double salBase = sc.nextDouble();
                System.out.print("Valor em vendas do funcionário: ");
                double valVendas = sc.nextDouble();
                Funcionario funcionario = new Vendedor(nome, id, salBase, valVendas);
                funcionario.setSalario();
                repositorio.adicionar(funcionario);
                try {
                    repositorio.salvarRepositorio();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Não foi possível salvar o arquivo.");
                }
                System.out.println();
            } else if (opcao == 2 ) {
                System.out.println("~Consultando pagamento.");
                System.out.print("Insira o ID do funcionário: ");
                int id = sc.nextInt();
                try {
                    System.out.println(repositorio.consultar(id));
                } catch (IdNaoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println();
            } else if (opcao == 3) {
                System.out.println("~Imprimindo toda a folha de pagamento:");
                for (Funcionario f : repositorio.getRepositorio()) {
                    System.out.println(f);
                }
                System.out.println();
            } else if (opcao == 4) {
                System.out.println("~Encerrando...");
                break;
            }
        }

        sc.close();
    }
}
