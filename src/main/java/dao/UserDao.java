package dao;

import configs.DatabaseConfig;
import entities.Session;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class UserDao {
    public Optional<User> findByLogin(String login) {
        String sql = "SELECT id, login, password FROM users WHERE login = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(Long.valueOf(rs.getString("id")));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.error("User didnt find with login: " + login, e);
        }
        return Optional.empty();

    }

    public void save(User user) {

        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getLogin());
            pstmt.setString(1, user.getPassword());

            int rowsInserted = pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error("User wasnt saved with login: " + user.getLogin(), e);
        }
    }
}
