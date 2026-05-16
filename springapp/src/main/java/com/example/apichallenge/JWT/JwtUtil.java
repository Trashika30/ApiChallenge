package com.example.apichallenge.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

@Component
public class JwtUtil {
    //generate token
    //validate toke
    //extract email
     @Value("${jwt.secret}")
    private String key;
     @Value("${jwt.expiration}")
    private long expiration;
    private Key skey;
     @PostConstruct
     public void init() {
         skey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
     }


     public String generateToken(String email){
         return Jwts.builder()
                 .setSubject(email)
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis()+expiration))
                 .signWith(skey, SignatureAlgorithm.HS256)
                 .compact();
     }

     public boolean validateToken(String token){
         try{
             extractEmail(token);
             return true;
         }
         catch(Exception e){
             return false;

         }
     }

     public String extractEmail(String token){
         return Jwts.parserBuilder()
                 .setSigningKey(skey)
                 .build()
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();
     }




}
