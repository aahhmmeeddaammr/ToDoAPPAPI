package com.API.ToDoAPP.Services;

import com.API.ToDoAPP.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private static final   String SECRET_KEY="acbe98daf058112622d271f8bba07fe1079da4da26f0532b62feaf4e67bf166b";
    public String ExtractUserEmailFromJWT(String token) {
        Claims claims = ExtractClaimsFromJWT(token);
        return claims.get("email", String.class);
    }

    public Claims ExtractClaimsFromJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String GenerateJwtToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        if(user.getRole() == null){
            claims.put("role", "Student");
        }else{
            claims.put("role" , user.getRole().name());
        }
        claims.put("id" , user.getId());
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000*60*24)).signWith(getSignInKey() , SignatureAlgorithm.HS256).compact();
    }

    public boolean validateJwtToken(String token, UserDetails userDetails) {
        try {
            String userEmail = ExtractUserEmailFromJWT(token);
            return userEmail.equals(((User) userDetails).getEmail()) && !IsTokenExpired(token);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    private boolean IsTokenExpired(String token) throws IllegalArgumentException {
        Date expirationDate = ExtractExpristion(token);
        if (expirationDate.before(new Date())) {
            throw new IllegalArgumentException("Expired Token: The token has already expired.");
        }
        return false;
    }

    private Date ExtractExpristion(String token) {
        Claims claims = ExtractClaimsFromJWT(token);
        return  claims.getExpiration();
    }

}
