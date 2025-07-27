package com.insurance.dao;

import com.insurance.model.Customer;
import com.insurance.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    
    public long createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (name, dob, contact, address, city, state, postal_code, " +
                    "country, gender, email, national_id, background) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, customer.getName());
            stmt.setDate(2, customer.getDob());
            stmt.setLong(3, customer.getContact());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getCity());
            stmt.setString(6, customer.getState());
            stmt.setString(7, customer.getpostalcode());
            stmt.setString(8, customer.getCountry());
            stmt.setString(9, customer.getGender());
            stmt.setString(10, customer.getEmail());
            stmt.setString(11, customer.getnationalid());
            stmt.setString(12, customer.getBackground());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long customerId = generatedKeys.getLong(1);
                    customer.setcustomerid(customerId);
                    return customerId;
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }
        }
    }
    
    public Customer findCustomerById(long customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customerid = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, customerId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
                return null;
            }
        }
    }
    
    public List<Customer> findCustomersByState(String state) throws SQLException {
        String sql = "SELECT * FROM customer WHERE state = ?";
        List<Customer> customers = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, state);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    customers.add(mapResultSetToCustomer(rs));
                }
            }
        }
        return customers;
    }
    
    public boolean updateCustomerContact(long customerId, long newContact, String newEmail) throws SQLException {
        String sql = "UPDATE customer SET contact = ?, email = ? WHERE customerid = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, newContact);
            stmt.setString(2, newEmail);
            stmt.setLong(3, customerId);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customer ORDER BY name";
        List<Customer> customers = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        }
        return customers;
    }
    
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setcustomerid(rs.getLong("customerid"));
        customer.setName(rs.getString("name"));
        customer.setDob(rs.getDate("dob"));
        customer.setContact(rs.getLong("contact"));
        customer.setAddress(rs.getString("address"));
        customer.setCity(rs.getString("city"));
        customer.setState(rs.getString("state"));
        customer.setpostalcode(rs.getString("postal_code"));
        customer.setCountry(rs.getString("country"));
        customer.setGender(rs.getString("gender"));
        customer.setEmail(rs.getString("email"));
        customer.setnationalid(rs.getString("national_id"));
        customer.setBackground(rs.getString("background"));
        return customer;
    }
}
