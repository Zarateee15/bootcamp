package py.bootcamp.editorial.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import py.bootcamp.editorial.usuario.UsuarioRepository;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repo;

    public DbUserDetailsService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = repo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado: " + username
                ));

        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }
}
