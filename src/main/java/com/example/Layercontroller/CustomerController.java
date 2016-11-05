package com.example.Layercontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.DTO.AddressDTO;
import com.example.DTO.CustomerDetailsDTO;
import com.example.Layerservice.CustomerService;
import com.example.exception.DataNotFoundException;
import com.example.pojo.Address;
import com.example.pojo.Customer;

@org.springframework.web.bind.annotation.RestController
//@RequestMapping(value = "/api")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/addCustomer",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addCustomer(@RequestBody Customer customer){
		//if(customer==null)
		//	throw new DataNotFoundException("Customer related data is missing. Please add customer data to payload");
		customerService.addCustomer(customer);
	}
	
	@RequestMapping(value="/getCustomers",method = RequestMethod.GET)
	public List<CustomerDetailsDTO> getCustomers(){
		
		return customerService.getCustomers();
	}
	
	@RequestMapping(value="/getCustomer/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDetailsDTO getCustomer(@PathVariable("id") int id){
		System.out.println("Get Customer "+ id);
		return customerService.getCustomer(id);
	}
	
	@RequestMapping(value="/updateCustomer",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDetailsDTO updateCustomer(@RequestBody Customer customer,@RequestParam("id") int id){
		customer.setId(id);
		return customerService.updateCustomer(customer);
	}
	@RequestMapping(value="/deleteCustomer",method = RequestMethod.DELETE)
	public boolean deleteCustomer(@RequestParam("id") int id) {
		return customerService.deleteCustomer(id);
	}
	
	@RequestMapping(value="/addAddress",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public AddressDTO addAddress(@RequestBody Address address,@RequestParam("customerid") int customerId  ) {

		return customerService.addAddress(address,customerId);
	}
	
	@RequestMapping(value="/updateAddress",method = RequestMethod.PUT,  produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public AddressDTO updateAddress(@RequestBody Address address, @RequestParam("customerid") int customerId ) {
		
		return customerService.updateAddress(address,customerId);
		
	}
	
	@RequestMapping(value="/deleteAddress",method = RequestMethod.DELETE)
	public boolean deleteAddress(@RequestParam("id") int addressId ) {

		return customerService.deleteAddress(addressId);
	}
	
	@RequestMapping(value="/evict",method = RequestMethod.DELETE)
	public boolean evict(@RequestParam("value") Boolean value) {
		System.out.println("EvictCache started");
		if(value == true)
		{
			customerService.evictCahce();
			return true;
		}
		return false;
	}
	
	
	
	
}
