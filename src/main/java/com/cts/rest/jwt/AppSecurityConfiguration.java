package com.cts.rest.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.rest.jwt.filter.AppSeciurityFilter;
@Configuration

public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	// authentication 
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private AppSeciurityFilter appSeciurityFilter;
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	// authorization
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable().authorizeHttpRequests()
		.antMatchers("/auth/save","/auth/authenticate").permitAll()
		.anyRequest().authenticated()
		
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// 
		.and()
		.addFilterBefore(appSeciurityFilter, UsernamePasswordAuthenticationFilter.class);
		;
		
		
	}
	
	
}
