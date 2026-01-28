package py.bootcamp.editorial.auth;

import java.util.List;

public class AuthDto {

    public record LoginRequest(
            String username,
            String password) {
    }

    public record LoginResponse(
            String token,
            String type,
            long expireInSeconds,
            List<String> roles
    ) {
    }
}
