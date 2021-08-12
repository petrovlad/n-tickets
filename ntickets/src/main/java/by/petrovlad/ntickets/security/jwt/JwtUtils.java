package by.petrovlad.ntickets.security.jwt;

import by.petrovlad.ntickets.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;

@Component
@Slf4j
public class JwtUtils {

    @Value("${by.petrovlad.ntickets.security.jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${by.petrovlad.ntickets.security.jwt.jwtExpirationMs}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        long now = Instant.now().toEpochMilli();
        long expiredAt = now + jwtExpiration;

        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date(now))
                .setExpiration(new Date(expiredAt)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
