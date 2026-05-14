package entities;

import java.time.LocalDateTime;

public abstract class ServiceOrder {
    protected LocalDateTime entryDate;
    protected String vehicleModel;
    protected Double baseValue;

    public ServiceOrder(LocalDateTime entryDate, String vehicleModel, Double baseValue) {
        this.entryDate = entryDate;
        this.vehicleModel = vehicleModel;
        this.baseValue = baseValue;
    }

    public abstract Double getRepairPrice();
}