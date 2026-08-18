package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import model.services.TaxManager;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDateTime entryDate;
    private String vehicleModel;
    private Customer customer;
    private double baseValue;
    private TaxManager adjusment;
    private int id;

    protected ServiceOrder() {}

    public ServiceOrder(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        this.entryDate = LocalDateTime.now();
        this.vehicleModel = vehicleModel;
        this.baseValue = baseValue;
        this.customer = customer;
        this.adjusment = adjusment;
    }

    public ServiceOrder(LocalDateTime entryDate, String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        this.entryDate = entryDate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}