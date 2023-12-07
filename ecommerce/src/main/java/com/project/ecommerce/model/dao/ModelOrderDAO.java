package com.project.ecommerce.model.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.ModelOrder;
import com.project.ecommerce.model.ModelUser;

public interface ModelOrderDAO extends ListCrudRepository<ModelOrder,Long>{
	
	List<ModelOrder> findByUser(ModelUser user);
}
