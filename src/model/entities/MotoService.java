package model.entities;

public class MotoService extends ServiceOrder {
    public MotoService(String vehicleModel, Double baseValue, Customer customer) {
        super(vehicleModel, baseValue, customer);
    }

    @Override
    public Double getRepairPrice() {
        return baseValue * 1.2;
    }
}