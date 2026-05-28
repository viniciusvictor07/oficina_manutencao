package model.entities;

public class CarService extends ServiceOrder {
    public CarService(String vehicleModel, double baseValue, Customer customer) {
        super(vehicleModel, baseValue, customer);
    }

    @Override
    public Double getBaseRepairPrice() {
        return baseValue * 1.3;
    }
}