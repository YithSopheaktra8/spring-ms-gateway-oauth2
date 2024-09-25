package co.istad.userservice.dto;

public record UserCreateRequest(
        String username,
        String email

) {
}
