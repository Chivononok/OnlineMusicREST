package onlineMusic.repository;

import onlineMusic.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByName(String name);
    Set<Song> findAllByAlbumId(Long id);
    //Page<Song> findAllByAlbumId(Long id, Pageable pageable);
    //Page<Song> findAll();
}
