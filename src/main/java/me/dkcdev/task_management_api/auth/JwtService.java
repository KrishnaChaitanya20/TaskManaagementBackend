package me.dkcdev.task_management_api.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.dkcdev.task_management_api.users.models.User;

@Service
public class JwtService {

    private static final String SECRET = "eedb3897b493801be40f9fdad2d8c918acd28367ab304cbb851b27e7e1878943";

    private String secretKey;

    public JwtService(){
        // secretKey = generateSecretKey();
        secretKey = SECRET;
    }

    // public String generateSecretKey() {
    //     try {
    //         KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
    //         SecretKey secretKey = keyGen.generateKey();
    //         System.out.println("Secret Key : " + secretKey.toString());
    //         return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    //     } catch (NoSuchAlgorithmException e) {
    //         throw new RuntimeException("Error generating secret key", e);
    //     }
    // }

    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("orgId",user.getOrganization().getOrganizationId());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
