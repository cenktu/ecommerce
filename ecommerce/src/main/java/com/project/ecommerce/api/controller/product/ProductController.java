package com.project.ecommerce.api.controller.product;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;



@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productService;
	
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}


	@GetMapping
	public List<Product> getProduct(){
		return productService.getProducts();
	}
	
	@GetMapping("/category/{name}")
    public List<Product> getProductsByCategoryName(@PathVariable String name) {
        return productService.getProductsByCategoryName(name);
    }
	
	@GetMapping("/{productId}")
	 public Optional<Product> getSingleProduct(@PathVariable Long productId) {
		 return productService.getOneProduct(productId);
	 }
}
