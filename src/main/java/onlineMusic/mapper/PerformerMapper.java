package onlineMusic.mapper;

import onlineMusic.dto.performer.PerformerRequest;
import onlineMusic.dto.performer.PerformerResponse;
import onlineMusic.entity.Performer;
import org.springframework.stereotype.Component;

@Component
public class PerformerMapper {
    public Performer toPerformer(PerformerRequest performerRequest){
        Performer performer = new Performer();
        performer.setName(performerRequest.getName());
        performer.setGroup(performerRequest.isGroup());
        return performer;
    }

    public PerformerResponse toPerformerResponse(Performer performer){
        PerformerResponse performerResponse = new PerformerResponse();
        performerResponse.setGroup(performer.isGroup());
        performerResponse.setName(performer.getName());
        return performerResponse;
    }
}
