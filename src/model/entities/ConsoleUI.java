package model.entities;

import model.enums.*;
import model.exception.*;
import model.services.*;

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
            case REMOVE_CUSTOMER -> customerUI.removeCustomerFromList();
            case REMOVE_SERVICE -> serviceOrderUI.removeServiceFromCustomer();
            case LIST_SERVICES -> serviceOrderUI.listAllServices();
            case PROFIT -> serviceOrderUI.showTotalProfit();
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }
}