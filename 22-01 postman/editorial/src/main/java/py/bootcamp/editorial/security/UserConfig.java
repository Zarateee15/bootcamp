package py.bootcamp.editorial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean   //  Spring Segurity lo usa para validar la contraseña (BCrypt)
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
