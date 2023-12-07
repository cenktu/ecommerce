package com.project.ecommerce.model.dao;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.Category;

public interface CategoryDAO extends ListCrudRepository<Category, Long> {

}
