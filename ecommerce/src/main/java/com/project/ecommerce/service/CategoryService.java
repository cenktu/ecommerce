package com.project.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.model.Category;
import com.project.ecommerce.model.dao.CategoryDAO;

@Service
public class CategoryService {
	
	private CategoryDAO categoryDAO;
	
	

	public CategoryService(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}



	public List<Category> getCategory() {
		return categoryDAO.findAll();
	}

}
