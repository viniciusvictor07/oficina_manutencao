package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceManager {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private List<ServiceOrder> serviceOrders;

    public ServiceManager() {
        this.serviceOrders = new ArrayList<>();
    }

    public ServiceOrder generateService(Scanner sc) {
        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();
        System.out.println("Qual email do cliente?");
        String email = sc.nextLine();
        System.out.println("Qual o telefone do cliente?");
        String phone = sc.nextLine();
        Customer customer = new Customer(name, phone, email);

        System.out.println("Qual nome do modelo?");
        String model = sc.nextLine();
        System.out.println("Qual o preço do modelo?");
        Double modelPrice = sc.nextDouble();
        sc.nextLine();

        LocalDateTime entryDate = LocalDateTime.now();
        return new ServiceOrder(customer, model, modelPrice, entryDate);
    }

    public void addService(ServiceOrder serviceOrderToAdd) {
        double repairPrice = serviceOrderToAdd.getModelPrice() * 1.1;
        serviceOrderToAdd.setRepairPrice(repairPrice);
        this.serviceOrders.add(serviceOrderToAdd);
        System.out.println("Serviço registrado com sucesso!");
    }

    public void removeService(Scanner sc) {
        if (this.serviceOrders.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        System.out.println("Digite o nome do cliente a ser removido:");
        String targetName = sc.nextLine();

        boolean removed = serviceOrders.removeIf(s -> s.getCustomer().getName().equalsIgnoreCase(targetName));

        if (removed) {
            System.out.println("Serviço removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o serviço.");
        }
    }

    public void listServices() {
        if (serviceOrders.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        this.serviceOrders.forEach(s -> {
            System.out.printf("Horário: %s | Cliente: %s | Modelo: %s | Conserto: R$ %.2f%n",
                    s.getEntryDate().format(fmt),
                    s.getCustomer().getName(),
                    s.getModel(),
                    s.getRepairPrice());
        });
    }

    public void listExpensiveServices() {
        if (serviceOrders.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        this.serviceOrders.stream()
                .filter(s -> s.getRepairPrice() > 500)
                .forEach(s -> {
                    System.out.printf("Horário: %s | Cliente: %s | Modelo: %s | Conserto: R$ %.2f%n",
                            s.getEntryDate().format(fmt),
                            s.getCustomer().getName(),
                            s.getModel(),
                            s.getRepairPrice());
                });
    }

    public void calculateTotalProfit() {
        if (serviceOrders.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }

        double totalModelValue = 0;
        double totalRepairValue = 0;
        for (ServiceOrder s : this.serviceOrders) {
            totalModelValue += s.getModelPrice();
            totalRepairValue += s.getRepairPrice();
        }
        double profit = totalRepairValue - totalModelValue;
        System.out.printf("Valor total de serviços: R$ %.2f%nValor total de conserto: R$ %.2f%nLucro total: R$ %.2f%n",
                totalModelValue, totalRepairValue, profit);
    }
}