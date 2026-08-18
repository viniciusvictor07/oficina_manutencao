package model.entities;

import jakarta.persistence.*;
import model.services.TaxManager;

@Entity
@DiscriminatorValue("CAR")
public class CarService extends ServiceOrder {
    public CarService() {
        super();
    }

    public CarService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        double repairPrice = getBaseValue() * 1.3;
        if (getAdjustment() != null) {
            return getAdjustment().applyAdjustment(repairPrice);
        }
        return repairPrice;
    }
}