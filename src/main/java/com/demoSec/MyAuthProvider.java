package com.demoSec;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;



@Component
public class MyAuthProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String pass = authentication.getCredentials().toString();
		if (name.equals("Mohit") && pass.equals("pass")) {
			return new UsernamePasswordAuthenticationToken(name, pass, Arrays.asList());
		} else {
			throw new BadCredentialsException("Wrong Username and password.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		if (authentication.equals(UsernamePasswordAuthenticationToken.class)) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
