package services;

import dao.UserDao;
import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao dao;

    public Optional<User> findByLogin(String login) {
        return dao.findByLogin(login);
    }

    public void save(User user) {
        dao.save(user);
    }
}
