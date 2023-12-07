package com.project.ecommerce.api.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.api.model.LoginModel;
import com.project.ecommerce.api.model.LoginResponse;
import com.project.ecommerce.api.model.RegistrationModel;
import com.project.ecommerce.exception.UserExistException;
import com.project.ecommerce.model.ModelUser;
import com.project.ecommerce.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private UserService userService;
	
	

	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}



	@PostMapping("/register")
	public ResponseEntity registerUser(@Valid @RequestBody RegistrationModel registrationModel) {
		try {
			userService.registerUser(registrationModel);
			return ResponseEntity.ok().build();
		} catch (UserExistException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginModel loginModel) {
		String jwt= userService.loginUser(loginModel);
		if(jwt==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		else {
			LoginResponse response = new LoginResponse();
			response.setJwt(jwt);
			return ResponseEntity.ok(response);
		}
		
	}
	@GetMapping("/me")
	public ModelUser getLoggedInUserProfile(@AuthenticationPrincipal ModelUser user) {
		return user;
	
	}
	
}
