package com.example.Layerrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.pojo.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
