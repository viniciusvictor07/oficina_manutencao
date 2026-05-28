package model.entities;

public class MotoService extends ServiceOrder {
    public MotoService(String vehicleModel, double baseValue, Customer customer) {
        super(vehicleModel, baseValue, customer);
    }

    @Override
    public Double getBaseRepairPrice() {
        return baseValue * 1.2;
    }
}