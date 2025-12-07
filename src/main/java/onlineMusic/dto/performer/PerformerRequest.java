package onlineMusic.dto.performer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PerformerRequest {
    @NotBlank
    private String name;
    private boolean isGroup;
}
