package model.entities;

import model.services.TaxManager;

import java.time.LocalDateTime;

public abstract class ServiceOrder {
    protected LocalDateTime entryDate;
    protected String vehicleModel;
    protected Customer customer;
    protected double baseValue;
    protected TaxManager adjusment;

    public ServiceOrder(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        this.entryDate = LocalDateTime.now();
        this.vehicleModel = vehicleModel;
        this.baseValue = baseValue;
        this.customer = customer;
        this.adjusment = adjusment;
    }

    public abstract Double getBaseRepairPrice();

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