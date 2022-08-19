package com.yeshua.clinicapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.
			authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/webjars/**", "/registration").permitAll()
				.antMatchers("/patient/**").access("hasRole('PATIENT')")
				.antMatchers("/doctor/**").access("hasRole('DOCTOR')")
				.antMatchers(HttpMethod.DELETE, "/admin/{id}/delete").hasRole("ADMIN")
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.permitAll()
				.and()
			.logout()
				.permitAll();
		
		return http.build();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    } 
}
