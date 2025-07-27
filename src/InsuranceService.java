import java.sql.*;

public class InsuranceService {

    public void addUser(String name, String email, String contact) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, contact) VALUES (?, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, contact);
        stmt.executeUpdate();
        conn.close();
    }

    public void addPolicy(int userId, String policyType, double premium, String status) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO policies (user_id, policy_type, premium_amount, policy_status) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, userId);
        stmt.setString(2, policyType);
        stmt.setDouble(3, premium);
        stmt.setString(4, status);
        stmt.executeUpdate();
        conn.close();
    }

    public void fileClaim(int policyId, String reason) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO claims (policy_id, claim_reason, claim_status) VALUES (?, ?, ?)");
        stmt.setInt(1, policyId);
        stmt.setString(2, reason);
        stmt.setString(3, "Pending");
        stmt.executeUpdate();
        conn.close();
    }
}
