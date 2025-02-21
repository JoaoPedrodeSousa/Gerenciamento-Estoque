package com.GerenciadorEstoque.GerenEstoque.infra.security;

import com.GerenciadorEstoque.GerenEstoque.Models.User;
import com.GerenciadorEstoque.GerenEstoque.Utils.JWTConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JWTGenerator {

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTConstants.JWT_SECRET);
            String token = JWT.create()
                    .withIssuer("AuthenticationAPI")
                    .withSubject(user.getUsername())
                    .withExpiresAt(Instant.ofEpochSecond(JWTConstants.JWT_EXPIRATION))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error Token Generator", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTConstants.JWT_SECRET);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }
}
