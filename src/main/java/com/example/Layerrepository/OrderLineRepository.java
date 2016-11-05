package com.example.Layerrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.pojo.OrderLine;

public interface OrderLineRepository extends CrudRepository<OrderLine,Integer>{

}
