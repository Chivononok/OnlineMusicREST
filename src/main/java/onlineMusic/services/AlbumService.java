package onlineMusic.services;

import lombok.RequiredArgsConstructor;
import onlineMusic.dto.album.AlbumRequest;
import onlineMusic.dto.album.AlbumResponse;
import onlineMusic.dto.song.SongResponse;
import onlineMusic.entity.Album;
import onlineMusic.entity.Song;
import onlineMusic.exceptions.NotFoundException;
import onlineMusic.mapper.AlbumMapper;
import onlineMusic.mapper.SongMapper;
import onlineMusic.repository.AlbumRepository;
import onlineMusic.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final AlbumMapper albumMapper;
    private final SongMapper songMapper;

    public AlbumResponse add(AlbumRequest albumRequest){
        Album album=albumRepository.save(albumMapper.toAlbum(albumRequest));
        return albumMapper.toAlbumResponse(album);
    }

    public void removeById(Long id){
        albumRepository.deleteById(id);
    }

    public List<AlbumResponse> getAll(){
        List<Album> albums = albumRepository.findAll();
        List<AlbumResponse> albumResponseList = new ArrayList<>();
        for (Album album: albums) {
            Set<Song> songs = songRepository.findAllByAlbumId(album.getId());
            Set<SongResponse> songResponseSet = new HashSet<>();
            for (Song song: songs) {
                songResponseSet.add(songMapper.toSongResponse(song));
            }
            AlbumResponse albumResponse = albumMapper.toAlbumResponse(album);
            albumResponse.setSongs(songResponseSet);
            albumResponseList.add(albumResponse);
        }
        return albumResponseList;
    }

    public AlbumResponse getById(Long id){
        Album album = albumRepository.findById(id).orElseThrow(() -> new NotFoundException("Не найден альбом с id = " + id));

        Set<Song> songs = songRepository.findAllByAlbumId(id);
        Set<SongResponse> songResponseSet = new HashSet<>();
        for (Song song: songs) {
            songResponseSet.add(songMapper.toSongResponse(song));
        }
        AlbumResponse albumResponse = albumMapper.toAlbumResponse(album);
        albumResponse.setSongs(songResponseSet);
        return albumResponse;
    }


}
