package entities;

import entities.enums.MenuOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final Scanner sc;
    private final ServiceManager serviceManager;

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

    private ServiceOrder inputServiceOrder() {
        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();
        System.out.println("Qual nome do modelo?");
        String model = sc.nextLine();
        System.out.println("Qual o preço do modelo?");
        Double modelPrice = sc.nextDouble();
        sc.nextLine();
        Customer customer = new Customer(name, model, modelPrice);

        LocalDateTime entryDate = LocalDateTime.now();
        return new ServiceOrder(customer, entryDate);
    }

    private void removeServiceOrder() {
        if (!serviceManager.hasServices()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        System.out.println("Digite o nome do cliente a ser removido:");
        String targetName = sc.nextLine();

        if (serviceManager.removeService(targetName)) {
            System.out.println("Serviço removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o serviço.");
        }
    }

    public List<ServiceOrder> getAvailableServices() {
        if (!serviceManager.hasServices()) {
            return null;
        }
        return serviceManager.getAllServices();
    }

    public void listAllServices() {
        List<ServiceOrder> serviceOrderList = getAvailableServices();
        if (serviceOrderList == null) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        serviceOrderList.forEach(s -> {
            System.out.printf("Horário: %s | Cliente: %s | Modelo: %s | Conserto: R$ %.2f%n",
                    s.getEntryDate().format(fmt),
                    s.getCustomer().getName(),
                    s.getCustomer().getModel(),
                    s.getRepairPrice());
        });
    }

    public void listExpensiveServices() {
        List<ServiceOrder> serviceOrderList = getAvailableServices();
        if (serviceOrderList == null) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        serviceOrderList.stream()
                .filter(s -> s.getRepairPrice() > 500)
                .forEach(s -> {
                    System.out.printf("Horário: %s | Cliente: %s | Modelo: %s | Conserto: R$ %.2f%n",
                            s.getEntryDate().format(fmt),
                            s.getCustomer().getName(),
                            s.getCustomer().getModel(),
                            s.getRepairPrice());
                });
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
                ServiceOrder generatedService = inputServiceOrder();
                serviceManager.addService(generatedService);
                break;

            case EXPENSIVE:
                listExpensiveServices();
                break;

            case LIST:
                listAllServices();
                break;

            case REMOVE:
                removeServiceOrder();
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