package onlineMusic.services;

import lombok.RequiredArgsConstructor;
import onlineMusic.dto.song.SongRequest;
import onlineMusic.dto.song.SongResponse;
import onlineMusic.entity.Album;
import onlineMusic.entity.Performer;
import onlineMusic.entity.Song;
import onlineMusic.exceptions.NotFoundException;
import onlineMusic.mapper.SongMapper;
import onlineMusic.repository.AlbumRepository;
import onlineMusic.repository.PerformerRepository;
import onlineMusic.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final PerformerRepository performerRepository;
    private final AlbumRepository albumRepository;
    private final SongMapper songMapper;

    public Optional<Song> getSongById(Long id){
        return songRepository.findById(id);
    }

    public List<SongResponse> getAll(PageRequest pageRequest){
        Page<Song> page = songRepository.findAll(pageRequest);
        return page.getContent().stream().map(song -> songMapper.toSongResponse(song))
                .collect(Collectors.toList());
    }

    public Song addSong(SongRequest songRequest){
        Song song = new Song();
        Performer performer = performerRepository.findById(songRequest.getPerformerId()).get();
        Album album = albumRepository.findById(songRequest.getAlbumId()).get();

        song.setLink(songRequest.getLink());
        song.setName(songRequest.getName());
        song.setPerformer(performer);
        song.setAlbum(album);

        songRepository.save(song);
        return song;
    }

    public SongResponse getById(Long id){
        return songRepository.findById(id).map(song-> songMapper.toSongResponse(song))
                .orElseThrow(() -> new NotFoundException("Песня с id=" +id + " не найдена"));
    }

    public SongResponse replaceSong(SongRequest songRequest, Long id){
        Song updatedSong = songRepository.findById(id)
                .map(song -> {
                    song.setName(songRequest.getName());
                    song.setLink((songRequest.getLink()));
                    song.setPerformer(performerRepository.findById(songRequest.getPerformerId()).get());
                    song.setAlbum(albumRepository.findById(songRequest.getAlbumId()).get());
                    return songRepository.save(song);
                })
                .orElseGet(()->{
                    return addSong(songRequest);
                });
        return songMapper.toSongResponse(updatedSong);
    }
}
