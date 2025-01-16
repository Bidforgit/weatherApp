package integrationTest;

import dao.UserDao;
import entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


class RegisterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    @Test
    void testGetUserById() throws Exception {
        // Подготовка данных в базе
        User user = new User();
        user.setName("John Doe");
        user = userRepository.save(user);

        // Вызов API и проверка ответа
        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
