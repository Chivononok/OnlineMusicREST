package onlineMusic.mapper;

import onlineMusic.dto.usersubscription.UserSubscriptionResponse;
import onlineMusic.entity.UserSubscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSubscriptionMapper {
    public UserSubscriptionResponse toUserSubscriptionResponse(UserSubscription userSubscription);
}
