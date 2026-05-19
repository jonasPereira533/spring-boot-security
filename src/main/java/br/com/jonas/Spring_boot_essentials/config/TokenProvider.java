package br.com.jonas.Spring_boot_essentials.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.expiration}")
    private Long expirationTime;

    @Value("${jwt.key}")
    private String key;

    // gerar um token
    public String gerarToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return buildToken (user.getUsername());
    }

    public String buildToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSingningKey())
                .compact();
    }

    private SecretKey getSingningKey() {
        return Keys.hmacShaKeyFor((key.getBytes()));
    }


    // validar um token
    public boolean isTokenValid(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }


    //extrair informações de um token
    public String getUsername (String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        // validar assinatura
        // validar expiração
        return Jwts.parser()
                .verifyWith(getSingningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
}
