package application;


import jakarta.persistence.EntityManager;
import model.dao.DB;
import model.services.ServiceManager;
import model.entities.ConsoleUI;

import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        EntityManager em = DB.getEntityManager();
        ServiceManager serviceManager = new ServiceManager(em);
        ConsoleUI consoleUI = new ConsoleUI(serviceManager, sc);
        consoleUI.process();

        sc.close();
        em.close();
        DB.close();
    }
}
