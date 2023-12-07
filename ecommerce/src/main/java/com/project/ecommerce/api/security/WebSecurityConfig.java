package com.project.ecommerce.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class WebSecurityConfig {
	
	private JWTRequestFilter jwtRequestFilter;
	CorsConfiguration config = new CorsConfiguration();
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	
	
	public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}



	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());
		http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
		http.authorizeHttpRequests(auth->auth
			.requestMatchers("/product","/auth/login","/auth/register","/category","/product/category/{name}","/product/{productId}").permitAll()
			.anyRequest().authenticated());
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		source.registerCorsConfiguration("/**", config);
		return http.build();
	}

}
