package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.dto.user.UserResponse;
import onlineMusic.entity.User;
import onlineMusic.services.AppService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Registration", description = "the registration API")
@RestController
@AllArgsConstructor
public class RegisterController {
    private AppService service;


    @PostMapping("/register")
    public UserResponse addUser(@RequestBody UserCreateUpdateRequest user) {
        UserResponse userResponse = service.addUser(user);
        return userResponse;
    }

    @DeleteMapping("/register/{id}")
    public UserResponse deleteUserById(@PathVariable Long id){
        return service.deleteUserById(id);
    }

}