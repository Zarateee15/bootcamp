package py.bootcamp.editorial.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final AuthServiceImp service;

    public AuthController(AuthenticationManager authManager, AuthServiceImp service) {
        this.authManager = authManager;
        this.service = service;
    }

    @PostMapping("/login")
    public AuthDto.LoginResponse login(@RequestBody AuthDto.LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );
        return service.login(auth);
    }
}
