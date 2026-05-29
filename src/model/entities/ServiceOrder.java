package model.entities;

import model.services.TaxManager;
import java.time.LocalDateTime;

public abstract class ServiceOrder {
    private final LocalDateTime entryDate;
    private final String vehicleModel;
    private final Customer customer;
    private final double baseValue;
    private final TaxManager adjusment;

    public ServiceOrder(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        this.entryDate = LocalDateTime.now();
        this.vehicleModel = vehicleModel;
        this.baseValue = baseValue;
        this.customer = customer;
        this.adjusment = adjusment;
    }

    public abstract Double getFinalRepairPrice();

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public TaxManager getAdjustment() {
        return adjusment;
    }

    public Customer getCustomer() {
        return customer;
    }
}