package co.istad.userservice.service.imp;

import co.istad.userservice.domain.User;
import co.istad.userservice.dto.UserCreateRequest;
import co.istad.userservice.service.UserRepository;
import co.istad.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        if(userRepository.existsByUsername(userCreateRequest.username())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Username already exists"
            );
        }
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(userCreateRequest.username());
        user.setEmail(userCreateRequest.email());
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
