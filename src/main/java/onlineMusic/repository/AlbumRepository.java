package onlineMusic.repository;

import onlineMusic.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumRepository extends JpaRepository<Album, Long> {

}
