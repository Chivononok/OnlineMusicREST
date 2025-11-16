package onlineMusic;

import onlineMusic.dto.album.AlbumRequest;
import onlineMusic.dto.performer.PerformerRequest;
import onlineMusic.services.AlbumService;
import onlineMusic.services.PerformerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        AlbumService albumService = context.getBean(AlbumService.class);

        AlbumRequest album1 = new AlbumRequest();
        album1.setName("Альбом1");
        album1.setYear(2001);

        AlbumRequest album2 = new AlbumRequest();
        album2.setName("Альбом2");
        album2.setYear(2002);

        albumService.add(album1);
        albumService.add(album2);

        PerformerService performerService = context.getBean(PerformerService.class, args);
        PerformerRequest performerRequest1 = new PerformerRequest();
        performerRequest1.setGroup(false);
        performerRequest1.setName("Певец1");

        PerformerRequest performerRequest2 = new PerformerRequest();
        performerRequest2.setGroup(true);
        performerRequest2.setName("Группа1");

        performerService.addPerformer(performerRequest1);
        performerService.addPerformer(performerRequest2);

    }


}
