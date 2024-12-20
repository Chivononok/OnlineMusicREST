package onlineMusic.repository;

import onlineMusic.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
