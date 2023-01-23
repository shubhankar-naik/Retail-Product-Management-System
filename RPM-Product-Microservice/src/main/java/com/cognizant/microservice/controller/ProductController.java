package com.cognizant.microservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservice.exception.ProductNotFoundException;
import com.cognizant.microservice.exception.RatingGreaterThan5Exception;
import com.cognizant.microservice.model.Product;
import com.cognizant.microservice.service.ProductServiceImpl;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductServiceImpl productService;
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	@GetMapping("/productById/{id}")
	public Product searchProductById(@PathVariable String id) throws ProductNotFoundException
	{
		log.info("Product is searching by id");
		return productService.searchProductById(Integer.parseInt(id));
		
	}

	@GetMapping("/productByName/{name}")
	public Product searchProductByName(@PathVariable String name) throws ProductNotFoundException 
	{
		log.info("Product is searching by name");
		return productService.searchProductByName(name);
	}

	@PostMapping("/addRating/{productId}/{rating}")
	public Product addProductRating(@PathVariable int productId, @PathVariable int rating)
			throws ProductNotFoundException, RatingGreaterThan5Exception 
	{
		log.info("Rating is adding to the prouduct with id");
		productService.addProductRating(productId, rating);
		Product product = productService.searchProductById(productId);
		log.info("addProductRating inside ProductController ended");
		return product;
	}

	@GetMapping("/getAll")
	public List<Product> getAll() 
	{
		log.info("fetching all the products");
		return productService.getAll();
	}
}
