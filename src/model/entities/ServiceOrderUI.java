package model.entities;

import model.enums.TaxOption;
import model.enums.VehicleOption;
import model.exception.DomainException;
import model.services.*;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ServiceOrderUI {
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final Scanner sc;
    private final ServiceManager serviceManager;
    private final CustomerUI customerUI;

    public ServiceOrderUI(ServiceManager serviceManager, Scanner sc, CustomerUI customerUI) {
        this.sc = sc;
        this.serviceManager = serviceManager;
        this.customerUI = customerUI;
    }

    public void registerServiceFlow() {
        Customer selectedCustomer = customerUI.selectCustomer();
        if (selectedCustomer == null) {
            return;
        }
        try {
            ServiceOrder pendingService = inputServiceOrder(selectedCustomer);
            addServiceToCustomer(pendingService, selectedCustomer);
            serviceManager.registerServiceToGlobalOrders(pendingService);
        } catch (DomainException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
    }

    private ServiceOrder inputServiceOrder(Customer selectedCustomer) {
        VehicleOption selectedVehicleOption = getVehicleOption();
        if (selectedVehicleOption == null) {
            System.out.println("Opção inválida! Tente novamente.");
            return null;
        }
        System.out.println("Qual o modelo do veículo?");
        String vehicleModel = sc.nextLine();

        System.out.println("Qual o preço base?");
        double baseValue = sc.nextDouble();
        sc.nextLine();

        if (baseValue <= 0) {
            throw new DomainException("O valor base deve ser maior que 0.");
        }

        TaxManager adjustment = getTaxAdjustment();
        return createServiceOrder(selectedVehicleOption, vehicleModel, baseValue, selectedCustomer, adjustment);
    }

    private VehicleOption getVehicleOption() {
        System.out.println("Qual o tipo do veículo?");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");

        VehicleOption selectedVehicleOption = VehicleOption.searchByCode(sc.nextInt());
        sc.nextLine();

        return selectedVehicleOption;
    }

    private TaxManager getTaxAdjustment() {
        System.out.println("Qual o ajuste de taxa?");
        System.out.println("1 - Sem Ajuste (Padrão)");
        System.out.println("2 - Desconto Black Friday (10% de desconto)");
        System.out.println("3 - Taxa de Urgência (+ R$ 50)");
        System.out.println();
        int taxOption = sc.nextInt();

        TaxOption selectedTax = TaxOption.searchByCode(taxOption);
        return switch (selectedTax) {
            case STANDARD -> new NoAdjustment();
            case BLACK_FRIDAY -> new BlackFridayDiscount();
            case URGENCY -> new UrgencyFee();
        };
    }

    private ServiceOrder createServiceOrder(VehicleOption option, String model, double value, Customer customer, TaxManager tax) {
        return switch (option) {
            case CAR -> new CarService(model, value, customer, tax);
            case MOTORCYCLE -> new MotoService(model, value, customer, tax);
        };
    }

    private void addServiceToCustomer(ServiceOrder pendingService, Customer selectedCustomer) {
        if (pendingService != null && selectedCustomer.addServiceOrder(pendingService)) {
            System.out.println("Serviço registrado com sucesso!");
        } else {
            System.out.println("Erro ao registrar serviço.");
        }
    }

    public void removeServiceFromCustomer() {
        Customer selectedCustomer = customerUI.selectCustomer();
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
                System.out.printf("--------%s--------%n", c.getCustomerName().toUpperCase());
                for (ServiceOrder s : c.getCustomerOrders()) showServices(s);
            }
        }
    }

    private void showServices(ServiceOrder s) {
        System.out.printf(
                "Horário: %s | Veículo: %s | Valor base: R$ %.2f | Conserto: R$ %.2f%n",
                s.getEntryDate().format(fmt),
                s.getVehicleModel(),
                s.getBaseValue(),
                s.getFinalRepairPrice()
        );
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
}
