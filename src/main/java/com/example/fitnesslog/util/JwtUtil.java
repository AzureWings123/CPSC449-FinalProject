package com.example.fitnesslog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate JWT with userId stored in "sub"
    public String generateToken(String userId, String email) {
        return Jwts.builder()
                .subject(userId)
                .claim("email", email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    // Extract userId from "sub"
    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extract email from custom claim
    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    // Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        String extractedEmail = extractEmail(token);
        return extractedEmail.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Parse claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
