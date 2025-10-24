package com.umg.venta_boletos.repo;


import com.umg.venta_boletos.domain.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
}
