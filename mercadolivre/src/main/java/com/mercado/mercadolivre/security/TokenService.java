package com.mercado.mercadolivre.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    // @Value("{security.jwt.expiration}")
    // private String expiration;

    // @Value("{security.jwt.secret}")
    // private String secret;
    
    public String gerarToken(Authentication authentication) {
        // Usuario logado = (Usuario) authentication.getPrincipal();

        Date expiration = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.of("America/Sao_Paulo")).toInstant());

        return Jwts.builder()
            .setIssuer("API do Mercado Livre")
            .setSubject("logado.getId().toString()")
            .setExpiration(expiration)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.ES256, "secret")
            .compact();
    }
}
