package com.rsaad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSecurityAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthorizationApplication.class, args);
	}

	@GetMapping("/")
	public String sayHello() {
		return "<h1>Hello</h1>";
	}
	
	@GetMapping("/user")
	public String getUser() {
		return "<h1>user</h1>";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "<h1>admin</h1>";
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
			.roles("USER")
			.and()
			.withUser("foo")
			.password("foo")
			.roles("ADMIN");
	}
	
	/**
	 * configure your authorization manager
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("ADMIN","USER")
		.antMatchers("/").permitAll()
		.and().formLogin();
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

