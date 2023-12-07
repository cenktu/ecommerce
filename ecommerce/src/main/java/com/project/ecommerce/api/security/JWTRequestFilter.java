package com.project.ecommerce.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.project.ecommerce.model.ModelUser;
import com.project.ecommerce.model.dao.ModelUserDAO;
import com.project.ecommerce.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{

	private JWTService jwtService;
	private ModelUserDAO modelUserDAO;
	
	public JWTRequestFilter(JWTService jwtService,ModelUserDAO modelUserDAO) {
		
		this.jwtService = jwtService;
		this.modelUserDAO=modelUserDAO;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tokenHeader= request.getHeader("Authorization");
		if(tokenHeader!= null && tokenHeader.startsWith("Bearer ")) {
			String token= tokenHeader.substring(7);
			try {
				String username = jwtService.getUsername(token);
				Optional<ModelUser> optionalUser= modelUserDAO.findByUsernameIgnoreCase(username);
				if(optionalUser.isPresent()) {
					ModelUser user = optionalUser.get();
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,new ArrayList());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request) );
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (JWTDecodeException e) {
				// TODO: handle exception
			}
			
		}
		filterChain.doFilter(request, response);
	}
	
	

}
