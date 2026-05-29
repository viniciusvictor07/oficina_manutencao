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
        return globalOrders.stream()
                .mapToDouble(ServiceOrder::getBaseValue)
                .sum();
    }

    public double getTotalRepairValue() {
        return globalOrders.stream()
                .mapToDouble(ServiceOrder::getFinalRepairPrice)
                .sum();
    }

    public List<Customer> getAllCustomers() {
        return this.allCustomers;
    }
}
