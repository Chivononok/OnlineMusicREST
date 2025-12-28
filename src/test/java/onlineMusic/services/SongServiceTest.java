package onlineMusic.services;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    @InjectMocks
    private SongService songService;

    @Mock
    private SongRepository songRepository;

    @Mock
    private PerformerRepository performerRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private SongMapper songMapper;

    @Test
    void getSongById_when_songExist() {
        Song song = new Song();
        song.setId(1);
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        Optional<Song> result = songService.getSongById(1L);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void getSongById_when_songNotExist() {
        when(songRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Song> result = songService.getSongById(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    void addSong() {
        SongRequest request = new SongRequest();
        request.setName("TestSong");
        request.setLink("http://link");
        request.setPerformerId(1L);
        request.setAlbumId(2L);

        Performer performer = new Performer();
        performer.setId(1L);
        Album album = new Album();
        album.setId(2L);

        when(performerRepository.findById(1L)).thenReturn(Optional.of(performer));
        when(albumRepository.findById(2L)).thenReturn(Optional.of(album));
        when(songRepository.save(any(Song.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Song result = songService.addSong(request);

        assertEquals("TestSong", result.getName());
        assertEquals("http://link", result.getLink());
        assertEquals(performer, result.getPerformer());
        assertEquals(album, result.getAlbum());
        verify(songRepository).save(any(Song.class));
    }

    @Test
    void getById_when_songExists() {
        Song song = new Song();
        song.setId(1);
        song.setName("SongX");
        song.setLink("http://songx");

        SongResponse response = new SongResponse("SongX", "http://songx", 99L);

        when(songRepository.findById(1L)).thenReturn(Optional.of(song));
        when(songMapper.toSongResponse(song)).thenReturn(response);

        SongResponse result = songService.getById(1L);

        assertEquals("SongX", result.getName());
        assertEquals("http://songx", result.getLink());
        assertEquals(99L, result.getPerformerid());
    }

    @Test
    void getById_when_songNotExists() {
        when(songRepository.findById(99L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> songService.getById(99L));

        assertEquals("Песня с id=99 не найдена", exception.getMessage());
    }
}
