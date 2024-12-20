package onlineMusic.services;

import lombok.RequiredArgsConstructor;
import onlineMusic.dto.performer.PerformerRequest;
import onlineMusic.dto.performer.PerformerResponse;
import onlineMusic.entity.Performer;
import onlineMusic.exceptions.NotFoundException;
import onlineMusic.mapper.PerformerMapper;
import onlineMusic.repository.PerformerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformerService {
    private final PerformerRepository performerRepository;
    private final PerformerMapper performerMapper;

    public void addPerformer(PerformerRequest performerRequest){
        Performer performer = performerMapper.toPerformer(performerRequest);
        performerRepository.save(performer);
    }

    public List<PerformerResponse> getAll(){
        return performerRepository.findAll().stream()
                .map(performer -> performerMapper.toPerformerResponse(performer)).collect(Collectors.toList());
    }

    public PerformerResponse getById(Long id){
        return performerMapper.toPerformerResponse(performerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Исполнитель с Id=" + id + " не найден")));
    }

    public void deleteById(Long id){
        performerRepository.deleteById(id);
    }
}
