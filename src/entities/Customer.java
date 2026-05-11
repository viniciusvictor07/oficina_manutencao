package entities;

public class Customer {
    private String name;
    private String model;
    private Double modelPrice;

    public Customer(String name, String model, Double modelPrice) {
        this.name = name;
        this.model = model;
        this.modelPrice = modelPrice;
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