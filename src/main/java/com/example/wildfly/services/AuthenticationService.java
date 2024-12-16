package com.example.wildfly.services;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wildfly.dto.LoginResponseDto;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	public LoginResponseDto login(String username, String password) {

		LoginResponseDto loginResponseDto = new LoginResponseDto();
		try {

			List<GrantedAuthority>authorities=new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ADMIN"));

			Authentication auth = new UsernamePasswordAuthenticationToken(username, password,authorities);
			Authentication authentication = authenticationManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			loginResponseDto.setStatusCode(200);
			loginResponseDto.setStatusMsg("Login successfull");
			return loginResponseDto;
		}

		catch (AuthenticationException e) {
			loginResponseDto.setStatusCode(400);
			loginResponseDto.setStatusMsg("Invalid username or password");
			return loginResponseDto;
		}

	}

}
