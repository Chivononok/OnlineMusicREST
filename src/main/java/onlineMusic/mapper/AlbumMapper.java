package onlineMusic.mapper;

import onlineMusic.dto.album.AlbumRequest;
import onlineMusic.dto.album.AlbumResponse;
import onlineMusic.entity.Album;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    public Album toAlbum(AlbumRequest albumRequest);
    public AlbumResponse toAlbumResponse(Album album);
}
