package co.istad.userservice.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<co.istad.userservice.domain.User, Long> {

    boolean existsByUsername(String username);
}
