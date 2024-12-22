package onlineMusic.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import onlineMusic.dto.user.UserDeleteRequest;
import onlineMusic.entity.User;
import onlineMusic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AllArgsConstructor
@TestPropertySource("classpath:application-test.properties")
class RegisterControllerTest {

    private MockMvc mockMvc;
    private UserRepository userRepository;

    @Test
    void deleteByName() throws Exception {
        UserDeleteRequest userDeleteRequest = new UserDeleteRequest();
        userDeleteRequest.setName("User7");
        List<User> users = userRepository.findAll();
        mockMvc.perform(delete("/register").content(userDeleteRequest.toString()))
                .andExpect(status().isOk());
    }
}