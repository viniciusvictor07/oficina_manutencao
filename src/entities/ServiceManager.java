package entities;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<ServiceOrder> serviceOrders;

    public ServiceManager() {
        this.serviceOrders = new ArrayList<>();
    }

    public void addService(ServiceOrder serviceOrderToAdd) {
        this.serviceOrders.add(serviceOrderToAdd);
        System.out.println("Serviço registrado com sucesso!");
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

    public void calculateTotalProfit() {
        if (serviceOrders.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        double totalModelValue = 0.0;
        double totalRepairValue = 0.0;
        for (ServiceOrder s : this.serviceOrders) {
            totalModelValue += s.getCustomer().getModelPrice();
            totalRepairValue += s.getRepairPrice();
        }
        double profit = totalRepairValue - totalModelValue;
        System.out.printf("Valor total de serviços: R$ %.2f%nValor total de conserto: R$ %.2f%nLucro total: R$ %.2f%n",
                totalModelValue, totalRepairValue, profit);
    }
}