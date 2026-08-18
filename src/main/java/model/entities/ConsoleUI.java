package model.entities;

import model.services.*;
import model.enums.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner sc;
    private final CustomerUI customerUI;
    private final ServiceOrderUI serviceOrderUI;

    public ConsoleUI(ServiceManager serviceManager, Scanner sc) {
        this.sc = sc;
        this.customerUI = new CustomerUI(serviceManager, sc);
        this.serviceOrderUI = new ServiceOrderUI(serviceManager, sc, this.customerUI);
    }

    public void process() {
        while (true) {
            displayMenu();
            MenuOption selectedOption = null;
            try {
                selectedOption = MenuOption.searchByCode(readInt(sc));
            } catch (InputMismatchException e) {
                sc.nextLine();
            }

            if (selectedOption == null) {
                System.out.println("Opção inválida! Tente novamente.");
            } else if (selectedOption == MenuOption.EXIT) {
                System.out.println("Saindo...");
                break;
            } else {
                handleService(selectedOption);
            }
        }
    }

    public static int readInt(Scanner sc) {
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public void displayMenu() {
        System.out.println("\n--- MENU OFICINA ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Serviço");
        System.out.println("3. Listar Serviços");
        System.out.println("4. Remover Cliente");
        System.out.println("5. Remover Serviço");
        System.out.println("6. Lucro Total");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void handleService(MenuOption selectedOption) {
        switch (selectedOption) {
            case REGISTER_CUSTOMER -> customerUI.registerCustomerFlow();
            case REGISTER_SERVICE -> serviceOrderUI.registerServiceFlow();
            case REMOVE_CUSTOMER -> customerUI.removeCustomerFlow();
            case REMOVE_SERVICE -> serviceOrderUI.removeServiceFlow();
            case LIST_SERVICES -> serviceOrderUI.listAllServicesFlow();
            case PROFIT -> serviceOrderUI.totalProfitFlow();
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }
}
