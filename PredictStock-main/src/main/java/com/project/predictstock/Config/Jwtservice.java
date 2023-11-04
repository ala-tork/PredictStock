package com.project.predictstock.Config;

import com.project.predictstock.Entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class Jwtservice {


    private String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private long jwtExpiration = 86400000;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            User userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            User userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, User userDetails) {
        final String username = extractUsername(token);
        boolean equals = username.equals(userDetails.getEmail());
        boolean tokenExpired = isTokenExpired(token);
        return equals && !tokenExpired;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
/*
    private  static final String SECRET_KEY = "B374A26A71490437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CCF";

    //extract email from jwt
    public String extractUserEmail(String token) {

        return  extractClaim(token,Claims::getSubject);
    }

    // extract all claims
    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String genrateToken(UserDetails userDetails){
        return  genrateTokne(new HashMap<>(),userDetails);
    }
    //Genrate token
    public String genrateTokne(Map<String,Object> extraClaims, UserDetails userDetails){
        return  Jwts
                .builder()
                .setClaims(extraClaims)// add extra data
                .setSubject(userDetails.getUsername())// add the email
                .setIssuedAt(new Date(System.currentTimeMillis()))// date of creating the token
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24)) // token valid for 24h
                //.signWith(getSignKey(), SignatureAlgorithm.ES256)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // verfy token
    public boolean iSTokenValid(String token , UserDetails userDetails){
        final String username = extractUserEmail(token);
        return (username.equals(userDetails.getUsername()))&& isTokenExpired(token);
    }

    // verify expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // get Expiration date from token
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return  Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return  Keys.hmacShaKeyFor(KeyBytes);
    }*/

/*   private PrivateKey getSignKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair.getPrivate();
        } catch (Exception e) {
            throw new RuntimeException("Error generating ECDSA PrivateKey", e);
        }
    }*/


}
