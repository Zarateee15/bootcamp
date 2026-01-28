package py.bootcamp.editorial.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import py.bootcamp.editorial.usuario.UsuarioRepository;

@Service
public class DbUserDetailsService implements UserDetailsService { // Autenticacion

    private final UsuarioRepository repo;

    // Consulta tabla Usuario
    public DbUserDetailsService(UsuarioRepository repo) {
        this.repo = repo;
    }

    // Cada que Spring necesite autenticar a alguien por username/password
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = repo.findByUsername(username) // Trae el usuario de la BD
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado: " + username
                ));

        String rol = u.getRol().getNombre(); // Trae el rol

        /*
            Crea un objeto que Spring Security entiende:
                - Username: el del usuario
                - Password: el password hasheado guardado en BD (BCrypt)
                - Roles: transforma "USER" en autoridad "ROLE_USER" o "ADMIN" → "ROLE_ADMIN" automaticamente
         */
        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(rol)
                .build();
    }
}
