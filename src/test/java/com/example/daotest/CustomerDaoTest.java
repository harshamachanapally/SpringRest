package com.example.daotest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringRestApplicationTests;
import com.example.Layerdao.CustomerDao;
import com.example.exception.DataNotFoundException;
import com.example.exception.DuplicateRecordException;
import com.example.pojo.Customer;

import org.junit.Assert; 

@Transactional
public class CustomerDaoTest extends SpringRestApplicationTests {
	
	@Autowired
	CustomerDao customerDao;
	
	@Before
	public void setUp(){
		customerDao.evictCahce();
	}
	
	@After
	public void tearDown(){
		
	}
	
	// Test cases for "addCustomer" method
	
	@Test(expected=DataNotFoundException.class)
	public void addCustomerTestWithId(){
		Customer customer = new Customer();
		customer.setName("Harsha");
		customer.setId(1);
		customer.setEmail("Harsha@abc.com");
		customerDao.addCustomer(customer);
		
	}
	
	@Test(expected=DataNotFoundException.class)
	public void addCustomerTestWithoutEmail(){
		Customer customer = new Customer();
		customer.setName("Harsha");
		customer.setId(1);
		//customer.setEmail("Harsha@abc.com");
		customerDao.addCustomer(customer);
	}
	
	@Test(expected=DataNotFoundException.class)
	public void addCustomerTestWithoutEmptyEmail(){
		Customer customer = new Customer();
		customer.setName("Harsha");
		customer.setId(1);
		customer.setEmail("");
		customerDao.addCustomer(customer);
	}
	
	@Test
	public void addCustomerTest(){
		Customer customer = new Customer();
		customer.setName("Harsha");
		customer.setEmail("harsh@abc.com");
		Customer result = customerDao.addCustomer(customer);
		Assert.assertEquals("Failure - customer not saved",customer, result);
		//Assert.assertTrue(customer.equals(result));
	}
	
	@Test(expected=DuplicateRecordException.class)
	public void addCustomerTestDuplicateEmail(){
		Customer customer = new Customer();
		customer.setName("Harsha");
		customer.setEmail("harsh@abc.com");
		customerDao.addCustomer(customer);
		Customer customer1 = new Customer();
		customer1.setName("Harsha1");
		customer1.setEmail("harsh@abc.com");
		customerDao.addCustomer(customer1);
		//Assert.assertTrue(customer.equals(result));
	}
	
	// Test cases for "getCustomers" method
	
	@Test
	public void getCustomersTestReturnAllCustomers(){

		int input = 2;
		Assert.assertEquals("Failure - did not retrive the",input, customerDao.getCustomers().size());
		//Assert.assertTrue(customer.equals(result));
	}
	
	// Test cases for "getCustomer" method
	
	@Test(expected=DataNotFoundException.class)
	public void getCustomerTestCustomerNotFound(){
		customerDao.getCustomer(Integer.MAX_VALUE);
		
	}
	
	@Test
	public void getCustomerTestReturnCustomer(){
		Customer customer = new Customer();
		customer.setName("Harsha");
		customer.setEmail("harsh@abc.com");
		Customer result = customerDao.addCustomer(customer);
		Assert.assertEquals("Failure - did not retrive the customer",customer, customerDao.getCustomer(result.getId()));
	}
	
	// Test cases for "getCustomerbyEmail" method
	
	@Test
	public void getCustomerbyEmailTestReturnCustomer(){
		
		Assert.assertEquals("Failure - did not retrive the customer",1,customerDao.getCustomerByEmail("harsha@abc.com").getId());
		System.out.println("-------------");
	}
	
	// Test cases for "updateCustomer" method
	@Test(expected=DataNotFoundException.class)
	public void updateCustomerTestCustomerNotFound(){
		
		Customer customer = customerDao.getCustomer(Integer.MAX_VALUE);
		customerDao.updateCustomer(customer);
		
	}
	
	@Test
	public void updateCustomerTestReturnUpdateCustomer(){
		
		Customer customer = customerDao.getCustomer(1);
		customer.setEmail("HarshaNew@abc.com");
		Assert.assertEquals("Failure - did not retrive the customer",customer, customerDao.updateCustomer(customer));
		
	}
	
}