package com.cahier.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "a1B2c3D4e5F6g7H8i9J0kL1mN2oP3qR4sT5uV6wX7yZ8@#$-_=+!";
    private static final long EXPIRATION_TIME = 86400000; // 1 jour en ms

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY);
    }

    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(getAlgorithm());
    }

    public String extractEmail(String token) {
        return decodeToken(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            decodeToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private DecodedJWT decodeToken(String token) {
        return JWT.require(getAlgorithm()).build().verify(token);
    }
}
