package com.cts.rest.jwt.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="user_tbl")
public class AuthUser {
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	@Column(unique = true)
	private String username;
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name="user_roles_tbl",joinColumns = @JoinColumn(name="id"))
	@Column(name="role")
	private Set<String> roles;

}

// 10  [admin,empoyee] // 10 admin 10 employee
