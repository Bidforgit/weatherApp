package scheduler;

import dao.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessionCleanupTask {

    private final SessionDao sessionDao;

    @Autowired
    public SessionCleanupTask(SessionDao sessionRepository) {
        this.sessionDao = sessionRepository;
    }

    @Scheduled(fixedRate = 3600000) // Every hour
    public void cleanupExpiredSessions() {
        sessionDao.deleteByExpiresAtBefore(LocalDateTime.now());
    }
}
