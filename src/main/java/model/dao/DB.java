package model.dao;

import jakarta.persistence.*;

public class DB {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("oficina-pu");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}