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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
@ExtendWith({MockitoExtension.class})
class AppServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void addUser() {
        UserCreateUpdateRequest user = new UserCreateUpdateRequest();
        user.setName("TestUser");
        user.setPassword("pass");
        user.setRoles("USER");
        service.addUser(new UserCreateUpdateRequest());

    }


}