package py.bootcamp.editorial.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AuthServiceImp implements AuthService {

    private final JwtEncoder encoder;

    @Value("${jwt.exp-seconds}")
    private long expSeconds;

    public AuthServiceImp(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public AuthDto.LoginResponse login (Authentication auth) {
        Instant now = Instant.now();

        // Obtiene el/los roles del usuario
        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) //  SimpleGrantedAuthority("ROLE_USER") → "ROLE_USER"
                .filter(a -> a.startsWith("ROLE_"))// Deja solo las que empiezan con "ROLE_".
                .map(a -> a.substring("ROLE_".length()))// Le quita "ROLE_" a cada string.
                .toList(); // Quedaría ["ADMIN"]

        // Contruye el contenido <claims> del JWT
        JwtClaimsSet claims = JwtClaimsSet.builder() // Builder para armar los claims
                .issuer("editorial-api") // Quien emite el token
                .issuedAt(now) // En que momento se genera el token
                .expiresAt(now.plusSeconds(expSeconds)) // Cuando vence el token
                .subject(auth.getName()) // De quien es el token (username)
                .claim("roles", roles) // Contiene los roles del usuario
                .build(); // Termina de construir

        // Genera el JWT string con los claims anteriores y el jwt.key
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();

        return new AuthDto.LoginResponse(token, "Bearer", expSeconds, roles);
    }
}
