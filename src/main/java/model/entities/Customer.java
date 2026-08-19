package model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_customer")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(name = "name")
    private String customerName;

    @SuppressWarnings("unused")
    public Customer() {
    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Integer getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    @SuppressWarnings("unused")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
