package com.project.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.model.ModelOrder;
import com.project.ecommerce.model.ModelUser;
import com.project.ecommerce.model.dao.ModelOrderDAO;

@Service
public class OrderService {

	private ModelOrderDAO modelOrderDAO;

	public OrderService(ModelOrderDAO modelOrderDAO) {
		this.modelOrderDAO = modelOrderDAO;
	}
	
	public List<ModelOrder> getOrders(ModelUser user){
		return modelOrderDAO.findByUser(user);
	}
	
}
