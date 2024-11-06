package com.apuliadigital.gestionaleautosalone.common;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey = "keb5nOfeC9PlMXJIez0MghFB5zdCdskJzqNwcBukOejNiUImPOZCuPz9JJO8HhePHI6nNUDcxbhxBFLxWwpTgUR0D8a5nNVHd1TOBDXvlQX4gbzMWXz4VUagkoVYRoON3QojvlJB8wTRQsHVlJg24LWSlb2nntYROwcYHlroEDEPYvLF8XxTb8TkbaAvjnqzkmdI0iXcd1hFxRwUGGmTYDZmi8MbF7KIaH9KCYWSzmj6OwHw9bVjqSg1gxAfApOk";
    private final long expirationTime = 3600000;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }




}
