package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserData(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
