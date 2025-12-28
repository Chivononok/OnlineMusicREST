package onlineMusic.services;

import onlineMusic.dto.performer.PerformerRequest;
import onlineMusic.dto.performer.PerformerResponse;
import onlineMusic.entity.Performer;
import onlineMusic.exceptions.NotFoundException;
import onlineMusic.mapper.PerformerMapper;
import onlineMusic.repository.PerformerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PerformerServiceTest {

    @InjectMocks
    private PerformerService performerService;
    @Mock
    private PerformerRepository performerRepository;
    @Mock
    private PerformerMapper performerMapper;

    @Test
    public void addPerformer_when_performerIsNew(){
        Performer performer = getTestPerformer("Имя");
        when(performerMapper.toPerformer(any())).thenReturn(performer);
        when(performerRepository.save(any())).thenReturn(performer);
        Performer actual = performerService.addPerformer(new PerformerRequest());
        Performer expected = getExpectedPerformer("Имя");
        assertEquals(actual.getName(), expected.getName());
        verify(performerRepository).save(performer);
    }

    @Test
    public void getById_when_performerNotFound(){
        Long id = 12L;
        lenient().when(performerMapper.toPerformerResponse(any())).thenReturn(new PerformerResponse());
        when(performerRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(NotFoundException.class, () -> performerService.getById(id));
        assertEquals(exception.getMessage(), "Исполнитель с Id=" + id + " не найден");
    }

    @Test
    void getAll_when_performersExist() {
        Performer performer1 = getTestPerformer("Имя1");
        Performer performer2 = getTestPerformer("Имя2");

        PerformerResponse response1 = new PerformerResponse();
        response1.setName("Имя1");
        PerformerResponse response2 = new PerformerResponse();
        response2.setName("Имя2");

        when(performerRepository.findAll()).thenReturn(List.of(performer1, performer2));
        when(performerMapper.toPerformerResponse(performer1)).thenReturn(response1);
        when(performerMapper.toPerformerResponse(performer2)).thenReturn(response2);

        List<PerformerResponse> actualResponses = performerService.getAll();

        assertEquals(2, actualResponses.size());
        assertEquals("Имя1", actualResponses.get(0).getName());
        assertEquals("Имя2", actualResponses.get(1).getName());

        verify(performerRepository).findAll();
    }

    private Performer getTestPerformer(String name){
        Performer performer = new Performer();
        performer.setName(name);
        return performer;
    }
    private Performer getExpectedPerformer(String name){
        Performer performer = new Performer();
        performer.setName(name);
        return performer;
    }
}
