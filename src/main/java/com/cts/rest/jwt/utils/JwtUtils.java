package com.cts.rest.jwt.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtils {

	@Value("app.secretkey")
	private String secretKey;
	
	
	// 6 .Validate Token
	
	
		public boolean validateToken(String token ,String username)
		{
			String usernameFormToken = getClaims(token).getSubject();
			
			return (username.equals(usernameFormToken) && ! isTokenExpired(token));
		}
		
		// 5 . Check Token expired or not
		
		
		public boolean isTokenExpired(String token)
		{
			Date tokenExpDate = getClaims(token).getExpiration();
			
			return tokenExpDate.before(new Date(System.currentTimeMillis()));
		}
		
		// 4 Read Subject / Username
		public String getSubectOrUserName(String token)
		{
			return getClaims(token).getSubject();
		}
		
		// 3. Get Expiration Date
		
		public Date getExpiryDate(String token)
		{
			return getClaims(token).getExpiration();
		}
		
		// 2. Get Claims 
		
		
		public Claims getClaims(String token)
		{
			return Jwts.parser()
			.setSigningKey(secretKey.getBytes())
			.parseClaimsJws(token)
			.getBody();
		}
		
		
		
		//  1 . Generate Token
		
		public String generateToken(String subject)
		{
			
			return Jwts.builder().setSubject(subject)
			.setIssuer("Krishna Kumar")
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(5)))
			.signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
			.compact();
		}

}
