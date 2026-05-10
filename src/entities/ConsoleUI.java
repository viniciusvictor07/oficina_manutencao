package entities;

import entities.enums.MenuOption;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner sc;
    private ServiceManager serviceManager;

    public ConsoleUI(ServiceManager serviceManager, Scanner sc) {
        this.serviceManager = serviceManager;
        this.sc = sc;
    }

    public void displayMenu() {
        System.out.println("\n--- MENU OFICINA ---");
        System.out.println("1. Cadastrar Serviço");
        System.out.println("2. Listar Serviços mais caros");
        System.out.println("3. Listar Serviços");
        System.out.println("4. Remover Serviço");
        System.out.println("5. Lucro Total");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void process() {
        while (true) {
            displayMenu();
            int typedOption = sc.nextInt();
            sc.nextLine();

            MenuOption selectedOption = MenuOption.searchByCode(typedOption);
            if (selectedOption == null) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }
            if (selectedOption == MenuOption.EXIT) {
                System.out.println("Saindo...");
                break;
            } else {
                handleService(selectedOption);
            }
        }
    }

    public void handleService(MenuOption selectedOption) {
        switch (selectedOption) {
            case REGISTER:
                ServiceOrder generatedService = serviceManager.generateService(sc);
                serviceManager.addService(generatedService);
                break;

            case EXPENSIVE:
                serviceManager.listExpensiveServices();
                break;

            case LIST:
                serviceManager.listServices();
                break;

            case REMOVE:
                serviceManager.removeService(sc);
                break;

            case PROFIT:
                serviceManager.calculateTotalProfit();
                break;

            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }
}