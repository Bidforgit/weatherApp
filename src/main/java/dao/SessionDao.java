package dao;

import entities.Session;

import java.time.LocalDateTime;
import java.util.Optional;

public class SessionDao {
    public Optional<Session> findBySessionId(String sessionId) {
    }

    public void deleteByExpiresAtBefore(LocalDateTime now) {
    }
}
