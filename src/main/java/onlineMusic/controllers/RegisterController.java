package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.dto.user.UserDeleteRequest;
import onlineMusic.dto.user.UserResponse;
import onlineMusic.entity.User;
import onlineMusic.services.AppService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Registration", description = "the registration API")
@RestController
@Validated
@AllArgsConstructor
public class RegisterController {
    private AppService service;


    @PostMapping("/register")
    public UserResponse addUser(@Valid @RequestBody UserCreateUpdateRequest user) {
        UserResponse userResponse = service.addUser(user);
        return userResponse;
    }

    @DeleteMapping("/register/{id}")
    public UserResponse deleteUserById(@PathVariable Long id){
        return service.deleteUserById(id);
    }

//    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/register")
    public UserResponse deleteByName(@RequestBody UserDeleteRequest userDeleteRequest){
        return service.deleteByName(userDeleteRequest);
    }

}