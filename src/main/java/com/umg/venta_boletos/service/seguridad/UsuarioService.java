package com.umg.venta_boletos.service.seguridad;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.seguridad.Usuario;
import com.umg.venta_boletos.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepo repo;
    private final org.springframework.security.crypto.password.PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Usuario getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Usuario "+id+" no existe"));
    }

    @Transactional
    public Usuario save(Usuario u){
        if(u.getActivoFlag()==null) u.setActivoFlag("S");
        var raw = u.getPasswordHash();
        if(raw != null && !raw.startsWith("$2a$") && !raw.startsWith("$2b$") && !raw.startsWith("{bcrypt}")){
            u.setPasswordHash(encoder.encode(raw));
        }
        return repo.save(u);
    }
}