package configs;

import dao.SessionDao;
import entities.Session;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    private final SessionDao sessionDao;

    @Autowired
    public SessionInterceptor(SessionDao sessionRepository) {
        this.sessionDao = sessionRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = null;

        // Retrieve session ID from cookies
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("SESSION_ID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        if (sessionId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return false;
        }

        // Validate session
        Optional<Session> sessionOpt = sessionDao.findBySessionId(sessionId);
        if (sessionOpt.isEmpty() || sessionOpt.get().getExpiresAt().isBefore(LocalDateTime.now())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Session expired or invalid");
            return false;
        }

        // Attach user information to the request
        request.setAttribute("userId", sessionOpt.get().getUserId());
        return true;
    }
}
