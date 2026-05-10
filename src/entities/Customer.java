package entities;

import java.util.Scanner;

public class Customer {
    private String name;
    private String model;
    private Double modelPrice;

    public Customer(String name, String model, Double modelPrice) {
        this.name = name;
        this.model = model;
        this.modelPrice = modelPrice;
    }

    public static Customer createCostumer(Scanner sc) {
        System.out.println("Qual nome do cliente?");
        String name = sc.nextLine();
        System.out.println("Qual nome do modelo?");
        String model = sc.nextLine();
        System.out.println("Qual o preço do modelo?");
        Double modelPrice = sc.nextDouble();
        sc.nextLine();

        return new Customer(name, model, modelPrice);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(Double modelPrice) {
        this.modelPrice = modelPrice;
    }
}