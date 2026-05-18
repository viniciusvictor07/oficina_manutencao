package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String customerName;
    private final List<ServiceOrder> customerOrders;

    public Customer(String customerName) {
        this.customerName = customerName;
        this.customerOrders = new ArrayList<>();
    }

    public boolean addServiceOrder(ServiceOrder pendingServiceOrder) {
        return this.customerOrders.add(pendingServiceOrder);
    }

    public boolean hasServices() {
        return !this.customerOrders.isEmpty();
    }

    public List<ServiceOrder> getCustomerOrders() {
        return customerOrders;
    }

    public String getCustomerName() {
        return customerName;
    }
}

