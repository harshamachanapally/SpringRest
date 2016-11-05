package com.example.servicetest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringRestApplicationTests;
import com.example.DTO.CustomerDetailsDTO;
import com.example.Layerdao.CustomerDao;
import com.example.Layerservice.CustomerService;
import com.example.exception.DataNotFoundException;
import com.example.exception.DuplicateRecordException;
import com.example.pojo.Customer;

@Transactional
public class CustomerServiceMockTest extends SpringRestApplicationTests {
	
	@Autowired
	CustomerService service;
	
	@Mock
	CustomerDao dao;
	List<Customer> list;
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		list = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Harsha1");
		customer.setEmail("harsha1@abc.com");
		Customer customer1 = new Customer();
		customer1.setId(2);
		customer1.setName("Harsha2");
		customer1.setEmail("harsha2@abc.com");
		list.add(customer1);
		list.add(customer);
		
	}
	
	@After
	public void tearDown(){
		
	}
	
	// Tests for "getCustomers" method - 
	

	
	@Test
	public void getCustomersTest(){
		when(dao.getCustomers()).thenReturn(list);
		List<CustomerDetailsDTO> result = service.getCustomers();
		Assert.assertEquals("Failure - expected customers list to be returned",list.size(), result.size());
		//Assert.assertTrue(customer.equals(result));
	}
	
	@Test
	public void getCustomerTest(){
		when(dao.getCustomer(1)).thenReturn(list.get(1));
		CustomerDetailsDTO result = service.getCustomer(1);
		Assert.assertEquals("Failure - expected customer to be returned",list.get(1).getId(), result.getId());
		//Assert.assertTrue(customer.equals(result));
	}
	
	@Test
	public void addCustomerTest(){
		Customer customer2 = new Customer();
		customer2.setName("Harsha2");
		customer2.setEmail("harsha2@abc.com");
		when(dao.addCustomer(any(Customer.class))).thenReturn(customer2);
		Customer result = service.addCustomer(customer2);
		//verify(dao,times(1)).addCustomer(any(Customer.class));
		Assert.assertEquals("Failure - expected newly created customer to return",customer2, result);
		//Assert.assertTrue(customer.equals(result));
	}
	
}
