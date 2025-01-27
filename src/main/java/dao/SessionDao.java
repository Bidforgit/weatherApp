package dao;

import configs.DatabaseConfig;
import entities.Session;
import exceptions.SessionNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class SessionDao {
    private DatabaseConfig db;

    public Optional<Session> findBySessionId(String sessionId) {

        String sql = "SELECT id, userId, expiresAt FROM sessions WHERE id = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sessionId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Session session = new Session();
                    session.setId(rs.getString("id"));
                    session.setUserId(Long.valueOf(rs.getString("userId")));
                    session.setExpiresAt(LocalDateTime.parse(rs.getString("expiresAt")));
                    return Optional.of(session);
                }
            }
        } catch (SQLException e) {
            log.error("something went wrong with sessionId: " + sessionId, e);
        }
        return Optional.empty();
    }


    public void deleteByExpiresAtBefore(LocalDateTime now) {
        String sql = "delete from sessions where now ";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sessionId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Session session = new Session();
                    session.setId(rs.getString("id"));
                    session.setUserId(Long.valueOf(rs.getString("userId")));
                    session.setExpiresAt(LocalDateTime.parse(rs.getString("expiresAt")));
                }
            }
        } catch (SQLException e) {
            log.error("something went wrong with sessionId: " + sessionId, e);
        }
    }
}