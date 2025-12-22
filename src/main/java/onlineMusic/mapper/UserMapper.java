package onlineMusic.mapper;

import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.dto.user.UserResponse;
import onlineMusic.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserResponse toUserResponse(User user);
    public User toUser(UserCreateUpdateRequest userCreateUpdateRequest);
}
