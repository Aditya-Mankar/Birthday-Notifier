package com.birthdaynotifier.utility;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.model.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTUtility {

    @Value("${jwt.secret}")
    private String secret_key;

    @Value("${jwt.validity}")
    private String token_validity;

    public String generateToken(CustomUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.username, userDetails.getUsername());
        claims.put(Constants.email_id, userDetails.getEmailId());
        claims.put(Constants.role, convertAuthoritiesToString(userDetails.getAuthorities()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(token_validity) * 1000))
                .signWith(SignatureAlgorithm.HS512, secret_key).compact();
    }

    public Map<String, Object> getClaimValues(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret_key)
                .parseClaimsJws(token)
                .getBody();

        Map<String, Object> claimValues = new HashMap<>();
        claimValues.put(Constants.username, claims.get(Constants.username));
        claimValues.put(Constants.email_id, claims.get(Constants.email_id));
        claimValues.put(Constants.role, claims.get(Constants.role));
        claimValues.put(Constants.expiration, claims.getExpiration());

        return claimValues;
    }

    public Boolean validateToken(String token, CustomUser userDetails) {
        Map<String, Object> claimValues = getClaimValues(token);
        Date expiration = (Date) claimValues.get(Constants.expiration);

        return (
                userDetails.getUsername().equals(claimValues.get(Constants.username)) &&
                userDetails.getEmailId().equals(claimValues.get(Constants.email_id)) &&
                convertAuthoritiesToString(userDetails.getAuthorities()).equals(claimValues.get(Constants.role)) &&
                !expiration.before(new Date())
        );
    }

    public String getUsernameFromToken(String token) {
        Map<String, Object> claimValues = getClaimValues(token);
        return String.valueOf(claimValues.get(Constants.username));
    }

    public String convertAuthoritiesToString(Collection<? extends GrantedAuthority> authorities) {
        return Arrays.toString(authorities.toArray());
    }

}
