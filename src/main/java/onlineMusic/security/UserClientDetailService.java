package onlineMusic.security;

import lombok.RequiredArgsConstructor;
import onlineMusic.entity.User;
import onlineMusic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserClientDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetailsOptional = userRepository.findByName(username);
        if (userDetailsOptional.isEmpty()){
            throw new RuntimeException();
        }
        return userDetailsOptional.get();
    }

}
