package com.example.Layerrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pojo.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{
	
}
