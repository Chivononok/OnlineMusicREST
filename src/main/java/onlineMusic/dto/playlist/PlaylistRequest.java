package onlineMusic.dto.playlist;

import lombok.Data;

import java.util.Set;

@Data
public class PlaylistRequest {
    private String playlistname;
    private String username;
    private Set<Integer> id;
}
