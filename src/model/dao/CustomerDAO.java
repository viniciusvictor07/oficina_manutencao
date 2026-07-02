package model.dao;

import model.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Customer> findAll() {
        String sql = "SELECT * FROM tb_customer";
        List<Customer> list = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String dbName = rs.getString("name");
                Customer customer = new Customer(dbName);
                list.add(customer);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes no banco: " + e.getMessage());
        }
    }

    public void delete(Customer customer) {
        String sql = "DELETE FROM tb_customer WHERE name = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, customer.getCustomerName());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer from database: " + e.getMessage());
        }
    }
}