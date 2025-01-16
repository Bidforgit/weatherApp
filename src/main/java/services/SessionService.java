package services;

import dao.SessionDao;
import entities.Session;
import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionDao dao;

    public String save(User user) {
        // Create a session
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session();
        session.setId(sessionId);
        session.setUserId(user.getId());
        session.setExpiresAt(LocalDateTime.now().plusHours(1)); // Session expires in 1 hour


        return sessionId;
    }
}
