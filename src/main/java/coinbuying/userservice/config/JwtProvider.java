package coinbuying.userservice.config;

import coinbuying.userservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String createJwtToken(User user, long interval) {
        Date expiration = new Date(System.currentTimeMillis() + interval);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        return Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("userType", user.getUserType())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiration)
                .compact();
    }
}
