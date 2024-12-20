package onlineMusic.dto.usersubscription;

import lombok.Data;

@Data
public class UserSubscriptionResponse {

    private Long userId;
    private String name;

    private Long subscriptionId;
    private String subscriptionName;
    private boolean isActive;
    private int days;

    private String dateStart;
    private String dateEnd;
}
