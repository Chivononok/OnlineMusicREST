package onlineMusic.mapper;

import onlineMusic.dto.album.AlbumRequest;
import onlineMusic.dto.album.AlbumResponse;
import onlineMusic.entity.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    public Album toAlbum(AlbumRequest albumRequest){
        Album album = new Album();
        album.setName(albumRequest.getName());
        album.setYear(albumRequest.getYear());
        return album;
    }

    public AlbumResponse toAlbumResponse(Album album){
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setName(album.getName());
        albumResponse.setYear(album.getYear());
        albumResponse.setId(album.getId());
        return albumResponse;
    }
}
