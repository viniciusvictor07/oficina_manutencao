package entities;

import java.time.LocalDateTime;

public class CarService extends ServiceOrder {
    public CarService(Customer customer, LocalDateTime entryDate) {
        super(customer, entryDate);
    }

    @Override
    public Double getRepairPrice() {
        return customer.getModelPrice() * 1.3;
    }

}