package onlineMusic.services;

import lombok.AllArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.entity.User;
import onlineMusic.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
@ExtendWith({MockitoExtension.class})
class AppServiceTest {

    @InjectMocks
    private AppService service;

//    @Autowired
//    @MockBean
//    private UserRepository userRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    void addUser() {
        User user = new User();
        service.addUser(new UserCreateUpdateRequest());

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }


}