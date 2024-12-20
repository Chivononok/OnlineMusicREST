package onlineMusic.repository;

import onlineMusic.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    Optional<UserSubscription> findByUserId(Long userId);
    List<UserSubscription> findBySubscriptionId(Long subscriptionId);
    List<UserSubscription> findAllByUserId(Long userId);
}

