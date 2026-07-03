package model.services;

import model.dao.CustomerDAO;
import model.entities.Customer;
import model.entities.ServiceOrder;
import model.exception.DomainException;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private final List<ServiceOrder> globalOrders;

    public ServiceManager() {
        this.globalOrders = new ArrayList<>();
    }

    public boolean addCustomer(Customer pendingCustomer) {
        CustomerDAO customerDAO = new CustomerDAO();

        if (customerDAO.existsByName(pendingCustomer.getCustomerName())) {
            throw new DomainException("Já existe um cliente cadastrado com este nome.");
        }

        customerDAO.save(pendingCustomer);
        return true;
    }

    public void registerServiceToGlobalOrders(ServiceOrder pendingOrder) {
        this.globalOrders.add(pendingOrder);
    }

    public boolean removeCustomer(Customer selectedCustomer) {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.delete(selectedCustomer);
        return true;
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
        return new CustomerDAO().findAll();
    }
}
