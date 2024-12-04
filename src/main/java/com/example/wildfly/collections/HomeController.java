package com.example.wildfly.collections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wildfly.entities.Product;
import com.example.wildfly.repositories.ProductRepository;

@RestController
@CrossOrigin
public class HomeController {
	
	@Autowired
	private ProductRepository productRepo;
	@GetMapping("/home")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/")
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
	
	

}
