package model.entities;

import model.services.TaxManager;

public class MotoService extends ServiceOrder {
    public MotoService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        double repairPrice = getBaseValue() * 1.2;
        return getAdjustment().applyAdjustment(repairPrice);
    }
}