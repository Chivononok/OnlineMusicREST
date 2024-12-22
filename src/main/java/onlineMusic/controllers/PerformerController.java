package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import onlineMusic.dto.performer.PerformerRequest;
import onlineMusic.dto.performer.PerformerResponse;
import onlineMusic.services.PerformerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Performer", description = "the performer API")
@RestController
@RequiredArgsConstructor
public class PerformerController {
    private final PerformerService performerService;

    @GetMapping("/performer/all")
    public List<PerformerResponse> getAll(){
        return performerService.getAll();
    }

    @GetMapping("/performer/{id}")
    public PerformerResponse getById(@PathVariable @Min(0) Long id){
        return performerService.getById(id);
    }

    @PostMapping("/performer/add")
    public void addPerformer(@RequestBody PerformerRequest performerRequest){
        performerService.addPerformer(performerRequest);
    }

    @DeleteMapping("/performer/{id}")
    public void deleteById(@PathVariable Long id){
        performerService.deleteById(id);
    }
}
