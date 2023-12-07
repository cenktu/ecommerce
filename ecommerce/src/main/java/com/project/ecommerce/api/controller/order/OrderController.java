package com.project.ecommerce.api.controller.order;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.ModelOrder;
import com.project.ecommerce.model.ModelUser;
import com.project.ecommerce.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@GetMapping
	public List<ModelOrder> getOrders(@AuthenticationPrincipal ModelUser user){
		return orderService.getOrders(user);
	}
}

