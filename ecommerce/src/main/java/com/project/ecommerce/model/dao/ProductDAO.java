package com.project.ecommerce.model.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.Category;
import com.project.ecommerce.model.Product;

public interface ProductDAO extends ListCrudRepository<Product, Long>{
	
	List<Product> findByCategoryName(String name);

}
