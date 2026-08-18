package model.entities;

import jakarta.persistence.Entity;
import model.services.TaxManager;
import java.time.LocalDateTime;

@Entity
public class MotoService extends ServiceOrder {

    public MotoService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    public
    MotoService(LocalDateTime entryDate, String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(entryDate, vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        double repairPrice = getBaseValue() * 1.2;
        return getAdjustment().applyAdjustment(repairPrice);
    }
}