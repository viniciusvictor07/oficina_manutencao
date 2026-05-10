package entities;

import entities.enums.OpcaoMenu;

import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private Gerenciador gerenciador;

    public Menu(Gerenciador gerenciador, Scanner sc) {
        this.gerenciador = gerenciador;
        this.sc = sc;
    }

    public void exibirMenu() {
        System.out.println("\n--- MENU OFICINA ---");
        System.out.println("1. Cadastrar Serviço");
        System.out.println("2. Listar Serviços");
        System.out.println("3. Remover Serviço");
        System.out.println("4. Lucro Total");
        System.out.println("5. Listar Serviços mais caros");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void processar() {
        while (true) {
            exibirMenu();
            int opcaoDigitada = sc.nextInt();
            sc.nextLine();

            OpcaoMenu opcaoSelecionada = OpcaoMenu.buscarporCodigo(opcaoDigitada);
            if (opcaoSelecionada == null) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }
            if (opcaoSelecionada == OpcaoMenu.SAIR) {
                System.out.println("Saindo...");
                break;
            } else {
                gerenciarServico(opcaoSelecionada);
            }
        }
    }

    public void gerenciarServico(OpcaoMenu opcaoSelecionada) {
        switch (opcaoSelecionada) {
            case CADASTRAR:
                Servico s = gerenciador.gerenciarServico(sc);
                gerenciador.addServico(s);
                break;

            case LISTAR:
                gerenciador.listarServicos();
                break;

            case REMOVER:
                gerenciador.removerServico(sc);
                break;

            case LUCRO:
                gerenciador.lucroTotal();
                break;
            case MAIS_CAROS:
                gerenciador.listarServicoCaro();
                break;

            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }
}
