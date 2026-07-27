package br.costa.AghataEscada.security.service;


import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.security.dto.Request.LoginEmployerRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private SecretKey  getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public String generateToken(EmployeeEntity dto) {
        return Jwts.builder()
                .subject(dto.getCltNumber())
                .claim("id", dto.getEmployeId())
                .claim("name", dto.getName())
                .claim("role", dto.getEnumEmployee().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigninKey())
                .compact();
    }

    public String extractCltNumber(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, EmployeeEntity dto) {
        String cltNumber = extractCltNumber(token);
        return cltNumber.equals(dto.getCltNumber()) && !isTokenExpired(token);
    }
}
