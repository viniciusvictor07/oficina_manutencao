package entities;

import java.time.LocalDateTime;

public class ServiceOrder {
    private Customer customer;
    private String model;
    private Double modelPrice;
    private Double repairPrice;
    private LocalDateTime entryDate;

    public ServiceOrder(Customer customer, String model, Double modelPrice, LocalDateTime entryDate) {
        this.customer = customer;
        this.model = model;
        this.modelPrice = modelPrice;
        this.entryDate = entryDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }
}