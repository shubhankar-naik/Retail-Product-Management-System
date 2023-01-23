package com.spring.rpm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rpm.dto.CartRequestDto;
import com.spring.rpm.dto.CartResponseDto;
import com.spring.rpm.dto.CustomerWishListDTO;
import com.spring.rpm.dto.CustomerWishListRequestDTO;
import com.spring.rpm.dto.StatusDTO;
import com.spring.rpm.service.CartService;
import com.spring.rpm.service.CustomerWishListService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private CustomerWishListService customerWishlistService;

	@PostMapping("/addProductToCart")
	public StatusDTO addProductToCart(@RequestBody CartRequestDto cartRequestDto) {
		log.info("Add product to cart service started");

		StatusDTO statusDto = new StatusDTO(cartService.addToCart(cartRequestDto));
		log.info("Add product to cart service executed successfuly");
		return statusDto;

	}

	@GetMapping("/getCart/{customerId}")
	public List<CartResponseDto> getCartList(@PathVariable long customerId) {
		log.info("get cart service started");
		List<CartResponseDto> cartList = cartService.getCartList(customerId);
		log.info("get cartList by customer id service executed successfuly");
		return cartList;
	}

	@PostMapping("/addToCustomerWishlist")
	public StatusDTO addToCustomerWishList(@RequestBody CustomerWishListRequestDTO customerWishlist) {
		log.info("Add customer wishList service started");
		return customerWishlistService.save(customerWishlist);
	}

	@GetMapping("/getWishlist/{customerId}")
	public List<CustomerWishListDTO> viewAllWishlis(@PathVariable long customerId){
		log.info("get cart service started");
		List<CustomerWishListDTO> customerDtoList= customerWishlistService.getCartDetails(customerId);		
		log.info("get cart service call ended");
        return customerDtoList;
	}

}
