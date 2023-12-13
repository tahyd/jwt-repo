package com.cts.rest.jwt.reposirtories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.rest.jwt.entities.AuthUser;

public interface AuthUserRepository  extends JpaRepository<AuthUser, Integer>{

	public Optional<AuthUser> findByUsername(String username);
	
}
