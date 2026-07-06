package model.entities;

import model.enums.TaxOption;
import model.enums.VehicleOption;
import model.exception.DomainException;
import model.services.*;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            serviceManager.addService(pendingService);
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

        double baseValue = getBaseValue();
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

    public double getBaseValue() {
        System.out.println("Qual o preço base?");
        double baseValue = sc.nextDouble();
        sc.nextLine();

        if (baseValue <= 0) {
            throw new DomainException("O valor base deve ser maior que 0.");
        }
        return baseValue;
    }

    private ServiceOrder createServiceOrder(VehicleOption option, String model, double value, Customer customer, TaxManager tax) {
        return switch (option) {
            case CAR -> new CarService(model, value, customer, tax);
            case MOTORCYCLE -> new MotoService(model, value, customer, tax);
        };
    }



    private void displayGroupedServices(List<ServiceOrder> servicesList) {
        Map<Customer, List<ServiceOrder>> servicesByCustomer = servicesList.stream()
                .collect(Collectors.groupingBy(ServiceOrder::getCustomer));

        servicesByCustomer.forEach((customer, orders) -> {
            System.out.printf("----- %s -----\n", customer.getCustomerName().toUpperCase());
            orders.forEach(this::printServiceDetails);
        });
    }

    private void printServiceDetails(ServiceOrder s) {
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
                totalModelsPrice, totalRepairsPrice, totalProfit
        );
    }
}
