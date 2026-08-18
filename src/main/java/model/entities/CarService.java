package model.entities;

import jakarta.persistence.Entity;
import model.services.TaxManager;
import java.time.LocalDateTime;

@Entity
public class CarService extends ServiceOrder {

    public CarService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    public CarService(LocalDateTime entryDate, String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(entryDate, vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        double repairPrice = getBaseValue() * 1.3;
        return getAdjustment().applyAdjustment(repairPrice);
    }
}