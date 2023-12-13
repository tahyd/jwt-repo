package com.cts.rest.jwt.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cts.rest.jwt.entities.AuthUser;
import com.cts.rest.jwt.reposirtories.AuthUserRepository;
@Component
public class CustomeUserDetailService  implements UserDetailsService{

	@Autowired
	private AuthUserRepository authUserRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		        Optional<AuthUser> authuserOptional = authUserRepository.findByUsername(username);
		        
		        
		        if(!authuserOptional.isPresent())
		        	throw new UsernameNotFoundException("User not found");
		
		
		         AuthUser authUser = authuserOptional.get();
		return  new User(username, authUser.getPassword(),
				
				
				authUser.getRoles().stream().map((role)->new SimpleGrantedAuthority(role))
				.collect(Collectors.toList())
				);
	}

}
