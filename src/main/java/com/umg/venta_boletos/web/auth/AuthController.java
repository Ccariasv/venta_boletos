package com.umg.venta_boletos.web.auth;

import com.umg.venta_boletos.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest req){
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        UserDetails u = (UserDetails) auth.getPrincipal();
        List<String> roles = u.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        String access = jwtService.generateAccess(u.getUsername(), roles);
        String refresh = jwtService.generateRefresh(u.getUsername(), roles);
        return ResponseEntity.ok(new TokenResponse(access, refresh, "Bearer"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody RefreshRequest req){
        if(!jwtService.isRefreshToken(req.refreshToken())) {
            return ResponseEntity.status(401).build();
        }
        var jws = jwtService.parse(req.refreshToken());
        var body = jws.getBody();                       // ✅
        String username = body.getSubject();
        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) body.get("roles");
        String newAccess  = jwtService.generateAccess(username, roles);
        String newRefresh = jwtService.generateRefresh(username, roles); // rotación
        return ResponseEntity.ok(new TokenResponse(newAccess, newRefresh, "Bearer"));
    }
}