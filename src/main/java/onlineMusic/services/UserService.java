package onlineMusic.services;

import lombok.AllArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.dto.user.UserDeleteRequest;
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
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUserByName(String username){
        return userRepository.findByName(username);
    }

    public UserResponse addUser(UserCreateUpdateRequest newUser) {
        User user = new User();
        Optional<User> userFromDatabase = userRepository.findByName(newUser.getName());
        if (userFromDatabase.isEmpty()){
            user = toUser(newUser);
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(user);
        } else {
            throw new NotUniqueUserException("Пользователь " + user.getName() + " уже существует");
        }
        UserResponse userResponse = toUserResponse(user);
        userResponse.setDescription("Пользователь успешно добавлен");
        return userResponse;
    }

    public UserResponse deleteUserById(Long id){
        Optional<User> userFromDatabase = userRepository.findById(id);
        if (userFromDatabase.isEmpty()){
            throw new NotFoundException("Пользователь с id=" + id + " не найден");
        }
        userRepository.deleteById(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setName(userFromDatabase.get().getName());
        userResponse.setDescription("Пользователь успешно удален");
        return userResponse;
    }

    public UserResponse deleteByName(UserDeleteRequest userDeleteRequest){
        Optional<User> userFromDatabase = userRepository.findByName(userDeleteRequest.getName());
        if (userFromDatabase.isEmpty()){
            throw new NotFoundException("Пользователь " + userDeleteRequest.getName() + " не найден");
        }
        userRepository.deleteById(userFromDatabase.get().getId());
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
