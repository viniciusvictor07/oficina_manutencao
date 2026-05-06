package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerenciador {
    private List<Servico> servicos;

    public Gerenciador() {
        this.servicos = new ArrayList<>();
    }

    public Servico gerenciarServico(Scanner sc) {
        System.out.println("Qual nome do cliente?");
        String cliente = sc.nextLine();
        System.out.println("Qual nome do modelo?");
        String modelo = sc.nextLine();
        System.out.println("Qual o preço do modelo?");
        Double precoModelo = sc.nextDouble();
        sc.nextLine();
        return new Servico(cliente, modelo, precoModelo);
    }

    public void addServico(Servico servicoAdicionado) {
        double precoConserto = servicoAdicionado.getPrecoModelo() * 1.1;
        servicoAdicionado.setPrecoConserto(precoConserto);
        this.servicos.add(servicoAdicionado);
        System.out.println("Serviço registrado com sucesso!");
    }

    public void removerServico(Scanner sc) {
        if (this.servicos.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        System.out.println("Digite o nome do cliente a ser removido:");
        String servicoRemovido = sc.nextLine();

        boolean conseguiuRemover = servicos.removeIf(s -> s.getCliente().equalsIgnoreCase(servicoRemovido));
        if (conseguiuRemover) {
            System.out.println("Serviço removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o serviço.");
        }
    }

    public void listarClientes() {
        if (servicos.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        this.servicos.stream()
                .sorted((s1, s2) -> s1.getCliente().compareToIgnoreCase(s2.getCliente()))
                .forEach(s -> {
                    System.out.printf("Nome do cliente: %s | Nome do modelo: %s | Preço do conserto: R$ %.2f%n",
                            s.getCliente(), s.getModelo(), s.getPrecoConserto());
                });
    }

    public void listarPrecos(Scanner sc) {

    }

    public void lucroTotal() {
        if (servicos.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        double lucro = 0;
        for (Servico s : this.servicos) {
            lucro += s.getPrecoConserto() - s.getPrecoModelo();
        }
        System.out.printf("Lucro total: R$ %.2f%n", lucro);
    }
}

