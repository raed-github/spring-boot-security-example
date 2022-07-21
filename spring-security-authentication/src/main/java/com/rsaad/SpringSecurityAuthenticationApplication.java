package com.rsaad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSecurityAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthenticationApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "<h1>Hello</h1>";
	}
}

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * configure your authentication manager
	 * @throws Exception 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("blah")
		.password("blah")
		.roles("USER");
	}
	
	/**
	 * add password encoder
	 * @return
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
