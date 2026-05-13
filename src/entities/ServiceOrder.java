package entities;

import java.time.LocalDateTime;

public class ServiceOrder {
    protected Customer customer;
    protected LocalDateTime entryDate;
    protected Double repairPrice;

    public ServiceOrder(Customer customer, LocalDateTime entryDate) {
        this.customer = customer;
        this.entryDate = entryDate;
        this.repairPrice = getRepairPrice();
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getRepairPrice() {
        return this.repairPrice = customer.getModelPrice() * 1.1;
    }

    public void setRepairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }
}