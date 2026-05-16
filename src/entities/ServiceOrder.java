package entities;

import java.time.LocalDateTime;

public abstract class ServiceOrder {
    protected LocalDateTime entryDate;
    protected String vehicleModel;
    protected Double baseValue;
    protected Customer customer;

    public ServiceOrder(String vehicleModel, Double baseValue, Customer customer) {
        this.entryDate = LocalDateTime.now();
        this.vehicleModel = vehicleModel;
        this.baseValue = baseValue;
        this.customer = customer;
    }

    public abstract Double getRepairPrice();

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public Double getBaseValue() {
        return baseValue;
    }
}