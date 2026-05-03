package application;

import entities.Gerenciador;
import entities.Servico;


import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Gerenciador gerenciador = new Gerenciador();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENU OFICINA ---");
            System.out.println("1. Cadastrar Serviço");
            System.out.println("2. Listar Serviços");
            System.out.println("3. Remover Serviço");
            System.out.println("4. Lucro Total");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                Servico s = gerenciador.gerenciarServico(sc);
                gerenciador.addServico(s);
            }
            if (opcao == 2) {
                gerenciador.listarTodos();
            }
            if (opcao == 3) {
                gerenciador.removerServico(sc);
            }
            if (opcao == 4) {
                gerenciador.lucroTotal();
            }
            if (opcao == 0) {
                System.out.println("Programa finalizado.");
                continuar = false;
            }
        }
        sc.close();
    }
}