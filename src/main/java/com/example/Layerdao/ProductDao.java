package com.example.Layerdao;

import java.util.List;

import com.example.pojo.Product;

public interface ProductDao {
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public boolean deleteProduct(int productId);
	
	public Product getProduct(int productId);
	
	public List<Product> getProducts();
}
