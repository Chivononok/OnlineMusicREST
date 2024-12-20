package onlineMusic.dto.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import onlineMusic.entity.Performer;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SongResponse {
    private String name;
    private String link;
    private Long performerid;
}
