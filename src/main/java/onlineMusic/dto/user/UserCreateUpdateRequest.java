package onlineMusic.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCreateUpdateRequest {
    private String name;
    private String password;

    @Pattern(regexp = "ADMIN|USER", message = "неизвестная роль")
    private String roles;
}
