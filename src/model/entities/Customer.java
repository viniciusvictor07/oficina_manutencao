package model.entities;

public class Customer {
    private Integer id;
    private final String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer(Integer id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return java.util.Objects.equals(customerName, customer.customerName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(customerName);
    }
}
