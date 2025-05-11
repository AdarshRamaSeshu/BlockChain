package com.Project.Blochchain.Controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Project.Blochchain.Configuration.CustomUserDetails;
import com.Project.Blochchain.DTO.LoginRequest;
import com.Project.Blochchain.DTO.LoginResponse;
import com.Project.Blochchain.Utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	AuthController(JwtTokenUtil jwttokenutil_obj){
		this.jwtTokenUtil=jwttokenutil_obj;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginrequest){
		try {
			Authentication authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginrequest.getUsername(),loginrequest.getPassword()) // This will internally call loadByUsername method 
					);
			
			CustomUserDetails user_Details = (CustomUserDetails)authentication.getPrincipal(); // The return object stored here
			System.out.println("Authentication is performed and sent for token generation");
			Map<String,Object> claim = new HashMap<>();
			
			String role = user_Details.getAuthorities()
									  .stream()
									  .map(GrantedAuthority::getAuthority)
									  .findFirst()
									  .orElse("ROLE_UNKOWN");
									  
			
			claim.put("username", user_Details.getUsername()); // set username in claim
			claim.put("marsId", user_Details.getMarsId()); // set marsId of user
			claim.put(role, role);// Need to add role of the user
			String token = jwtTokenUtil.generateToken(claim,user_Details);
			
			
			return ResponseEntity.ok(new LoginResponse(token));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
	}

}
