package entities;

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

    public boolean hasCustomers() {
        return !this.allCustomers.isEmpty();
    }

    public List<Customer> getAllCostumers() {
        return this.allCustomers;
    }
}