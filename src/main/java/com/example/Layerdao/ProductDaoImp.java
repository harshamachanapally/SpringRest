package com.example.Layerdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.Layerrepository.ProductRepository;
import com.example.exception.DataNotFoundException;
import com.example.exception.OutOfStockException;
import com.example.pojo.Product;

@Repository
@Transactional( propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class ProductDaoImp implements ProductDao {
	
	@Autowired
	ProductRepository prores;
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public Product addProduct(Product product) {
		System.out.println(product.getProductId());
		//if( product.getProductId() != 0 )
		//	throw new DataNotFoundException("Incorrect product details");
		
		return prores.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Product product1 = prores.findOne(product.getProductId());
			
		if( product1 != null ) {
			if(product.getQuantity()<0)
				throw new OutOfStockException(product.getName() +" is currently out of stock..");
			product1.setName(product.getName());
			product1.setCurrentPrice(product.getCurrentPrice());
			product1.setQuantity(product.getQuantity());
			return product1;
		}
		else {
			throw new DataNotFoundException("Product doesn't exist");
		}
	}

	@Override
	public boolean deleteProduct(int productId) {
		if( prores.exists(productId)) {
			prores.delete(productId);
			return true;
		}
		else {
			throw new DataNotFoundException("Product with id "+productId+" doesn't exist");
		}
	}

	@Override
	public Product getProduct(int productId) {
		Product product = prores.findOne(productId);
		if(product!=null)
			return product;
		else
			throw new DataNotFoundException("Product with id "+productId+" doesn't exist");
	}

	@Override
	public List<Product> getProducts() {
		List<Product> products = (List<Product>) prores.findAll();

		return products;
		
		
	}

}
