package onlineMusic.services;

import lombok.RequiredArgsConstructor;
import onlineMusic.entity.Subscription;
import onlineMusic.exceptions.NotFoundException;
import onlineMusic.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public Subscription getById(Long id){
        return subscriptionRepository.findById(id).orElseThrow(()-> new NotFoundException("Подписка с id = " + id + " не найдена"));
        //return subscriptionRepository.findById(id);
    }
}
