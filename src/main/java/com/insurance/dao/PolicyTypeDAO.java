package com.insurance.dao;

import com.insurance.model.PolicyType;
import com.insurance.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyTypeDAO {
    
    public long createPolicyType(PolicyType policyType) throws SQLException {
        String sql = "INSERT INTO policytype (policy_name, description, risk_score) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, policyType.getpolicyname());
            stmt.setString(2, policyType.getDescription());
            stmt.setLong(3, policyType.getriskscore());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating policy type failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long policyTypeId = generatedKeys.getLong(1);
                    policyType.setpolicytypeid(policyTypeId);
                    return policyTypeId;
                } else {
                    throw new SQLException("Creating policy type failed, no ID obtained.");
                }
            }
        }
    }
    
    public PolicyType findPolicyTypeById(long policyTypeId) throws SQLException {
        String sql = "SELECT * FROM policytype WHERE policytypeid = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, policyTypeId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPolicyType(rs);
                }
                return null;
            }
        }
    }
    
    public List<PolicyType> getAllPolicyTypes() throws SQLException {
        String sql = "SELECT * FROM policytype ORDER BY policy_name";
        List<PolicyType> policyTypes = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                policyTypes.add(mapResultSetToPolicyType(rs));
            }
        }
        return policyTypes;
    }
    
    public List<PolicyType> findPolicyTypesByRiskScore(long minRiskScore, long maxRiskScore) throws SQLException {
        String sql = "SELECT * FROM policytype WHERE risk_score BETWEEN ? AND ? ORDER BY risk_score";
        List<PolicyType> policyTypes = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, minRiskScore);
            stmt.setLong(2, maxRiskScore);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    policyTypes.add(mapResultSetToPolicyType(rs));
                }
            }
        }
        return policyTypes;
    }
    
    private PolicyType mapResultSetToPolicyType(ResultSet rs) throws SQLException {
        PolicyType policyType = new PolicyType();
        policyType.setpolicytypeid(rs.getLong("policytypeid"));
        policyType.setpolicyname(rs.getString("policy_name"));
        policyType.setDescription(rs.getString("description"));
        policyType.setriskscore(rs.getLong("risk_score"));
        return policyType;
    }
}
