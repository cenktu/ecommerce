package com.project.ecommerce.model.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.Address;

public interface AddressDAO extends ListCrudRepository<Address, Long> {

	List<Address> findByUserId(Long id);
}
