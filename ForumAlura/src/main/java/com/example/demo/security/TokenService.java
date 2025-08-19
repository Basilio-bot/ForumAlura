package com.example.demo.security;

import com.example.demo.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    private final String secret;
    private final long expiration;

    public TokenService(
            @Value("${forum.jwt.secret}") String secret,
            @Value("${forum.jwt.expiration}") long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setIssuer("API Fórum Hub")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}