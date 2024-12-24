package onlineMusic.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dateStart;
    private String dateEnd;
//    private LocalDate dateStart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Subscription subscription;

}
