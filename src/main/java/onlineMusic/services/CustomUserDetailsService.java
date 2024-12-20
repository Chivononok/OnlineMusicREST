package onlineMusic.services;

import lombok.RequiredArgsConstructor;
import onlineMusic.config.CustomUserDetails;
import onlineMusic.entity.User;
import onlineMusic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByName(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}