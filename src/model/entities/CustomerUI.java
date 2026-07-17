package model.entities;

import model.exception.DomainException;
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
        try {
            Customer pendingCustomer = inputCustomer();
            if (serviceManager.addCustomer(pendingCustomer)) {
                System.out.println("Cliente registrado com sucesso no banco e na memória!");
            } else {
                System.out.println("Erro ao registrar na memória.");
            }
        } catch (DomainException e) {
            System.out.println("Falha ao salvar no banco de dados: " + e.getMessage());
        }
    }

    public void removeCustomerFlow() {
        Customer selectedCustomer = selectCustomer();
        if (selectedCustomer == null) {
            return;
        }

        if (serviceManager.removeCustomer(selectedCustomer)) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Falha ao remover cliente.");
        }
    }

    public Customer selectCustomer() {
        List<Customer> customersList = serviceManager.getAllCustomers();

        if (customersList.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no sistema.");
            return null;
        }

        System.out.println("--- Selecione o Cliente ---");
        int[] i = {1};
        customersList.forEach(c -> System.out.printf("%d. %s%n", i[0]++, c.getCustomerName()));

        System.out.print("Digite o número do cliente: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();

        if (index >= 0 && index < customersList.size()) {
            return customersList.get(index);
        } else {
            System.out.println("Índice inválido!");
            return null;
        }
    }

    private Customer inputCustomer() {
        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();
        return new Customer(name);
    }
}
