package com.agent.agent.utility;


import com.agent.agent.modele.Agent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility {
    private final String JWT_SECRET;

    private final Integer JWT_TTL;

    @Autowired
    public JwtUtility(@Value("jwt.secret") String jwt_secret,
                      @Value("#{ T(java.lang.Integer).parseInt('${jwt.ttl}')}") Integer jwt_ttl) {
        JWT_SECRET = jwt_secret;
        JWT_TTL = jwt_ttl;
    }

    public String generateToken(Agent agent) {
        String subject = getSubjectFromUser(agent);

        Map<String, Object> claims = new HashMap<>();
        // claims.put("authorities", user.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * JWT_TTL))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, Agent agent) {
        final String subject = extractSubject(token);
        return (subject.equals(getSubjectFromUser(agent)) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String getSubjectFromUser(Agent agent) {
        return agent.getId_agent().toString();
    }
}
