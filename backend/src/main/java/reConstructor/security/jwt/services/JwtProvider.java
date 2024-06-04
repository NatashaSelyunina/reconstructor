package reConstructor.security.jwt.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reConstructor.domain.entities.Moderator;
import reConstructor.domain.entities.Restaurant;
import reConstructor.domain.entities.Staff;
import reConstructor.exception_handling.exceptions.access.jwt.*;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;

    private JwtProvider(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    public String generateAccessTokenForModerator(@Nonnull Moderator moderator) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault())
                .toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .subject(moderator.getEmail())
                .expiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("role", "ROLE_MODERATOR")
                .claim("name", moderator.getName())
                .claim("surname", moderator.getSurname())
                .claim("restaurantsId", getListRestaurantId(moderator))
                .compact();
    }

    public String generateAccessTokenForStaff(@Nonnull Staff staff) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault())
                .toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .subject(staff.getCode())
                .expiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("role", staff.getRole().getName())
                .claim("name", staff.getName())
                .claim("surname", staff.getSurname())
                .claim("restaurantId", staff.getRestaurant().getId())
                .compact();
    }

    public String generateRefreshTokenForModerator(@Nonnull Moderator moderator) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(7).atZone(ZoneId.systemDefault())
                .toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);

        return Jwts.builder()
                .subject(moderator.getEmail())
                .expiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .claim("role", "ROLE_MODERATOR")
                .claim("name", moderator.getName())
                .claim("surname", moderator.getSurname())
                .claim("restaurantsId", getListRestaurantId(moderator))
                .compact();
    }

    public String generateRefreshTokenForStaff(@Nonnull Staff staff) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(7).atZone(ZoneId.systemDefault())
                .toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);

        return Jwts.builder()
                .subject(staff.getCode())
                .expiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .claim("role", staff.getRole().getName())
                .claim("name", staff.getName())
                .claim("surname", staff.getSurname())
                .claim("restaurantId", staff.getRestaurant().getId())
                .compact();
    }

    public boolean validateAccessToken(@Nonnull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@Nonnull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private boolean validateToken(@Nonnull String token, @Nonnull SecretKey secret) {
        try {
            Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException expiredException) {
            return false;
        } catch (UnsupportedJwtException unsupportedException) {
            throw new UnsupportedTokenException("Unsupported token", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (MalformedJwtException malformedException) {
            throw new InvalidFormatTokenException("Malformed token", HttpStatus.BAD_REQUEST);
        } catch (SignatureException signatureException) {
            throw new InvalidTokenSignatureException("Invalid signature", HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            throw new InvalidTokenException("invalid token", HttpStatus.UNAUTHORIZED);
        }
    }

    public Claims getAccessClaims(@Nonnull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@Nonnull String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(@Nonnull String token, @Nonnull SecretKey secret) {
        try {
            return Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private List<Long> getListRestaurantId(Moderator moderator) {
        return new ArrayList<>(moderator.getRestaurants()
                .stream()
                .map(Restaurant::getId)
                .toList());
    }
}
