package onlineMusic.dto.playlist;

import lombok.Data;
import onlineMusic.dto.song.SongResponse;

import java.util.Set;

@Data
public class PlayListResponse {
    private String username;
    private Long playlistid;
    private String playlistname;
    private Set<SongResponse> songs;
}
