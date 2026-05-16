package application;

import model.services.ServiceManager;
import model.entities.ConsoleUI;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ServiceManager serviceManager = new ServiceManager();
        ConsoleUI consoleUI = new ConsoleUI(serviceManager, sc);
        consoleUI.process();
        sc.close();
    }
}