package com.insurance.dao;

import com.insurance.model.Claim;
import com.insurance.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {
    
    public long createClaim(Claim claim) throws SQLException {
        String sql = "INSERT INTO claim (customerpolicy_id, incident_date, apply_date, claim_type, " +
                    "amountclaimed, status, claim_note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setLong(1, claim.getcustomerpolicyid());
            stmt.setDate(2, claim.getincidentdate());
            stmt.setDate(3, claim.getapplydate());
            stmt.setLong(4, claim.getclaimtype());
            stmt.setBigDecimal(5, claim.getamountclaimed());
            stmt.setString(6, claim.getStatus());
            stmt.setString(7, claim.getclaimnote());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating claim failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long claimId = generatedKeys.getLong(1);
                    claim.setclaimid(claimId);
                    return claimId;
                } else {
                    throw new SQLException("Creating claim failed, no ID obtained.");
                }
            }
        }
    }
    
    public Claim findClaimById(long claimId) throws SQLException {
        String sql = "SELECT * FROM claim WHERE claimid = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, claimId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToClaim(rs);
                }
                return null;
            }
        }
    }
    
    public List<Claim> findClaimsByStatus(String status) throws SQLException {
        String sql = "SELECT * FROM claim WHERE status = ? ORDER BY apply_date DESC";
        List<Claim> claims = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    claims.add(mapResultSetToClaim(rs));
                }
            }
        }
        return claims;
    }
    
    public List<Claim> findClaimsByCustomerPolicy(long customerPolicyId) throws SQLException {
        String sql = "SELECT * FROM claim WHERE customerpolicy_id = ? ORDER BY apply_date DESC";
        List<Claim> claims = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, customerPolicyId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    claims.add(mapResultSetToClaim(rs));
                }
            }
        }
        return claims;
    }
    
    public boolean updateClaimStatus(long claimId, String newStatus) throws SQLException {
        String sql = "UPDATE claim SET status = ? WHERE claimid = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, newStatus);
            stmt.setLong(2, claimId);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    private Claim mapResultSetToClaim(ResultSet rs) throws SQLException {
        Claim claim = new Claim();
        claim.setclaimid(rs.getLong("claimid"));
        claim.setcustomerpolicyid(rs.getLong("customerpolicy_id"));
        claim.setincidentdate(rs.getDate("incident_date"));
        claim.setapplydate(rs.getDate("apply_date"));
        claim.setclaimtype(rs.getLong("claim_type"));
        claim.setamountclaimed(rs.getBigDecimal("amountclaimed"));
        claim.setStatus(rs.getString("status"));
        claim.setclaimnote(rs.getString("claim_note"));
        return claim;
    }
}
