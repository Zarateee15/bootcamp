package py.bootcamp.editorial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF protege contra ataques donde un sitio externo hace que tu navegador envíe un CRUD sin que vos quieras
                .csrf(AbstractHttpConfigurer::disable)

                //
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Para cada request HTTP que llegue, aplica estas reglas de autorización
                .authorizeHttpRequests(auth -> auth

                        // Loggearse
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                        // User puede hacer solo GET
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")

                        // Admin puede hacer CRUD
                        .requestMatchers("/api/usuarios", "/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

                        .anyRequest().permitAll()
                )

                // oauth2ResourceServer().jwt() hace que se valide el token con jwtDecoder en cada request con header
                // jwtAuthenticationConverter(...) controla cómo se traduce lo del token a roles dentro de Spring Security
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
                );

        return http.build();
    }

    /*
        - Llama a DbUserDetailsService.java para cargar objeto Usuario
        - Usa el PasswordEncoder para comparar contraseñas
        - Si coincide devuelve un Authentication autenticado; si no lanza error (401)
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Se usa en cada request con Bearer token para convertir roles del JWT a permisos reales en Spring
    @Bean
    JwtAuthenticationConverter jwtAuthConverter() {
        var gac = new JwtGrantedAuthoritiesConverter();
        gac.setAuthoritiesClaimName("roles");
        gac.setAuthorityPrefix("ROLE_");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(gac);
        return converter;
    }
}
