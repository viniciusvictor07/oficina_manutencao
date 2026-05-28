package model.entities;

import model.services.TaxManager;

public class MotoService extends ServiceOrder {
    public MotoService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        double repairPrice = baseValue * 1.2;
        return adjusment.applyAdjustment(repairPrice);
    }
}