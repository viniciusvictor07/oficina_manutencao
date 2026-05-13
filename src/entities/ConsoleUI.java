package entities;

import entities.enums.MenuOption;
import entities.enums.VehicleOption;

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
            case REGISTER -> {
                ServiceOrder pendingService = inputServiceOrder();
                if (pendingService != null) {
                    addServiceOrder(pendingService);
                }
            }
            case EXPENSIVE -> listExpensiveServices();
            case LIST -> listAllServices();
            case REMOVE -> removeServiceOrder();
            case PROFIT -> showTotalProfit();
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    public void listAllServices() {
        List<ServiceOrder> serviceOrderList = getAvailableServices();
        if (serviceOrderList == null) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        serviceOrderList.forEach(this::printService);
    }

    public void listExpensiveServices() {
        List<ServiceOrder> serviceOrderList = getAvailableServices();
        if (serviceOrderList == null) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        serviceOrderList.stream()
                .filter(s -> s.getRepairPrice() > 500)
                .forEach(this::printService);
    }

    public void showTotalProfit() {
        double totalModelsPrice = serviceManager.getTotalModelPrices();
        double totalRepairsPrice = serviceManager.getTotalRepairPrices();
        double totalProfit = totalRepairsPrice - totalModelsPrice;

        System.out.printf("Valor total de serviços: R$ %.2f%nValor total de conserto: R$ %.2f%nLucro total: R$ %.2f%n",
                totalModelsPrice, totalRepairsPrice, totalProfit);
    }

    private ServiceOrder inputServiceOrder() {
        System.out.println("Qual o tipo do veículo?");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        int typedVehicleOption = sc.nextInt();
        sc.nextLine();

        String modelType;
        LocalDateTime now = LocalDateTime.now();
        VehicleOption selectedVehicleOption = VehicleOption.searchByCode(typedVehicleOption);

        if (selectedVehicleOption == null) {
            System.out.println("Opção inválida! Tente novamente.");
            return null;
        }
        switch (selectedVehicleOption) {
            case CAR -> {
                modelType = "Carro";
            }
            case MOTORCYCLE -> {
                modelType = "Motocicleta";
            }
            default -> {
                System.out.println("Opção inválida! Tente novamente.");
                return null;
            }
        }

        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();

        System.out.println("Qual o preço do veículo?");
        Double modelPrice = sc.nextDouble();
        sc.nextLine();

        Customer customer = new Customer(name, modelType, modelPrice);
        ServiceOrder pendingService = null;

        switch (selectedVehicleOption) {
            case CAR -> pendingService = new CarService(customer, now);
            case MOTORCYCLE -> pendingService = new MotoService(customer, now);
        }
        return pendingService;
    }

    private void addServiceOrder(ServiceOrder pendingService) {
        if (serviceManager.addService(pendingService)) {
            System.out.println("Serviço registrado com sucesso!");
        } else {
            System.out.println("Erro ao registrar serviço.");
        }
    }

    private void removeServiceOrder() {
        if (getAvailableServices() == null) {
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

    private List<ServiceOrder> getAvailableServices() {
        if (!serviceManager.hasServices()) {
            return null;
        }
        return serviceManager.getAllServices();
    }

    private void printService(ServiceOrder s) {
        System.out.printf("Horário: %s | Cliente: %s | Veículo: %s | Conserto: R$ %.2f%n",
                s.getEntryDate().format(fmt),
                s.getCustomer().getName(),
                s.getCustomer().getModel(),
                s.getRepairPrice());
    }
}