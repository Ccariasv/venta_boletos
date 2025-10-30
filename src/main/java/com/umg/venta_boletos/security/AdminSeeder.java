package com.umg.venta_boletos.security;

import com.umg.venta_boletos.domain.seguridad.*;
import com.umg.venta_boletos.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component @Profile("dev")
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {
    private final UsuarioRepo usuarios;
    private final RolSistemaRepo roles;
    private final PasswordEncoder encoder;

    @Override public void run(String... args){
        roles.findByCodigo("ADMIN").orElseGet(() -> {
            var r = new RolSistema(); r.setCodigo("ADMIN"); r.setDescripcion("Administrador"); return roles.save(r);
        });
        usuarios.findByUsername("admin").orElseGet(() -> {
            var u = new Usuario();
            u.setUsername("admin");
            u.setPasswordHash(encoder.encode("admin123"));
            u.setRol(roles.findByCodigo("ADMIN").orElseThrow());
            u.setActivoFlag("S");
            return usuarios.save(u);
        });
    }
}
