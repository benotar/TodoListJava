package com.example.todolist.security;

import com.example.todolist.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
	
	private final UserEntity userEntity;
	
	public UserDetailsImpl(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(
			 new SimpleGrantedAuthority(userEntity.getRole().name())
		);
	}
	
	@Override
	public String getPassword() {
		return userEntity.getPasswordHash();
	}
	
	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		//true
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//true
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
