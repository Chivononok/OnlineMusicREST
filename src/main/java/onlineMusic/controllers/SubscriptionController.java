package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import onlineMusic.entity.Subscription;
import onlineMusic.exceptions.SubscriptionNotFoundException;
import onlineMusic.repository.SubscriptionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subscription", description = "the subscription API")
@RestController
public class SubscriptionController {
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping("/subscriptions")
    List<Subscription> all(){return subscriptionRepository.findAll();}

    @PostMapping("/subscriptions/add")
    public Subscription add(@RequestBody Subscription newSubscription){
        return subscriptionRepository.save(newSubscription);
    }

    @GetMapping("/subscriptions/{id}")
        Subscription getOne(@PathVariable Long id){
        return subscriptionRepository.findById(id)
                .orElseThrow(()->new SubscriptionNotFoundException(id));
    }

    @PutMapping("/subscriptions/{id}")
    Subscription replaceSubscription(@RequestBody Subscription newSubscription, @PathVariable Long id){
        return subscriptionRepository.findById(id)
                .map(subscription->{
                    subscription.setActive(true);
                    subscription.setDays(newSubscription.getDays());
                    subscription.setName(newSubscription.getName());
                    return subscriptionRepository.save(subscription);
                })
                .orElseGet(()->{
                    return subscriptionRepository.save(newSubscription);
                });
    }

    @DeleteMapping("/subscriptions/{id}")
    void deleteSubscription(@PathVariable Long id){
        subscriptionRepository.deleteById(id);
    }

}
