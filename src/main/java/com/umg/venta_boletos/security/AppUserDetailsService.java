package com.umg.venta_boletos.security;

import com.umg.venta_boletos.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe usuario: " + username));
        // Mapea roles_sistema.codigo → ROLE_*
        String codigo = u.getRol().getCodigo();
        List<SimpleGrantedAuthority> auths = List.of(new SimpleGrantedAuthority("ROLE_" + codigo));
        boolean enabled = "S".equalsIgnoreCase(u.getActivoFlag());
        return User.withUsername(u.getUsername())
                .password(u.getPasswordHash())
                .authorities(auths)
                .disabled(!enabled)
                .build();
    }
}
