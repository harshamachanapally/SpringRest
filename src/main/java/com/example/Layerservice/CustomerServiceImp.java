package com.example.Layerservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.DTO.AddressDTO;
import com.example.DTO.CustomerDetailsDTO;
import com.example.Layerdao.CustomerDao;
import com.example.Layerrepository.FileRepository;
import com.example.pojo.Address;
import com.example.pojo.Customer;
import com.example.pojo.FileUpload;
import com.example.utils.Conversion;

@org.springframework.stereotype.Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	FileRepository fileRepository;
	
	public Customer addCustomer( Customer customer ) {
		Customer customer1 = customerDao.addCustomer(customer);
		return customer1;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	public List<CustomerDetailsDTO> getCustomers() {
		List<Customer> customers = customerDao.getCustomers();
		
		List<CustomerDetailsDTO> customerDetailsDTO = new ArrayList<CustomerDetailsDTO>();
		
		for(Customer customer: customers) {
			customerDetailsDTO.add(Conversion.CustomerToCustomerDetailsDTO(customer, "EAGER"));
		}
		return customerDetailsDTO;
		
	}
	public CustomerDetailsDTO getCustomer(int id) {
		
		Customer customer =	customerDao.getCustomer(id);
		
		CustomerDetailsDTO customerDetailDTO = Conversion.CustomerToCustomerDetailsDTO(customer, "EAGER");
		
		return customerDetailDTO;
	}
	
	public CustomerDetailsDTO updateCustomer( Customer customer ) {
		Customer updatedCustomer  = customerDao.updateCustomer(customer);
		
		CustomerDetailsDTO customerDetailDTO = Conversion.CustomerToCustomerDetailsDTO(updatedCustomer, "EAGER");
		
		return customerDetailDTO;
	}
	
	public boolean deleteCustomer( int id ) {
		
		return customerDao.deleteCustomer(id);
	}
	
	public AddressDTO addAddress( Address address, int customerId ) {
		Customer customer = customerDao.getCustomer(customerId);
		address.setCustomer(customer);
		Address address1 = customerDao.addAddress(address);
		return Conversion.AddressToAddressDTO(address1);
	}

	
	public AddressDTO updateAddress(Address address, int customerId) {
		Customer customer = customerDao.getCustomer(customerId);
		address.setCustomer(customer);
		Address address1 = customerDao.updateAddress(address);
		return Conversion.AddressToAddressDTO(address1);
	}

	public boolean deleteAddress(int addressId) {
		// TODO Auto-generated method stub
		return customerDao.deleteAddress(addressId);
	}

	public void evictCahce() {
		customerDao.evictCahce();
	}

	public boolean saveFile(FileUpload fileUpload) {
		fileRepository.save(fileUpload);
		return true;
	}

	public FileUpload getFile(int id) {
		FileUpload fileUpload = fileRepository.findOne(id);
		return fileUpload;
	}
	
}
