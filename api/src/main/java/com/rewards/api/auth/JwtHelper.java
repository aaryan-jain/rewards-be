package com.rewards.api.auth;

import com.rewards.api.User.User;
import com.rewards.api.auth.apikey.ApiKeyEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "wBM3oq4P+fNtirNfYJOwNw==wBM3oq4P+fNtirNfYJOwNw==wBM3oq4P+fNtirNfYJOwNw==wBM3oq4P+fNtirNfYJOwNw==wBM3oq4P+fNtirNfYJOwNw==";

    private String getSubjectFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //generate token for user
    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUserId());
    }

    public String generateTokenForApiKey(String client) {
        Map<String, Object> claims = new HashMap<>();
        String subject = client + "=====" + new Date().toString();
        return doGenerateToken(claims, subject);
    }
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (25L * 365L * 24L * 60L * 60L * 1000L)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getClientSubjectFromToken(String token) {
        return getSubjectFromToken(token);
    }

    public Boolean validateTokenForClient(String token, ApiKeyEntity apiKeyEntity) {
        final String client = getClientSubjectFromToken(token);
        String clientActual = client.split("=====")[0];
        return (apiKeyEntity.getClient().equals(clientActual) && apiKeyEntity.getIsDisabled() == 0);
    }
}
