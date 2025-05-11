package com.Project.Blochchain.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.Project.Blochchain.Configuration.CustomUserDetails;



//This class will help us to generate token and validate tokes
@Service
public class JwtTokenUtil {
	
	
	private String SECRET_KEY;
	
	SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	public String generateToken(Map<String, Object> claim, UserDetails user_Details) throws Exception {
		System.out.println("Generate token method is invoked");
		try {
			String jwt_token= Jwts.builder()
	        		.setClaims(claim)
	                .setSubject(user_Details.getUsername())  
	                .setIssuedAt(new Date(System.currentTimeMillis()))               
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
	                .signWith(key)                     
	                .compact();
			
			System.out.print(jwt_token);
			return jwt_token;
		}
		catch(Exception e) {
			 throw new Exception("Token creation Failed "+e);
		}
        
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims = extractAllToken(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllToken(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
                .parseClaimsJws(token)
                .getBody();
	}




	public boolean validateToken(CustomUserDetails user_Details, String token) {
		final String username = extractUsername(token);
		
		return (username.equals(user_Details.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		Date expiredTime= extractExpiration(token);
		return expiredTime.before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}
