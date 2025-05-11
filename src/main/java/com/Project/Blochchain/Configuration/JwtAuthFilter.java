package com.Project.Blochchain.Configuration;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.Project.Blochchain.Service.UserService;
import com.Project.Blochchain.Utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter  {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	
    @Autowired
    private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader= request.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7); // Token starts at index 7
			
			String username = jwtTokenUtil.extractUsername(token);
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
				CustomUserDetails user_Details = (CustomUserDetails) userService.loadUserByUsername(username);
				
				if(jwtTokenUtil.validateToken(user_Details , token)) {
					UsernamePasswordAuthenticationToken authToken=
							new UsernamePasswordAuthenticationToken(user_Details,null,user_Details.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authToken); // setting our security context holder for authenticated user
				}
			}
		}
		
		filterChain.doFilter(request,response); // This is allow request to go through next filters
		
	}

}
