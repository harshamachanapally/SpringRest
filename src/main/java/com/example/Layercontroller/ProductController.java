package com.example.Layercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Layerservice.ProductService;
import com.example.pojo.Product;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/Products",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@RequestMapping(value="/Products/{id}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
		product.setProductId(id);
		return productService.updateProduct(product);
	}
	
	@RequestMapping(value="/Products",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProducts() {
		
		return productService.getProducts();
	}
	
	@RequestMapping(value="/Products/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProduct(@PathVariable("id") int id) {
		return productService.getProduct(id);
	}
	
	@RequestMapping(value="/Products/{id}",method = RequestMethod.DELETE)
	public Product deleteProduct(@PathVariable("id") int id) {
		return productService.getProduct(id);
	}
	
	
}
