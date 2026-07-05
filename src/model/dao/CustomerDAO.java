package model.dao;

import model.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void save(Customer pendingCustomer) {
        String sql = "INSERT INTO tb_customer (name) VALUES (?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, pendingCustomer.getCustomerName());
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
                int dbId = rs.getInt("id");
                String dbName = rs.getString("name");
                Customer customer = new Customer(dbId, dbName);
                list.add(customer);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes no banco: " + e.getMessage());
        }
    }

    public void delete(Customer selectedCustomer) {
        String sql = "DELETE FROM tb_customer WHERE id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, selectedCustomer.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer from database: " + e.getMessage());
        }
    }

    public boolean existsByName(String customerName) {
        String sql = "SELECT COUNT(*) FROM tb_customer WHERE name = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, customerName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar nome no banco de dados: " + e.getMessage());
        }
        return false;
    }
}