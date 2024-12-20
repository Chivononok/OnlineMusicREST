package onlineMusic.dto.usersubscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import onlineMusic.dto.SubscriptionDTO;

@Data
public class UserSubscriptionRequest {

    //@JsonProperty("user_no_dto")
    private String username;
    //private SubscriptionDTO subscriptionDTO;
    private Long subscriptionid;
}
