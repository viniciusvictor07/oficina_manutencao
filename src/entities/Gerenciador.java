package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerenciador {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private List<Servico> servicos;

    public Gerenciador() {
        this.servicos = new ArrayList<>();
    }

    public Servico gerenciarServico(Scanner sc) {
        System.out.println("Qual nome do cliente?");
        String nome = sc.nextLine();
        System.out.println("Qual email do cliente?");
        String email = sc.nextLine();
        System.out.println("Qual o telefone do cliente?");
        String telefone = sc.nextLine();
        Cliente cliente = new Cliente(nome, telefone, email);

        System.out.println("Qual nome do modelo?");
        String modelo = sc.nextLine();
        System.out.println("Qual o preço do modelo?");
        Double precoModelo = sc.nextDouble();
        sc.nextLine();
        LocalDateTime dataEntrada = LocalDateTime.now();
        return new Servico(cliente, modelo, precoModelo, dataEntrada);
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

        boolean conseguiuRemover = servicos.removeIf(s -> s.getCliente().getNome().equalsIgnoreCase(servicoRemovido));
        if (conseguiuRemover) {
            System.out.println("Serviço removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o serviço.");
        }
    }

    public void listarServicos() {
        if (servicos.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        this.servicos.forEach(s -> {
            System.out.printf("Horário: %s | Cliente: %s | Modelo: %s | Conserto: R$ %.2f%n",
                    s.getData().format(fmt),
                    s.getCliente().getNome(),
                    s.getModelo(),
                    s.getPrecoConserto());
        });
    }

    public void listarServicoCaro() {
        if (servicos.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        this.servicos.stream()
                .filter(s -> s.getPrecoConserto() > 500)
                .forEach(s -> {
                    System.out.printf("Horário: %s | Cliente: %s | Modelo: %s | Conserto: R$ %.2f%n",
                            s.getData().format(fmt),
                            s.getCliente().getNome(),
                            s.getModelo(),
                            s.getPrecoConserto());
                });
    }

    public void lucroTotal() {
        if (servicos.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        double valorTotalServico = 0;
        double valorTotalConserto = 0;
        for (Servico s : this.servicos) {
            valorTotalServico += s.getPrecoModelo();
            valorTotalConserto += s.getPrecoConserto();
        }
        double lucro = valorTotalConserto - valorTotalServico;
        System.out.printf("Valor total de serviços: R$ %.2f%nValor total de conserto: R$ %.2f%nLucro total: R$ %.2f%n", valorTotalServico, valorTotalConserto, lucro);
    }
}

