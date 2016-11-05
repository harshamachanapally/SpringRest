package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Layerservice.AccountService;

@Component
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	AccountService accountService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		if( userDetails.getPassword() == null || token.getCredentials() == null ) {
			throw new BadCredentialsException("Credentials cannot be null..");
		}
		if( !passwordEncoder.matches( (String) token.getCredentials() , userDetails.getPassword() ) ) {
			throw new BadCredentialsException("Credentials are incorrect..");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		UserDetails userDetails = accountService.findByUserName(username);
		return userDetails;
	}
	
	

}
