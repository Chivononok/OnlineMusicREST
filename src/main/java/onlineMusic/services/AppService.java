package onlineMusic.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.dto.user.UserResponse;
import onlineMusic.entity.User;
import onlineMusic.exceptions.NotFoundException;
import onlineMusic.exceptions.NotUniqueUserException;
import onlineMusic.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserResponse addUser(UserCreateUpdateRequest newUser) {
        User user = new User();
        Optional<User> userFromDatabase = repository.findByName(newUser.getName());
        if (userFromDatabase.isEmpty()){
            user = toUser(newUser);
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            repository.save(user);
        } else {
            throw new NotUniqueUserException("Пользователь " + user.getName() + " уже существует");
        }
        UserResponse userResponse = toUserResponse(user);
        userResponse.setDescription("Пользователь успешно добавлен");
        return userResponse;
    }

    public UserResponse deleteUserById(Long id){
        Optional<User> userFromDatabase = repository.findById(id);
        if (userFromDatabase.isEmpty()){
            throw new NotFoundException("Пользователь с id=" + id + " не найден");
        }
        repository.deleteById(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setName(userFromDatabase.get().getName());
        userResponse.setDescription("Пользователь успешно удален");
        return userResponse;
    }



    private UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        return userResponse;
    }

    private User toUser(UserCreateUpdateRequest userCreateUpdateRequest){
        User user = new User();
        user.setRoles(userCreateUpdateRequest.getRoles());
        user.setPassword(userCreateUpdateRequest.getPassword());
        user.setName(userCreateUpdateRequest.getName());
        return user;
    }
}