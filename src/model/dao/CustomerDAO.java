package model.dao;

import model.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    public void save(Customer customer) {
        String sql = "INSERT INTO tb_customer (name) VALUES (?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, customer.getCustomerName());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente no banco: " + e.getMessage());
        }
    }
}