package com.cts.rest.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.rest.jwt.reposirtories.AuthUserRepository;
import com.cts.rest.jwt.utils.JwtUtils;
@Component
public class AppSeciurityFilter  extends OncePerRequestFilter{

	@Autowired
	 private JwtUtils jwtutils;
	@Autowired
	private UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		String tokenString = request.getHeader("Authorization");
		
		
		if(tokenString!=null){
			
			//getToken 
			
			String token = tokenString.substring(7);
			
			
			
			
			// read the   username from token
			
			
			 
			
	String username = 	jwtutils.getSubectOrUserName(token);
	
	
	
	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		
		
UserDetails user =		userDetailsService.loadUserByUsername(username);
		
boolean isValidToken =		jwtutils.validateToken(token, user.getUsername());
	
	
if(isValidToken)
{
		UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(username,
				
				
				user.getPassword(),user.getAuthorities());
		
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
}
	
	
	}
	
			
		}
		filterChain.doFilter(request, response);
		
	}

}
