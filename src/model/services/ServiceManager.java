package model.services;

import model.entities.Customer;
import model.entities.ServiceOrder;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private final List<Customer> allCustomers;

    public ServiceManager() {
        this.allCustomers = new ArrayList<>();
    }

    public boolean addCustomer(Customer pendingCustomer) {
        return this.allCustomers.add(pendingCustomer);
    }

    public boolean removeCustomer(Customer selectedCustomer) {
        return allCustomers.removeIf(c -> c == selectedCustomer);
    }

    public double getTotalBaseValue() {
        double baseValues = 0.0;
        List<Customer> customers = getAllCustomers();

        for (Customer c : customers) {
            for (ServiceOrder s : c.getServiceOrders()) {
                baseValues += s.getBaseValue();
            }

        }

        return baseValues;
    }

    public double getTotalRepairValue() {
        double repairValues = 0.0;
        List<Customer> customers = getAllCustomers();

        for (Customer c : customers) {
            for (ServiceOrder s : c.getServiceOrders()) {
                repairValues += s.getRepairPrice();
            }
        }
        return repairValues;
    }

    public List<Customer> getAllCustomers() {
        return this.allCustomers;
    }
}