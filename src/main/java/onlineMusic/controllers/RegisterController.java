package onlineMusic.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.dto.user.UserDeleteRequest;
import onlineMusic.dto.user.UserResponse;
import onlineMusic.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Registration", description = "the registration API")
@RestController
@Validated
@AllArgsConstructor
public class RegisterController {
    private UserService service;


    @PostMapping("/register")
    public UserResponse addUser(@Valid @RequestBody UserCreateUpdateRequest user) {
        UserResponse userResponse = service.addUser(user);
        return userResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/register/{id}")
    public UserResponse deleteUserById(@PathVariable @Min(0) Long id){
        return service.deleteUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/register")
    public UserResponse deleteByName(@RequestBody UserDeleteRequest userDeleteRequest){
        return service.deleteByName(userDeleteRequest);
    }

}