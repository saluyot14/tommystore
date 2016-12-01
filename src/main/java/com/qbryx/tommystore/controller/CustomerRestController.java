package com.qbryx.tommystore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.service.CustomerService;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystore.util.CartHelper;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartHelper cartHelper;
	
	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
	public CartProduct addToCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request){
		
		cartProduct.setProduct(productService.findByProductId(cartProduct.getProduct().getProductId()));
		
		cartHelper.addProductToCart(request, cartProduct);
		
		return cartProduct;
	}
	
	@RequestMapping(value="/removeFromCart", method = RequestMethod.POST)
	public int removeFromCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request){
	
		cartHelper.removeProductFromCart(request, cartProduct);
		return cartHelper.getCartSize(request);
	}
	
	@RequestMapping(value="/addShippingAddress", method = RequestMethod.POST)
	public ShippingAddress addShippingAddress(@ModelAttribute ShippingAddress shippingAddress){
		
		shippingAddress.setUser(userService.findByEmail(shippingAddress.getUser().getEmail()));

		customerService.createShippingAddress(shippingAddress);
		
		return shippingAddress;
	}
}
