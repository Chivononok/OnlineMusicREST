package onlineMusic.repository;

import onlineMusic.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser_Name(String userName);
    Optional<RefreshToken> findByToken(String token);
}
