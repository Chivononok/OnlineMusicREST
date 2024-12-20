package onlineMusic.services;

import lombok.RequiredArgsConstructor;

import onlineMusic.dto.usersubscription.UserSubscriptionRequest;
import onlineMusic.dto.usersubscription.UserSubscriptionResponse;
import onlineMusic.entity.Subscription;
import onlineMusic.entity.User;
import onlineMusic.entity.UserSubscription;
import onlineMusic.exceptions.NotFoundException;
import onlineMusic.mapper.UserSubscriptionMapper;
import onlineMusic.repository.SubscriptionRepository;
import onlineMusic.repository.UserSubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserSubscriptionService {

    private final UserSubscriptionRepository repository;
    private final UserSubscriptionMapper userSubscriptionMapper;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public List<UserSubscriptionResponse> getAllByUserId(Long id) {
        var userSubscriptions = repository.findAllByUserId(id);
        List<UserSubscriptionResponse> response = userSubscriptions.stream()
                .map(userSubscription -> toUserSubscriptionResponse(userSubscription)).collect(Collectors.toList());
        return response;
    }

    public List<UserSubscriptionResponse> getAll(){
        var userSubscriptions = repository.findAll();
        List<UserSubscriptionResponse> response = userSubscriptions.stream()
                .map(userSubscription -> toUserSubscriptionResponse(userSubscription)).collect(Collectors.toList());
        return response;
    }

    private UserSubscriptionResponse toUserSubscriptionResponse(UserSubscription userSubscription) {
        UserSubscriptionResponse userSubscriptionDTO = new UserSubscriptionResponse();
        userSubscriptionDTO.setDateEnd(userSubscription.getDateEnd());
        userSubscriptionDTO.setDateStart(userSubscription.getDateStart());

        userSubscriptionDTO.setUserId(userSubscription.getUser().getId());
        userSubscriptionDTO.setName(userSubscription.getUser().getName());

        userSubscriptionDTO.setActive(userSubscription.getSubscription().isActive());
        userSubscriptionDTO.setDays(userSubscription.getSubscription().getDays());
        userSubscriptionDTO.setSubscriptionId(userSubscription.getSubscription().getId());
        userSubscriptionDTO.setSubscriptionName(userSubscription.getSubscription().getName());
        return userSubscriptionDTO;
    }

    public void addUserSubscription(UserSubscriptionRequest userSubscriptionRequest){
        LocalDateTime curDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Optional<User> user = userService.getUserByName(userSubscriptionRequest.getUsername());
        Subscription subscription = subscriptionService.getById(userSubscriptionRequest.getSubscriptionid());
        UserSubscription userSubscription = new UserSubscription();

        userSubscription.setUser(user.get());
        userSubscription.setSubscription(subscription);
        userSubscription.setDateStart(dtf.format(curDateTime));
        curDateTime = curDateTime.plusDays(subscription.getDays());
        userSubscription.setDateEnd(dtf.format(curDateTime));
        repository.save(userSubscription);
    }

    public UserSubscriptionResponse getById(Long id){
        UserSubscription subscription = repository.findById(id).orElseThrow(()->new NotFoundException("Подписка с id="+id +" не найдена"));
        return toUserSubscriptionResponse(subscription);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
