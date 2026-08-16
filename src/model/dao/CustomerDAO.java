package model.dao;

import jakarta.persistence.EntityManager;
import model.entities.Customer;
import model.exception.DomainException;

import java.util.List;

public class CustomerDAO {
    private final EntityManager em;

    public CustomerDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Customer pendingCustomer) {
        try {
            em.getTransaction().begin();
            if (pendingCustomer.getId() == null) {
                em.persist(pendingCustomer);
            } else {
                em.merge(pendingCustomer);
            }
            em.getTransaction().commit();
        } catch (DomainException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar cliente no banco: " + e.getMessage());
        }
    }

    public List<Customer> findAll() {
        String jpql = "SELECT c FROM Customer c";
        return em.createQuery(jpql, Customer.class).getResultList();
    }

    public void delete(Customer selectedCustomer) {
        try {
            em.getTransaction().begin();
            Customer customerToRemove = em.merge(selectedCustomer);
            em.remove(customerToRemove);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar cliente no banco: " + e.getMessage());
        }
    }

    public boolean existsByName(String customerName) {
        String jpql = "SELECT COUNT(c) FROM Customer c WHERE c.name = :name";
        return em.createQuery(jpql, Long.class)
                .setParameter("name", customerName)
                .getSingleResult() > 0;
    }
}