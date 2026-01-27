package py.bootcamp.editorial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF protege contra ataques donde un sitio externo hace que tu navegador envíe un CRUD sin que vos quieras
                .csrf(csrf -> csrf.disable())

                // Para cada request HTTP que llegue, aplica estas reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/usuarios").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated()
                )
                //Spring Security verifica si viene el header Authorization: Basic y extrae usuario-contraseña
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
