package onlineMusic.jwt;

import lombok.RequiredArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.model.JwtAuthenticationResponse;
import onlineMusic.services.TokenService;
import onlineMusic.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;

    public JwtAuthenticationResponse signUp(UserCreateUpdateRequest userCreateUpdateRequest){
        UserDetails user = userService.addUserJwt(userCreateUpdateRequest);
        String refreshToken = tokenService.generateRefreshToken(user.getUsername());
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwtService.generateToken(user));
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse signIn(UserCreateUpdateRequest userCreateUpdateRequest){
        var user = userDetailsService.loadUserByUsername(userCreateUpdateRequest.getName());
        var authToken = new UsernamePasswordAuthenticationToken(
                userCreateUpdateRequest.getName(),
                userCreateUpdateRequest.getPassword(),
                user.getAuthorities()
        );
        authenticationManager.authenticate(authToken);
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
        String jwt = jwtService.generateToken(user);
        String refreshToken = tokenService.generateRefreshToken(user.getUsername());
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
}
