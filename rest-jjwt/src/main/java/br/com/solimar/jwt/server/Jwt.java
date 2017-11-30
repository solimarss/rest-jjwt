package br.com.solimar.jwt.server;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
 
 

public class Jwt {
	
	private static final String ISSUER = "Sol";// emissor
	private static final String KEY = "secret";
	private static final long TTLMILLIS = 1000;
    //The JWT signature algorithm we will be using to sign the token
	private static final  SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256; 
	
	
	//Sample method to validate and read the JWT
	 public static void validateJwt(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
			 
	     // Validate the token
	     //Jwts.parser().setSigningKey(KEY.getBytes("UTF-8")).parseClaimsJws(token);
	           
	 
		// Validate the token
	    Claims claims = Jwts.parser()         
	       .setSigningKey(KEY.getBytes("UTF-8"))
	       .parseClaimsJws(token).getBody();
	    
	    System.out.println("ID: " + claims.getId());
	    System.out.println("Subject: " + claims.getSubject());
	    System.out.println("Issuer: " + claims.getIssuer());
	    System.out.println("Expiration: " + claims.getExpiration());
	    System.out.println("Clam: " + claims.get("name"));
	    System.out.println("Clam: " + claims.get("profile"));
	}
	
	
	 public static String issueToken(String login) throws UnsupportedEncodingException {
	    	
	    	long nowMillis = System.currentTimeMillis();
	 	    long expMillis = nowMillis + TTLMILLIS;
	 	    Date expDate = new Date(expMillis);
		   
	        String jwtToken = Jwts.builder()
	        		.setId(login)
	                .setSubject(login)
	                .setIssuer(ISSUER)
	                .setIssuedAt(new Date())
	                .setExpiration(expDate)
	                .claim("name", "Robert Token Man")
	                .claim("profile", "admin")
	                .signWith(SIGNATURE_ALGORITHM, KEY.getBytes("UTF-8"))
	                .compact();
	        
	     
	        return jwtToken;
	    }

}
