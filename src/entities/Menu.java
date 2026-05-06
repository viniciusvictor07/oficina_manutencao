package entities;

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
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 0) {
                System.out.println("Saindo...");
                break;
            } else {
                gerenciarServico(opcao);
            }
        }
    }

    public void gerenciarServico(int opcao) {
        switch (opcao) {
            case 1:
                Servico s = gerenciador.gerenciarServico(sc);
                gerenciador.addServico(s);
                break;

            case 2:
                gerenciador.listarClientes();
                break;

            case 3:
                gerenciador.removerServico(sc);
                break;

            case 4:
                gerenciador.lucroTotal();
                break;
            case 5:
                gerenciador.listarServicoCaro();
                break;

            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }
}
