package application;

import entities.Servico;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//    TODO: inputs do objeto
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("quantos serviços serão registrados? ");
        int n = sc.nextInt();
        sc.nextLine();

        Servico[] servicos = new Servico[n];
        for (int i = 0; i < servicos.length; i++) {
            System.out.printf("Qual nome do %dº cliente?%n", i + 1);
            String cliente = sc.nextLine();
            System.out.println("Qual nome do modelo?");
            String modelo = sc.nextLine();
            System.out.println("Qual o preço do conserto?");
            Double precoConserto = sc.nextDouble();
            sc.nextLine();
            servicos[i] = new Servico(cliente, modelo, precoConserto);
            System.out.println("Serviço registrado com sucesso!");
        }
        for (Servico s : servicos) {
            System.out.printf("Nome do cliente: %s | Nome do modelo: %s | Preço do conserto: R$ %.2f%n", s.getCliente(), s.getModelo(), s.getPrecoConserto());
        }
        sc.close();
    }
}