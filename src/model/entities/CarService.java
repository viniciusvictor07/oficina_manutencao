package model.entities;

public class CarService extends ServiceOrder {
    public CarService(String vehicleModel, Double baseValue, Customer customer) {
        super(vehicleModel, baseValue, customer);
    }

    @Override
    public Double getRepairPrice() {
        return baseValue * 1.3;
    }
}