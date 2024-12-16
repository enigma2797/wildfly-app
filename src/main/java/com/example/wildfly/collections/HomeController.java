package com.example.wildfly.collections;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wildfly.dto.LoginRequestDto;
import com.example.wildfly.dto.LoginResponseDto;
import com.example.wildfly.entities.Product;
import com.example.wildfly.repositories.ProductRepository;
import com.example.wildfly.services.AuthenticationService;

@RestController
@CrossOrigin(origins = "http://localhost:4201")
public class HomeController {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private ProductRepository productRepo;
	
	
	@GetMapping("/home")
	public String root()
	{
		return "hi from wildfly";
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>>getAllProducts()
	{
		List<Product>products=productRepo.findAll();
		return ResponseEntity.ok(products);
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<LoginResponseDto>login(@RequestBody LoginRequestDto loginRequestDto)
	{
	
		return ResponseEntity.ok(authService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
	}

}
