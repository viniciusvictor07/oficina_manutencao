package model.services;

import model.entities.Customer;
import model.entities.ServiceOrder;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private final List<Customer> allCustomers;
    private final List<ServiceOrder> globalOrders;

    public ServiceManager() {
        this.allCustomers = new ArrayList<>();
        this.globalOrders = new ArrayList<>();
    }

    public boolean addCustomer(Customer pendingCustomer) {
        return this.allCustomers.add(pendingCustomer);
    }

    public void registerServiceToGlobalOrders(ServiceOrder pendingOrder) {
        this.globalOrders.add(pendingOrder);
    }

    public boolean removeCustomer(Customer selectedCustomer) {
        return allCustomers.removeIf(c -> c == selectedCustomer);
    }

    public double getTotalBaseValue() {
        double baseValues = 0.0;

        for (ServiceOrder s : globalOrders) {
            baseValues += s.getBaseValue();
        }

        return baseValues;
    }

    public double getTotalRepairValue() {
        double repairValues = 0.0;

        for (ServiceOrder s : globalOrders) {
            repairValues += s.getBaseRepairPrice();
        }
        return repairValues;
    }

    public List<Customer> getAllCustomers() {
        return this.allCustomers;
    }
}