package com.umg.venta_boletos.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {
    private final JwtProperties props;
    private final SecretKey key;

    public JwtService(JwtProperties props){
        this.props = props;
        this.key = Keys.hmacShaKeyFor(props.secret().getBytes());
    }

    public String generateAccess(String username, List<String> roles){
        return buildToken(username, roles, props.accessTtlSeconds(), "access");
    }

    public String generateRefresh(String username, List<String> roles){
        return buildToken(username, roles, props.refreshTtlSeconds(), "refresh");
    }

    private String buildToken(String sub, List<String> roles, long ttlSec, String typ){
        Instant now = Instant.now();
        return Jwts.builder()
                // Si dejas header typ, será "JWT"; el tipo lo marcamos en CLAIMS:
                .setSubject(sub)
                .addClaims(Map.of("roles", roles, "typ", typ)) // <-- claim typ
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttlSec)))
                .signWith(key, SignatureAlgorithm.HS512)       // API 0.11.5
                .compact();
    }

    public Jws<Claims> parse(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)        // API 0.11.5
                .build()
                .parseClaimsJws(token);
    }

    public boolean isRefreshToken(String token){
        try {
            var jws = parse(token);
            Object typ = jws.getBody().get("typ");    // claim, no header
            return "refresh".equals(typ);
        } catch (JwtException e){
            return false;
        }
    }
}
