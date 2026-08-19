package model.entities;

import jakarta.persistence.*;
import model.services.TaxManager;

@Entity
@DiscriminatorValue("MOTO")
public class MotoService extends ServiceOrder {
    @SuppressWarnings("unused")
    public MotoService() {
        super();
    }

    public MotoService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        double repairPrice = getBaseValue() * 1.2;
        return getAdjustment().applyAdjustment(repairPrice);
    }
}