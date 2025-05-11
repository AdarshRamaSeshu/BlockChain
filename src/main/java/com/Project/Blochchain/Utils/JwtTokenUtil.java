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
	
	
	private String SECRET_KEY="5258aca671753080af2fbaf95dcfdd5d51b0373b8667e167633cca5291b49a7f9cec632d2a67deb1d2e9c5fa95b8e0b336bc00c7c42c4b367d3fb3ffbd93771f942a1f65911868abb0776186f8ace671ed08184525c3e244dbc0ca2568557a4e661927c4db4bbce6ac0af7727ce1dd6704fb2ea4e4c9da0aa57a99fde210059f77c04b5ae11ff578024af34b20e85e048bea92fd0a3a5e810694f73139a7c474e1485742ff24dcdb87cb1fb71f70bb87712a49fc097b74be117f3590ebfedf7e3e0807fb53dc8f55f2adfc382dbe0c8eab943a735bd88e5d4ea519531813e443f5857f2ce9573deae351116c83dff61c3a1849a7c30ce2f19a3f408915fde4bc";
	
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
