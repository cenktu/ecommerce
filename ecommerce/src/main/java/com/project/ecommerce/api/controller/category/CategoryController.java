package com.project.ecommerce.api.controller.category;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.Category;
import com.project.ecommerce.model.ModelOrder;
import com.project.ecommerce.model.ModelUser;
import com.project.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public List<Category> getCategories(){
		return categoryService.getCategory();
	}
	
	

}
