package onlineMusic.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;


@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean isActive;
    private int days;


    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSubscription> userSubscription;

    public void addUserSubscription(UserSubscription userSubscription){
        this.userSubscription.add(userSubscription);
        userSubscription.setSubscription(this);
    }

    public void removeUserSubscription(UserSubscription userSubscription){
        this.userSubscription.remove(userSubscription);
        userSubscription.setSubscription(null);
    }


}
