package com.project.ecommerce.model.dao;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.ModelUser;

public interface ModelUserDAO extends ListCrudRepository<ModelUser, Long> {
	
	Optional<ModelUser> findByUsernameIgnoreCase(String username);
	
	Optional<ModelUser> findByEmailIgnoreCase(String email);

}
