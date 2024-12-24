package onlineMusic.dto.song;

import lombok.Data;

@Data
public class SongRequest {
    private String name;
    private String link;
    private Long performerId;
    private Long albumId;
}
