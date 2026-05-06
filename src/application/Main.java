package application;

import entities.Gerenciador;
import entities.Menu;
import entities.Servico;


import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Gerenciador gerenciador = new Gerenciador();
        Menu menu = new Menu(gerenciador, sc);
        menu.processar();
        sc.close();
    }
}
