package com.example.Layerrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.pojo.Order1;

public interface OrderRepository extends CrudRepository<Order1,Integer> {
	
}
