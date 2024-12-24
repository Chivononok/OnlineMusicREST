package onlineMusic.dto.album;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlbumRequest {
    @NotBlank
    private String name;

    private Integer year;
}
