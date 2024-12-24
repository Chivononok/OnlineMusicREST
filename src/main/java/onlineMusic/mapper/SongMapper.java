package onlineMusic.mapper;

import onlineMusic.dto.song.SongResponse;
import onlineMusic.entity.Song;
import org.springframework.stereotype.Component;


@Component
public class SongMapper {
    public SongResponse toSongResponse(Song song){
        SongResponse songResponse = new SongResponse();
        songResponse.setName(song.getName());
        songResponse.setLink(song.getLink());
        songResponse.setPerformerid(song.getPerformer().getId());
        return songResponse;
    }
}
