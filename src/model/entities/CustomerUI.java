package model.entities;

import model.services.ServiceManager;

import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    ServiceManager serviceManager;
    Scanner sc;

    public CustomerUI(ServiceManager serviceManager, Scanner sc) {
        this.serviceManager = serviceManager;
        this.sc = sc;
    }

    public void registerCustomerFlow() {
        Customer pendingCustomer = inputCustomer();
        addCustomerToList(pendingCustomer);
    }

    private Customer inputCustomer() {
        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();

        return new Customer(name);
    }

    private void addCustomerToList(Customer pendingCustomer) {
        if (serviceManager.addCustomer(pendingCustomer)) {
            System.out.println("Cliente registrado com sucesso!");
        } else {
            System.out.println("Erro ao registrar serviço.");
        }
    }

    public void removeCustomerFromList() {
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

    public Customer selectCustomer() {
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
}
