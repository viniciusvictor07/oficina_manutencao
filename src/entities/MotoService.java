package entities;

import java.time.LocalDateTime;

public class MotoService extends ServiceOrder {
    public MotoService(Customer customer, LocalDateTime entryDate) {
        super(customer, entryDate);
    }

    @Override
    public Double getRepairPrice() {
        return customer.getModelPrice() * 1.2;
    }

}