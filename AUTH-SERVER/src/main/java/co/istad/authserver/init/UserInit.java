package co.istad.authserver.init;

import co.istad.authserver.domain.Authority;
import co.istad.authserver.domain.User;
import co.istad.authserver.repository.AuthorityRepository;
import co.istad.authserver.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserInit {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        Authority read = authorityRepository.save(Authority.builder().name("READ").build());
        Authority write = authorityRepository.save(Authority.builder().name("WRITE").build());
        authorityRepository.saveAll(List.of(read, write));

        User user = User.builder()
                .username("user")
                .uuid(UUID.randomUUID().toString())
                .password(passwordEncoder.encode("password"))
                .email("user@gmail.com")
                .authorities(Set.of(read,write))
                .build();

        userRepository.save(user);
    }
}