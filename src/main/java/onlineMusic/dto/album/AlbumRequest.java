package onlineMusic.dto.album;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlbumRequest {
    @NotBlank
    private String name;

    private Integer year;
}
