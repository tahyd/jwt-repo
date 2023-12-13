package com.cts.rest.jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.rest.jwt.entities.AuthUser;
import com.cts.rest.jwt.reposirtories.AuthUserRepository;
@Service
public class AuthUserService  implements IAuthUserService{
@Autowired
	private AuthUserRepository authUserRepository;
@Autowired
private PasswordEncoder passwordEncoder;

@Override
public AuthUser save(AuthUser authUser) {
	// TODO :  check user is already registered , throw DuplicateUserException if yes
	    authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
	
	return authUserRepository.save(authUser);
}
	

}
