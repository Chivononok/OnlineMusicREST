package onlineMusic.services;

import lombok.RequiredArgsConstructor;
import onlineMusic.dto.playlist.PlayListResponse;
import onlineMusic.dto.playlist.PlaylistRequest;
import onlineMusic.entity.PlayLists;
import onlineMusic.entity.User;
import onlineMusic.mapper.SongMapper;
import onlineMusic.repository.PlayListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayListService {
    private final PlayListRepository playListRepository;
    private final SongMapper songMapper;
    private final UserService userService;
    private final SongService songService;

    public List<PlayListResponse> getPlaylistByUserId(Long userId){
        List<PlayLists> playLists = playListRepository.getPlayListsByUserId(userId);
        List<PlayListResponse> response = playLists.stream()
                .map(playlist -> toPlayListResponse(playlist)).collect(Collectors.toList());
        return response;
    }

    private PlayListResponse toPlayListResponse(PlayLists playLists){
        PlayListResponse playListResponse = new PlayListResponse();
        playListResponse.setPlaylistid(playLists.getId());
        playListResponse.setPlaylistname(playLists.getName());
        playListResponse.setUsername(playLists.getUser().getName());
        playListResponse.setSongs(playLists.getSongs().stream().map(song -> songMapper.toSongResponse(song)).collect(Collectors.toSet()));
        return playListResponse;
    }

    public void createPlaylist(PlaylistRequest playlistRequest){
        PlayLists playLists = new PlayLists();
        Optional<User> user = userService.getUserByName(playlistRequest.getUsername());

        playLists.setName(playlistRequest.getPlaylistname());
        playLists.setUser(user.get());

        for (Integer id:playlistRequest.getId()) {
            playLists.addSong(songService.getSongById(id.longValue()).get());
        }
        playListRepository.save(playLists);
    }

    public void addSongToPlaylist(PlaylistRequest playlistRequest, Long idPlaylist){
        PlayLists playLists = playListRepository.findById(idPlaylist).get();
        for (Integer id:playlistRequest.getId()) {
            playLists.addSong(songService.getSongById(id.longValue()).get());
        }
        playListRepository.save(playLists);
    }

    public void removeSongFromPlaylist(PlaylistRequest playlistRequest, Long idPlaylist){
        PlayLists playLists = playListRepository.findById(idPlaylist).get();

        for (Integer id:playlistRequest.getId()) {
            playLists.removeSong(songService.getSongById(id.longValue()).get());
        }
        playListRepository.save(playLists);
    }

    public void deletePlaylist(Long id){
        playListRepository.deleteById(id);
    }
}
