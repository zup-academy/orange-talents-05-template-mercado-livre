package com.mercado.mercadolivre.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.mercado.mercadolivre.entity.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("{security.jwt.secret}")
    private String secret;
    
    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();

        Date expiration = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.of("America/Sao_Paulo")).toInstant());

        return Jwts.builder()
            .setIssuer("API do Mercado Livre")
            .setSubject(logado.getId().toString())
            .setExpiration(expiration)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
