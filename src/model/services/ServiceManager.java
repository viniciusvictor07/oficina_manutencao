package model.services;

import model.dao.CustomerDAO;
import model.dao.ServiceOrderDAO;
import model.entities.Customer;
import model.entities.ServiceOrder;
import model.exception.DomainException;

import java.util.List;

public class ServiceManager {
    public boolean addCustomer(Customer pendingCustomer) {
        CustomerDAO customerDAO = new CustomerDAO();

        if (customerDAO.existsByName(pendingCustomer.getCustomerName())) {
            throw new DomainException("Já existe um cliente cadastrado com este nome.");
        }

        customerDAO.save(pendingCustomer);
        return true;
    }

    public void registerService(ServiceOrder pendingOrder) {
        ServiceOrderDAO dao = new ServiceOrderDAO();
        dao.save(pendingOrder);
    }

    public boolean removeCustomer(Customer selectedCustomer) {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.delete(selectedCustomer);
        return true;
    }

    public List<ServiceOrder> getAllServices() {
        ServiceOrderDAO dao = new ServiceOrderDAO();
        return dao.findAll();
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
        return new CustomerDAO().findAll();
    }
}
