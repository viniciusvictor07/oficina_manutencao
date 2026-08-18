package model.dao;

import jakarta.persistence.EntityManager;
import model.entities.ServiceOrder;
import model.exception.DomainException;
import java.sql.*;
import java.util.List;

public class ServiceOrderDAO {
    private final EntityManager em;

    public ServiceOrderDAO(EntityManager em) {
        this.em = em;
    }

    public void save(ServiceOrder order) {
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        } catch (DomainException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DomainException("Erro ao salvar a ordem de serviço: " + e.getMessage());
        }
    }

    public List<ServiceOrder> findAll() {
        try {
            String jpql = "SELECT s FROM ServiceOrder s";
            return em.createQuery(jpql, ServiceOrder.class).getResultList();
        } catch (DomainException e) {
            throw new DomainException("Erro ao buscar ordens de serviço: " + e.getMessage());
        }
    }

    public List<ServiceOrder> findByCustomerId(int customerId) {
        try {
            String jpql = "SELECT s FROM ServiceOrder s WHERE s.customer.id = :customerId";
            return em.createQuery(jpql, ServiceOrder.class)
                    .setParameter("customerId", customerId)
                    .getResultList();
        } catch (Exception e) {
            throw new DomainException("Erro ao buscar ordens de serviço do cliente: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            em.getTransaction().begin();
            ServiceOrder order = em.find(ServiceOrder.class, id);
            if (order != null) {
                em.remove(order);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DomainException("Erro ao deletar a ordem de serviço: " + e.getMessage());
        }
    }
}