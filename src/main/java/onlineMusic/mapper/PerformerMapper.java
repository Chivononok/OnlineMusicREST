package onlineMusic.mapper;

import onlineMusic.dto.performer.PerformerRequest;
import onlineMusic.dto.performer.PerformerResponse;
import onlineMusic.entity.Performer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerformerMapper {
    public Performer toPerformer(PerformerRequest performerRequest);
    public PerformerResponse toPerformerResponse(Performer performer);
}
