package entities;

import entities.enums.MenuOption;
import entities.enums.VehicleOption;

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
            case REGISTER_CUSTOMER -> {
                Customer pendingCustomer = inputCustomer();
                addCustomerToList(pendingCustomer);
            }

            case REGISTER_SERVICE -> {
                Customer selectedCustomer = selectCustomer();

                if (selectedCustomer == null) {
                    return;
                }

                ServiceOrder pendingService = inputServiceOrder(selectedCustomer);
                addServiceToCustomer(pendingService, selectedCustomer);
            }

            case REMOVE_CUSTOMER -> {
                Customer selectedCustomer = selectCustomer();
                if (selectedCustomer == null) {
                    return;
                }
                removeCustomerFromList(selectedCustomer);
            }
//
//            case REMOVE_SERVICE -> {
//            }
//
            case LIST_SERVICES -> listAllServices();
//            case PROFIT -> showTotalProfit();
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private Customer inputCustomer() {
        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();

        return new Customer(name);
    }

    private ServiceOrder inputServiceOrder(Customer selectedCustomer) {
        System.out.println("Qual o tipo do veículo?");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");

        VehicleOption selectedVehicleOption = VehicleOption.searchByCode(sc.nextInt());
        sc.nextLine();

        if (selectedVehicleOption == null) {
            System.out.println("Opção inválida! Tente novamente.");
            return null;
        }

        System.out.println("Qual o modelo do veículo?");
        String vehicleModel = sc.nextLine();

        System.out.println("Qual o preço da mão de obra?");
        Double baseValue = sc.nextDouble();
        sc.nextLine();

        return switch (selectedVehicleOption) {
            case CAR -> new CarService(vehicleModel, baseValue, selectedCustomer);
            case MOTORCYCLE -> new MotoService(vehicleModel, baseValue, selectedCustomer);
        };
    }

    private Customer selectCustomer() {
        List<Customer> customers = serviceManager.getAllCostumers();

        if (customers.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no sistema.");
            return null;
        }

        System.out.println("--- Selecione o Cliente ---");

        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, customers.get(i).getCustomerName());
        }

        System.out.print("Digite o número do cliente: ");

        int index = sc.nextInt() - 1;
        sc.nextLine();

        if (index >= 0 && index < customers.size()) {
            return customers.get(index);
        } else {
            System.out.println("Índice inválido!");
            return null;
        }
    }

    private void addCustomerToList(Customer pendingCustomer) {
        if (serviceManager.addCustomer(pendingCustomer)) {
            System.out.println("Cliente registrado com sucesso!");
        } else {
            System.out.println("Erro ao registrar serviço.");
        }
    }

    private void addServiceToCustomer(ServiceOrder pendingService, Customer selectedCustomer) {
        if (pendingService != null && selectedCustomer.addServiceOrder(pendingService)) {
            System.out.println("Serviço registrado com sucesso!");
        } else {
            System.out.println("Erro ao registrar serviço.");
        }
    }

    private void removeCustomerFromList(Customer selectedCustomer) {
        if (serviceManager.removeCustomer(selectedCustomer)) {
            System.out.println("Clente removido com sucesso!");
        } else {
            System.out.println("Falha ao remover cliente.");
        }
    }

    private void removeServiceOrder() {
//        if (getAvailableServices() == null) {
//            System.out.println("Não existe nenhum serviço aqui!");
//            return;
//        }
//
//        System.out.println("Digite o nome do cliente a ser removido:");
//        String targetName = sc.nextLine();
//
//        if (serviceManager.removeCustomer(targetName)) {
//            System.out.println("Serviço removido com sucesso!");
//        } else {
//            System.out.println("Não foi possível remover o serviço.");
//        }
    }

    public void listAllServices() {
        List<Customer> customersList = serviceManager.getAllCostumers();
        if (customersList.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        for (Customer c : customersList) {
            if (c.hasServices()) {
                System.out.println("Cliente: " + c.getCustomerName().toUpperCase());
                for (ServiceOrder s : c.getServiceOrders()) {
                    showServices(s);
                }
            }
        }
    }

    public void showTotalProfit() {
//        double totalModelsPrice = serviceManager.getTotalModelPrices();
//        double totalRepairsPrice = serviceManager.getTotalRepairPrices();
//        double totalProfit = totalRepairsPrice - totalModelsPrice;
//
//        System.out.printf(
//                "Valor total de serviços: R$ %.2f%nValor total de conserto: R$ %.2f%nLucro total: R$ %.2f%n",
//                totalModelsPrice,
//                totalRepairsPrice,
//                totalProfit
//        );
    }

    private void showServices(ServiceOrder s) {
        System.out.printf(
                "Horário: %s | Veículo: %s | Mão de Obra: R$ %.2f | Conserto: R$ %.2f%n",
                s.getEntryDate().format(fmt),
                s.getVehicleModel(),
                s.getBaseValue(),
                s.getRepairPrice()
        );
    }
}