package onlineMusic.controllers;

import lombok.NoArgsConstructor;
import onlineMusic.dto.user.UserDeleteRequest;
import onlineMusic.entity.User;
import onlineMusic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@NoArgsConstructor
class RegisterControllerTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    void deleteByName() throws Exception {
        UserDeleteRequest userDeleteRequest = new UserDeleteRequest();
        userDeleteRequest.setName("User7");
        List<User> users = userRepository.findAll();
//        mockMvc.perform(delete("/register").content(userDeleteRequest.toString()))
//                .andExpect(status().isOk());
    }
}