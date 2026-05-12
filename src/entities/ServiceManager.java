package entities;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<ServiceOrder> serviceOrders;

    public ServiceManager() {
        this.serviceOrders = new ArrayList<>();
    }

    public boolean addService(ServiceOrder serviceOrderToAdd) {
        return this.serviceOrders.add(serviceOrderToAdd);
    }

    public boolean removeService(String targetName) {
        return serviceOrders.removeIf(s -> s.getCustomer().getName().equalsIgnoreCase(targetName));
    }

    public boolean hasServices() {
        return !this.serviceOrders.isEmpty();
    }

    public List<ServiceOrder> getAllServices() {
        return this.serviceOrders;
    }

    public double getTotalModelPrices() {
        double totalModelValue = 0.0;
        for (ServiceOrder s : this.serviceOrders) {
            totalModelValue += s.getCustomer().getModelPrice();
        }
        return totalModelValue;
    }

    public double getTotalRepairPrices() {
        double totalRepairValue = 0.0;
        for (ServiceOrder s : this.serviceOrders) {
            totalRepairValue += s.getRepairPrice();
        }
        return totalRepairValue;
    }
}