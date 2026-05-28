package model.entities;

import model.services.TaxManager;

public class CarService extends ServiceOrder {
    public CarService(String vehicleModel, double baseValue, Customer customer, TaxManager adjusment) {
        super(vehicleModel, baseValue, customer, adjusment);
    }

    @Override
    public Double getFinalRepairPrice() {
        return baseValue * 1.3;
    }
}