package onlineMusic.dto.user;

import lombok.Data;

@Data
public class UserCreateUpdateRequest {
    private String name;
    private String password;
    private String roles;
}
