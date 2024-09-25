package co.istad.userservice.service;


import co.istad.userservice.domain.User;
import co.istad.userservice.dto.UserCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void createUser(UserCreateRequest userCreateRequest);

    List<User> getAllUsers();

}
