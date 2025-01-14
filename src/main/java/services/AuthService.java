package services;

import dto.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private Long authUser(AuthRequestDto dto) {

        return
    }
}
