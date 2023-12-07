package com.project.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.dao.ProductDAO;

@Service
public class ProductService {

	private ProductDAO productDAO;

	public ProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public List<Product> getProducts(){
		return productDAO.findAll();
	}

	public List<Product> getProductsByCategoryName(String name) {
		
		return productDAO.findByCategoryName(name);
	}

	public Optional<Product> getOneProduct(Long productId) {
		return productDAO.findById(productId);
	}
	
}
