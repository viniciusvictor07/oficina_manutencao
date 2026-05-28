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
        double TotalBaseValues = 0.0;

        for (ServiceOrder s : globalOrders) {
            TotalBaseValues += s.getBaseValue();
        }

        return TotalBaseValues;
    }

    public double getTotalRepairValue() {
        double TotalRepairValues = 0.0;

        for (ServiceOrder s : globalOrders) {
            TotalRepairValues += s.getFinalRepairPrice();
        }
        return TotalRepairValues;
    }

    public List<Customer> getAllCustomers() {
        return this.allCustomers;
    }
}
