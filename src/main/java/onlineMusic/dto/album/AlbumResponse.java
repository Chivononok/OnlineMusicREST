package onlineMusic.dto.album;

import lombok.Data;
import onlineMusic.dto.song.SongResponse;

import java.util.Set;

@Data
public class AlbumResponse {
    private Long id;
    private String name;
    private Integer year;
    private Set<SongResponse> songs;
}
