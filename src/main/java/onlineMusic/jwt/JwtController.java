package onlineMusic.jwt;

import lombok.RequiredArgsConstructor;
import onlineMusic.dto.user.UserCreateUpdateRequest;
import onlineMusic.model.JwtAuthenticationResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "oauth")
@RequiredArgsConstructor
public class JwtController {

    private final SignService signService;

    @RequestMapping(value = "sign-in", method = RequestMethod.POST)
    public JwtAuthenticationResponse signIn(@RequestBody UserCreateUpdateRequest userCreateUpdateRequest){
        return signService.signIn(userCreateUpdateRequest);
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public JwtAuthenticationResponse signUp(@RequestBody UserCreateUpdateRequest userCreateUpdateRequest){
        return signService.signUp(userCreateUpdateRequest);
    }
}
