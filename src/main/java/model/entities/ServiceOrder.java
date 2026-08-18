package model.entities;

import jakarta.persistence.*;
import model.services.TaxManager;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_service_order")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "service_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Transient
    private TaxManager adjustment;

    @Column(name = "base_value")
    private double baseValue;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    protected ServiceOrder() {
    }

    public ServiceOrder(String vehicleModel, double baseValue, Customer customer, TaxManager adjustment) {
        this.entryDate = LocalDateTime.now();
        this.vehicleModel = vehicleModel;
        this.baseValue = baseValue;
        this.customer = customer;
        this.adjustment = adjustment;
    }

    public ServiceOrder(LocalDateTime entryDate, String vehicleModel, double baseValue, Customer customer, TaxManager adjustment) {
        this.entryDate = entryDate;
        this.vehicleModel = vehicleModel;
        this.baseValue = baseValue;
        this.customer = customer;
        this.adjustment = adjustment;
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
        return adjustment;
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