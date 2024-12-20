package onlineMusic.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import onlineMusic.entity.User;
import onlineMusic.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByName(String username){
        return userRepository.findByName(username);
    }
}
