package com.example.Layerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Layerdao.ProductDao;
import com.example.pojo.Product;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	public Product addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productDao.updateProduct(product);
	}

	@Override
	public boolean deleteProduct(int productId) {
		return productDao.deleteProduct(productId);
	}

	@Override
	public Product getProduct(int productId) {
		return productDao.getProduct(productId);
	}

	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

}
