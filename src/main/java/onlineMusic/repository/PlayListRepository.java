package onlineMusic.repository;

import onlineMusic.entity.PlayLists;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayLists, Long> {
    List<PlayLists> getPlayListsByUserId(Long userId);

}