package model.entities;

import model.enums.MenuOption;
import model.enums.VehicleOption;
import model.exception.DomainException;
import model.services.ServiceManager;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
            case REGISTER_CUSTOMER -> registerCustomerFlow();
            case REGISTER_SERVICE -> registerServiceFlow();
            case REMOVE_CUSTOMER -> removeCustomerFromList();
            case REMOVE_SERVICE -> removeServiceFromCustomer();
            case LIST_SERVICES -> listAllServices();
            case PROFIT -> showTotalProfit();
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private Customer inputCustomer() {
        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();

        return new Customer(name);
    }

    private void registerCustomerFlow() {
        Customer pendingCustomer = inputCustomer();
        addCustomerToList(pendingCustomer);
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
        double baseValue = sc.nextDouble();
        sc.nextLine();

        if (baseValue <= 0) {
            throw new DomainException("O valor da mão de obra deve ser maior que 0.");
        }
        return switch (selectedVehicleOption) {
            case CAR -> new CarService(vehicleModel, baseValue, selectedCustomer);
            case MOTORCYCLE -> new MotoService(vehicleModel, baseValue, selectedCustomer);
        };
    }

    private Customer selectCustomer() {
        List<Customer> customers = serviceManager.getAllCustomers();

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

    private void registerServiceFlow() {
        Customer selectedCustomer = selectCustomer();
        if (selectedCustomer == null) {
            return;
        }
        try {
            ServiceOrder pendingService = inputServiceOrder(selectedCustomer);
            addServiceToCustomer(pendingService, selectedCustomer);
        } catch (DomainException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
    }

    private void removeCustomerFromList() {
        Customer selectedCustomer = selectCustomer();
        if (selectedCustomer == null) {
            return;
        }

        if (serviceManager.removeCustomer(selectedCustomer)) {
            System.out.println("Clente removido com sucesso!");
        } else {
            System.out.println("Falha ao remover cliente.");
        }
    }

    private void removeServiceFromCustomer() {
        Customer selectedCustomer = selectCustomer();
        if (selectedCustomer == null || !selectedCustomer.hasServices()) {
            System.out.println("Este cliente não existe ou não possui nenhum serviço cadastrado.");
            return;
        }

        List<ServiceOrder> services = selectedCustomer.getCustomerOrders();
        System.out.printf("\n--- SERVIÇOS DO CLIENTE: %s---%n", selectedCustomer.getCustomerName().toUpperCase());
        for (int i = 0; i < services.size(); i++) {
            System.out.printf("%d. ", i + 1);
            showServices(services.get(i));
        }
        try {
            System.out.print("\nDigite o número do serviço que deseja remover: ");
            int targetIndex = sc.nextInt() - 1;
            sc.nextLine();
            services.remove(targetIndex);
            System.out.println("Serviço removido com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Opção de serviço inválida! Digite apenas números!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice de serviço inválido!");
        }
    }

    public void listAllServices() {
        List<Customer> customersList = serviceManager.getAllCustomers();
        if (customersList.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        for (Customer c : customersList) {
            if (c.hasServices()) {
                System.out.println("Cliente: " + c.getCustomerName().toUpperCase());
                for (ServiceOrder s : c.getCustomerOrders()) showServices(s);
                }
            }
        }

    public void showTotalProfit() {
        double totalModelsPrice = serviceManager.getTotalBaseValue();
        double totalRepairsPrice = serviceManager.getTotalRepairValue();
        double totalProfit = totalRepairsPrice - totalModelsPrice;

        System.out.printf(
                "Valor total de serviços: R$ %.2f%nValor total de conserto: R$ %.2f%nLucro total: R$ %.2f%n",
                totalModelsPrice,
                totalRepairsPrice,
                totalProfit
        );
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