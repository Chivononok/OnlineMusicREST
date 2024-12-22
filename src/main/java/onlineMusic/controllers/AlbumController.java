package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import onlineMusic.dto.album.AlbumRequest;
import onlineMusic.dto.album.AlbumResponse;
import onlineMusic.services.AlbumService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Album", description = "the album API")
@RestController
@Validated
@RequiredArgsConstructor
public class AlbumController {
    public final AlbumService albumService;

    @Operation(summary = "return all albums",
            description = "fetches all albums entities and their data from data source")
    @ApiResponse(responseCode = "200", description = "successful  operation")
    @GetMapping("/album/all")
    public List<AlbumResponse> getAll(){
        return albumService.getAll();
    }

    @GetMapping("/album/{id}")
    public AlbumResponse getById(@PathVariable @Min(0) Long id){
        return albumService.getById(id);
    }

    @PostMapping("/album/add")
    public void add(@Valid @RequestBody AlbumRequest albumRequest){
        albumService.add(albumRequest);
    }

    @DeleteMapping("/album/{id}")
    public void deleteById(@PathVariable @Min(0) Long id){
        albumService.removeById(id);
    }
}
