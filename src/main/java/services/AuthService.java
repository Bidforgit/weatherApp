package services;

import entities.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final SessionService sessionService;
    private final PasswordService passwordService;

    public ResponseEntity<String> authUser(String username, String password, HttpServletResponse response) {
        Optional<User> userOpt = userService.findByLogin(username);

        if (userOpt.isEmpty() || !passwordService.verifyPassword(password, userOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        User user = userOpt.get();
        String sessionId = sessionService.save(user);

        // Set the session ID in a cookie
        Cookie cookie = new Cookie("SESSION_ID", sessionId);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return ResponseEntity.ok("Login successful");
    }

    public void registerUser(String login, String rawPassword) {
        String bcryptHashString = passwordService.hashPassword(rawPassword);

        User user = new User();
        user.setLogin(login);
        user.setPassword(bcryptHashString);

        userService.save(user);
    }


}
