package model.services;

import jakarta.persistence.EntityManager;
import model.dao.CustomerDAO;
import model.dao.ServiceOrderDAO;
import model.entities.Customer;
import model.entities.ServiceOrder;
import model.exception.DomainException;

import java.util.List;

public class ServiceManager {
    private final CustomerDAO customerDAO;
    private final ServiceOrderDAO serviceOrderDAO;

    public ServiceManager(EntityManager em) {
        this.customerDAO = new CustomerDAO(em);
        this.serviceOrderDAO = new ServiceOrderDAO(em);
    }
    public boolean addCustomer(Customer pendingCustomer) {
        if (customerDAO.existsByName(pendingCustomer.getCustomerName())) {
            throw new DomainException("Já existe um cliente cadastrado com este nome.");
        }
        customerDAO.save(pendingCustomer);
        return true;
    }

    public void addService(ServiceOrder pendingOrder) {
        serviceOrderDAO.save(pendingOrder);
    }

    public boolean removeCustomer(Customer selectedCustomer) {
        customerDAO.delete(selectedCustomer);
        return true;
    }

    public void removeService(int id) {
        serviceOrderDAO.delete(id);
    }

    public List<ServiceOrder> getAllServices() {
        return serviceOrderDAO.findAll();
    }

    public List<ServiceOrder> getServicesByCustomer(Customer customer) {
        return serviceOrderDAO.findByCustomerId(customer.getId());
    }

    public double getTotalBaseValue() {
        return getAllServices().stream()
                .mapToDouble(ServiceOrder::getBaseValue)
                .sum();
    }

    public double getTotalRepairValue() {
        return getAllServices().stream()
                .mapToDouble(ServiceOrder::getFinalRepairPrice)
                .sum();
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }
}
