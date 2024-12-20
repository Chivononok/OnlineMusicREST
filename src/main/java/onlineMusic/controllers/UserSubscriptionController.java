package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import onlineMusic.dto.usersubscription.UserSubscriptionRequest;
import onlineMusic.dto.usersubscription.UserSubscriptionResponse;
import onlineMusic.repository.SubscriptionRepository;
import onlineMusic.repository.UserRepository;
import onlineMusic.repository.UserSubscriptionRepository;
import onlineMusic.services.UserSubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usersubscription", description = "the user subscription API")
@RestController
@RequiredArgsConstructor
public class UserSubscriptionController {
    private final UserSubscriptionService userSubscriptionService;


//    public UserSubscriptionController(UserSubscriptionRepository repository,
//                                      UserRepository userRepository,
//                                      SubscriptionRepository subscriptionRepository){
//        this.repository = repository;
//        this.subscriptionRepository = subscriptionRepository;
//        this.userRepository = userRepository;
//    }

    @GetMapping("/usersubscription/all")
    List<UserSubscriptionResponse> getAll(){
        return userSubscriptionService.getAll();
    }

    @PostMapping("/usersubscription/add")
    public void addUserSubscription(@RequestBody UserSubscriptionRequest userSubscriptionDTO){
        userSubscriptionService.addUserSubscription(userSubscriptionDTO);
    }

    @GetMapping("/usersubscription/{id}")
    UserSubscriptionResponse getOne(@PathVariable Long id){
        return userSubscriptionService.getById(id);
        /*
        return repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Подписка номер " + id + " не найдена"));
         */
    }

    @GetMapping("/usersubscription/users/{id}")
    public List<UserSubscriptionResponse> getAllByUserId(@PathVariable Long id){
        return userSubscriptionService.getAllByUserId(id);
    }

    @DeleteMapping("/usersubscription/{id}")
    public void delete(@PathVariable Long id){
        userSubscriptionService.deleteById(id);
    }
}
