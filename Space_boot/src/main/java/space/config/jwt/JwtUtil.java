package space.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

public class JwtUtil {
    private static final String JWT_KEY = "6E5A7234753778214125442A472D4B6150645367556B58703273357638792F42";
    private static final int JWT_EXPIRATION = 36000000;
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private JwtUtil() {
    }

    // Génération du jeton pour un utilisateur
    public static String generate(Authentication authentication) {
    SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
    Date now = new Date();

    String role = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse("ROLE_UTILISATEUR"); // Par défaut

    return Jwts.builder()
            .setSubject(authentication.getName()) // username
            .claim("role", role) // 👈 On ajoute le rôle ici
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + JWT_EXPIRATION))
            .signWith(key)
            .compact();
}

    public static Optional<String> getUsername(String token) {
        if (token == null || token.isBlank()) {
            return Optional.empty();
        }

        try {
            log.debug("Validating token then claim username ...");

            Claims claims = Jwts.parserBuilder().setSigningKey(JWT_KEY.getBytes(StandardCharsets.UTF_8)) // On redonne																			// validation
                    .build() // On fabrique le contenu du jeton à extraire
                    .parseClaimsJws(token) // On indique le jeton à parser
                    .getBody(); // On récupère le "contenu" du jeton

            return Optional.ofNullable(claims.getSubject()); // On retourne le subject (le nom d'utilisateur) contenu
            // dans le jeton
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return Optional.empty();
    }

    public static Optional<String> getRole(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JWT_KEY.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.ofNullable(claims.get("role", String.class));
        } catch (JwtException e) {
            log.error("JWT role extraction error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
