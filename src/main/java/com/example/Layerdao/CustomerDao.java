package com.example.Layerdao;

import java.util.List;

import com.example.pojo.Address;
import com.example.pojo.Customer;

public interface CustomerDao {
	
	
	public Customer addCustomer(Customer customer);
	
	public List<Customer> getCustomers();
	
	public Customer getCustomer(int id);
	
	public Customer updateCustomer(Customer customer);
	
	public boolean deleteCustomer(int id);
	
	public void evictCahce();
	
	//Address related Crud operations
	
	public Address addAddress(Address address);
	
	public Address updateAddress(Address address);
	
	public boolean deleteAddress(int addressId);

	Customer getCustomerByEmail(String email);
	
}
