package onlineMusic.mapper;

import onlineMusic.dto.song.SongResponse;
import onlineMusic.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper {
    @Mapping(target = "performerid", source = "performer.id")
    public SongResponse toSongResponse(Song song);
}
