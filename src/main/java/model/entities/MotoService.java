package model.entities;

import jakarta.persistence.*;
import model.services.TaxManager;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("MOTO")
public class MotoService extends ServiceOrder {
    public MotoService() {
        super();
    }

    public MotoService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    public MotoService(LocalDateTime entryDate, String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(entryDate, vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        double repairPrice = getBaseValue() * 1.2;
        return getAdjustment().applyAdjustment(repairPrice);
    }
}