package com.project.ecommerce.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.ecommerce.api.model.LoginModel;
import com.project.ecommerce.api.model.RegistrationModel;
import com.project.ecommerce.exception.UserExistException;
import com.project.ecommerce.model.ModelUser;
import com.project.ecommerce.model.dao.ModelUserDAO;



@Service
public class UserService {

	private ModelUserDAO modelUserDAO;
	private EncryptionService encryptionService;
	private JWTService jwtService;

	
	public UserService(ModelUserDAO modelUserDAO, EncryptionService encryptionService, JWTService jwtService) {
		this.modelUserDAO = modelUserDAO;
		this.encryptionService = encryptionService;
		this.jwtService= jwtService;
	}


	public ModelUser registerUser(RegistrationModel registrationModel) throws UserExistException{
		if(modelUserDAO.findByEmailIgnoreCase(registrationModel.getEmail()).isPresent() || 
			modelUserDAO.findByUsernameIgnoreCase(registrationModel.getUsername()).isPresent()) {
			throw new UserExistException();
		}
		ModelUser user = new ModelUser();
		user.setEmail(registrationModel.getEmail());
		user.setFirstName(registrationModel.getFirstName());
		user.setLastName(registrationModel.getLastName());
		user.setUsername(registrationModel.getUsername());
		user.setPassword(encryptionService.encryptPassword(registrationModel.getPassword()) );//Encryption
		user = modelUserDAO.save(user);
		return user;
	}
	
	public String loginUser(LoginModel loginModel) {
		Optional<ModelUser> optionalUser = modelUserDAO.findByUsernameIgnoreCase(loginModel.getUsername());
		if(optionalUser.isPresent()) {
			ModelUser user= optionalUser.get();
			if(encryptionService.verifyPassword(loginModel.getPassword(), user.getPassword())) {
				return jwtService.generateJWT(user);
			}
		}
		return null;
		
	}
}


