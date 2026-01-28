package py.bootcamp.editorial.auth;

import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthDto.LoginResponse login (Authentication auth);
}
