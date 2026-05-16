package entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String customerName;
    private final List<ServiceOrder> serviceOrders;

    public Customer(String customerName) {
        this.customerName = customerName;
        this.serviceOrders = new ArrayList<>();
    }

    public boolean addServiceOrder(ServiceOrder pendingServiceOrder) {
        return this.serviceOrders.add(pendingServiceOrder);
    }

    public boolean hasServices() {
        return !this.serviceOrders.isEmpty();
    }

    public double getTotalSpent() {
        double totalSpent = 0.0;
        for (ServiceOrder s : this.serviceOrders) {
            totalSpent += s.getRepairPrice();
        }
        return totalSpent;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public String getCustomerName() {
        return customerName;
    }
}

