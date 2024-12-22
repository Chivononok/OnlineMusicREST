package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import onlineMusic.dto.playlist.PlayListResponse;
import onlineMusic.dto.playlist.PlaylistRequest;
import onlineMusic.services.PlayListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Playlist", description = "the playlist API")
@RestController
@RequiredArgsConstructor
public class PlayListsController {
    private final PlayListService playListService;

    @GetMapping("/playlists/user/{id}")
    List<PlayListResponse> getPlaylistsByUserId(@PathVariable @Min(0) Long id){
        return playListService.getPlaylistByUserId(id);
    }

    @PostMapping("/playlists/add")
    public void createPlayList(@RequestBody PlaylistRequest playlistRequest){
        playListService.createPlaylist(playlistRequest);
    }

    @PostMapping("/playlists/addSong/{id}")
    public void addSong(@RequestBody PlaylistRequest playlistRequest, @PathVariable @Min(0) Long id){
        playListService.addSongToPlaylist(playlistRequest, id);
    }

    @DeleteMapping("/playlists/removeSong/{id}")
    public void removeSong(@RequestBody PlaylistRequest playlistRequest, @PathVariable @Min(0) Long id){
        playListService.removeSongFromPlaylist(playlistRequest, id);
    }

    @DeleteMapping("/playlists/{id}")
    public void removePlaylist(@PathVariable @Min(0) Long id){
        playListService.deletePlaylist(id);
    }
}
