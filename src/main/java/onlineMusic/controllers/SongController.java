package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import onlineMusic.dto.song.SongRequest;
import onlineMusic.dto.song.SongResponse;
import onlineMusic.repository.SongRepository;
import onlineMusic.services.SongService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Song", description = "the song API")
@RestController
@RequiredArgsConstructor
public class SongController {
    private final SongRepository songRepository;
    private final SongService songService;


    @GetMapping("/songs")
    public List<SongResponse> all(@RequestParam(required = false, defaultValue = "0") int page,
                           @RequestParam(required = false, defaultValue = "5") int size){
        return songService.getAll(PageRequest.of(page, size));
    }

    @PostMapping("/songs")
    public void newSong(@RequestBody SongRequest newSong){
        songService.addSong(newSong);
    }

    @GetMapping("/songs/{id}")
    public SongResponse one(@PathVariable Long id){
        return songService.getById(id);
    }

    @PutMapping("/songs/{id}")
    public SongResponse replaceSong(@RequestBody SongRequest newSong, @PathVariable Long id){
        return songService.replaceSong(newSong, id);
    }

    @DeleteMapping("songs/{id}")
    void deleteSong(@PathVariable Long id){
        songRepository.deleteById(id);
    }
}
