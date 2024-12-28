package com.sms.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class Jwtutil {

    private static final String SECRET_KEY = "28597d2d1d2abedca0f52498be8c04291a1d2e9265134eee1fbbdb9d9654bc7e6b021a970d0440a66461c7dc49409a435e799dec946b3c2db9167a858766e02ffc30ac4811abafaa93d76badfdc59a33494f19c32b5108f47638b82aa3803c8778861f8c75dc6fa5110721d1138ad380233daf591a071e4caad790315baca699d13d6299ed29960f852229a36ba53f7e4dbed149eb289fcf013a81b94860970fb0376d9cea85866fd4a60f23f6314664d34894ea1470381065a6d2847da8fb24f77e524e7b2faeb01c76e0934c068ba6d570edd492dbff2dac5712626069d4cfbc1b57d3a9d0c1df023a0cf5fb54366bfd9c5a1f5438cc4f546c56622a95c9ad";
    // Base64-encoded secret key
    private static final long TOKEN_VALIDITY = 3600000;

    public static String generateToken(String username) {
        // Generate a secure secret key for HS256
        byte[] secretKeyBytes = SECRET_KEY.getBytes(); // Ensure it's 32 bytes or more
        if (secretKeyBytes.length < 32) {
            throw new IllegalArgumentException("The secret key must be at least 256 bits (32 bytes).");
        }
        Map<String,Object> claims=new HashMap<>();

        String token = Jwts.builder().setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(Keys.hmacShaKeyFor(secretKeyBytes))
                .compact();

        System.out.println("Generated Token: " + token); // Debug token creation
        return token;
    }
//
//    public static String generateToken(String username) {
//
//        String token= Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
//                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(SECRET_KEY)) // Decode Base64 key
//                .compact();
//        System.out.println("Generated Token: " + token);
//        return token;
//    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Claims extractAllClaims(String token) {
        // Decode the secret key to ensure it matches the format used during token generation
        byte[] secretKeyBytes = SECRET_KEY.getBytes(); // Ensure it's 32 bytes or more
        if (secretKeyBytes.length < 32) {
            throw new IllegalArgumentException("The secret key must be at least 256 bits (32 bytes).");
        }

        try {
            // Parse the token and extract the claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("Decoded Claims: " + claims); // Debug decoded claims
            return claims;
        } catch (Exception e) {
            System.err.println("Invalid or expired token: " + e.getMessage());
            throw new IllegalArgumentException("Invalid or expired token.", e);
        }

    }
}

