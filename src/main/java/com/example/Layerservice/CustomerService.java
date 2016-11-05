package com.example.Layerservice;

import java.util.List;

import com.example.DTO.AddressDTO;
import com.example.DTO.CustomerDetailsDTO;
import com.example.pojo.Address;
import com.example.pojo.Customer;
import com.example.pojo.FileUpload;
import com.example.pojo.Product;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public List<CustomerDetailsDTO> getCustomers();
	
	public CustomerDetailsDTO getCustomer(int id);
	
	public CustomerDetailsDTO updateCustomer(Customer customer);
	
	public boolean deleteCustomer(int id);
	
	public void evictCahce();
	
	//Address related Crud operations
	
	public AddressDTO updateAddress(Address address,int customerId);
	
	public boolean deleteAddress(int addressId);

	AddressDTO addAddress(Address address, int customerId);
	
	
	public boolean saveFile(FileUpload fileUpload);
	
	public FileUpload getFile(int id);
	
}
