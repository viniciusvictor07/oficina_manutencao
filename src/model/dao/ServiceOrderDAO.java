package model.dao;

import model.entities.CarService;
import model.entities.Customer;
import model.entities.ServiceOrder;
import model.exception.DomainException;
import model.services.NoAdjustment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderDAO {

    public void save(ServiceOrder order) {
        String sql = "INSERT INTO tb_service_order (entry_date, vehicle_model, base_value, customer_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setTimestamp(1, Timestamp.valueOf(order.getEntryDate()));
            st.setString(2, order.getVehicleModel());
            st.setDouble(3, order.getBaseValue());
            st.setInt(4, order.getCustomer().getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DomainException("Erro ao salvar a ordem de serviço: " + e.getMessage());
        }
    }

    public List<ServiceOrder> findAll() {
        String sql = "SELECT so.*, c.name FROM tb_service_order so INNER JOIN tb_customer c ON so.customer_id = c.id";
        List<ServiceOrder> list = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");

                String model = rs.getString("vehicle_model");
                double value = rs.getDouble("base_value");
                LocalDateTime date = rs.getTimestamp("entry_date").toLocalDateTime();

                int customerId = rs.getInt("customer_id");
                String customerName = rs.getString("name");
                Customer customer = new Customer(customerId, customerName);

                ServiceOrder order = new CarService(date, model, value, customer, new NoAdjustment());

                order.setId(id);

                list.add(order);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ordens de serviço: " + e.getMessage());
        }
    }
}