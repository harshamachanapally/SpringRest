package com.example.Layerrepository;

import org.hibernate.Session;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pojo.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	Customer findByEmail(String email);
	
}
