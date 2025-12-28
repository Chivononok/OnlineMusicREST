package onlineMusic.repository;

import onlineMusic.entity.Performer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PerformerRepositoryTest {
    @Autowired
    private PerformerRepository performerRepository;

    @Test
    public void saveANdFindByIdAndDeleteByIdPerformer(){
        Performer performerTest = performerRepository.save(getPerformer("ИмяТест"));
        Long id = performerTest.getId();
        Optional<Performer> optionalPerformer = performerRepository.findById(id);
        assertTrue(optionalPerformer.isPresent());
        assertEquals("ИмяТест", optionalPerformer.get().getName());
        performerRepository.deleteById(id);
        optionalPerformer = performerRepository.findById(id);
        assertFalse(optionalPerformer.isPresent());
    }


    private Performer getPerformer(String name){
        Performer performer = new Performer();
        performer.setName(name);
        performer.setGroup(true);
        return  performer;
    }
}
