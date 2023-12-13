package com.cts.rest.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.rest.jwt.entities.AuthUser;
import com.cts.rest.jwt.entities.JwtRequest;
import com.cts.rest.jwt.entities.JwtResponse;
import com.cts.rest.jwt.services.IAuthUserService;
import com.cts.rest.jwt.utils.JwtUtils;

@RestController
@RequestMapping("auth")
public class AuthorizationController {

	@Autowired
	private IAuthUserService authUserService;
	@Autowired
	private JwtUtils jwtutils;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/save")
	public ResponseEntity<AuthUser> createUser(@RequestBody AuthUser authUser)
	{
		return new ResponseEntity<AuthUser>(authUserService.save(authUser),
				
				HttpStatus.CREATED);
				
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequst)
	{
		  // validate username and password
		
		
		// genrate Token
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequst.getUsername(), jwtRequst.getPassword()));
		
		
	String token =	jwtutils.generateToken(jwtRequst.getUsername());
		
		JwtResponse response = new JwtResponse();
		response.setToken(token);
		return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello()
	{
		return new ResponseEntity<String>("Hello Welcome",HttpStatus.OK);
	}
}
