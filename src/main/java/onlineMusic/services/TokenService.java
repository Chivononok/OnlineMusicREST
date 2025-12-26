package onlineMusic.services;

import lombok.RequiredArgsConstructor;
import onlineMusic.entity.RefreshToken;
import onlineMusic.entity.User;
import onlineMusic.exceptions.InvalidTokenException;
import onlineMusic.jwt.JwtService;
import onlineMusic.model.JwtAuthenticationResponse;
import onlineMusic.repository.RefreshTokenRepository;
import onlineMusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${jwt.refresh-token.lifetime}")
    private Long lifeTime;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public JwtAuthenticationResponse refresh(String token){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(token);
        if (optionalRefreshToken.isEmpty()){
            throw new InvalidTokenException("Токен не валиден");
        }
        RefreshToken refreshToken = optionalRefreshToken.get();
        if (refreshToken.getExpireAs().isBefore(LocalDateTime.now())){
            throw new InvalidTokenException("Время жизни токена истекло");
        }
        User user = refreshToken.getUser();
        String accessToken = jwtService.generateToken(user);
        String refreshTokenString = UUID.randomUUID().toString();
        refreshToken.setToken(refreshTokenString);
        refreshToken.setExpireAs(LocalDateTime.now().plusMinutes(lifeTime));
        refreshTokenRepository.save(refreshToken);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(accessToken);
        jwtAuthenticationResponse.setRefreshToken(refreshTokenString);
        return jwtAuthenticationResponse;
    }

    public String generateRefreshToken(String userName){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUser_Name(userName);
        RefreshToken refreshToken;
        if (optionalRefreshToken.isEmpty()){
            User user = userRepository.findByName(userName).orElseThrow(()->
                    new RuntimeException("Такого пользователя нет"));
            refreshToken = new RefreshToken();
            refreshToken.setUser(user);
        } else {
            refreshToken = optionalRefreshToken.get();
        }
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpireAs(LocalDateTime.now().plusMinutes(lifeTime));
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }
}
