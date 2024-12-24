package onlineMusic.dto.usersubscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import onlineMusic.dto.SubscriptionDTO;

@Data
public class UserSubscriptionRequest {
    private String username;
    private Long subscriptionid;
}
