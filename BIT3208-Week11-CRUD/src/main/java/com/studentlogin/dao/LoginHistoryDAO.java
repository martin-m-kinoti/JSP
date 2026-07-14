package com.studentlogin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studentlogin.db.DBConnection;
import com.studentlogin.model.LoginHistoryEntry;

public class LoginHistoryDAO {

    public void recordLogin(String username, String ipAddress) throws SQLException {
        String sql = "INSERT INTO login_history (username, ip_address) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, ipAddress);
            stmt.executeUpdate();
        }
    }

    public List<LoginHistoryEntry> getAllLogins() throws SQLException {
        List<LoginHistoryEntry> entries = new ArrayList<>();
        String sql = "SELECT id, username, login_time, ip_address FROM login_history ORDER BY login_time DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LoginHistoryEntry entry = new LoginHistoryEntry();
                entry.setId(rs.getInt("id"));
                entry.setUsername(rs.getString("username"));
                entry.setLoginTime(rs.getTimestamp("login_time"));
                entry.setIpAddress(rs.getString("ip_address"));
                entries.add(entry);
            }
        }
        return entries;
    }

    public int countDistinctUsers() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT username) FROM login_history";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}
