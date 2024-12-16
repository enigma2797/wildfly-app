package com.example.wildfly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
         auth.inMemoryAuthentication()
         .withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN");
     }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable().authorizeRequests().antMatchers("/auth/login","/products").permitAll()
        .anyRequest().authenticated()
        .and().httpBasic();
    }

    
@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception
{
    return super.authenticationManagerBean();
}

@Bean
BCryptPasswordEncoder bCryptPasswordEncoder()
{
    return new BCryptPasswordEncoder();
}
  
}
