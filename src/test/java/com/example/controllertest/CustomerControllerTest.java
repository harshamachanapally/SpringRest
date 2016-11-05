package com.example.controllertest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.DTO.CustomerDetailsDTO;
import com.example.Layerservice.CustomerService;
import com.example.exception.DataNotFoundException;
import com.example.exception.ErrorMessage;
import com.example.pojo.Customer;

@Transactional
public class CustomerControllerTest extends AbstractControllerTest {
	
	@Autowired
	CustomerService customerService;

	@Before
	public void setUp(){
		super.setUp();
		customerService.evictCahce();
	}
	
	//Tests for "getCustomers" method
	
	@Test
	public void getCustomersTest() throws Exception{
		String uri = "/getCustomers";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 200 status code", 200,status);
		Assert.assertTrue("Failure - response should contain payload/json response", content.length() > 0);
	}
	
	//Tests for "getCustomer" method
	
	@Test
	public void getCustomerTestReturnCustomer() throws Exception{
		String uri = "/getCustomer/{id}";
		int id = 1;
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		Customer customer = mapFromJson(content,Customer.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 200 status code", 200,status);
		//Assert.assertTrue("Failure - response should contain payload/json response", content.length() > 0);
		Assert.assertEquals("Failure - expected customer json with id "+id, 1, customer.getId());
	}
	
	@Test
	public void getCustomerTestCustomerNotFound() throws Exception{
		String uri = "/getCustomer/{id}";
		int id = Integer.MAX_VALUE;
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		//Assert.assertTrue("Failure - response should contain payload/json response", content.length() > 0);
	}
	
	// Tests for "addCustomer" method
	
	@Test
	public void addCustomerTestWithCustomerId() throws Exception{
		String uri = "/addCustomer";
		Customer customer = new Customer();
		customer.setId(2);
		customer.setEmail("abc@abc.com");
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "Customer cannot provide customerId", errorMessage.getErrorMessage());
	}
	
	@Test
	public void addCustomerTestWithEmptyEmail() throws Exception{
		String uri = "/addCustomer";
		Customer customer = new Customer();
		customer.setEmail("");
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "Incorrect Email. Please provide valid Email Id", errorMessage.getErrorMessage());
	}
	
	@Test
	public void addCustomerTestWithEmailAsNull() throws Exception{
		String uri = "/addCustomer";
		Customer customer = new Customer();
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "Incorrect Email. Please provide valid Email Id", errorMessage.getErrorMessage());
	}
	
	@Test
	public void addCustomerTestWithDuplicateEmail() throws Exception{
		String uri = "/addCustomer";
		Customer customer = new Customer();
		customer.setEmail("harsha@abc.com");
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 400,status);
		Assert.assertEquals("Failure - response should contain Error message", "Email "+customer.getEmail()+" already exists", errorMessage.getErrorMessage());
	}
	
	@Test
	public void addCustomerTestCreatesCustomer() throws Exception{
		String uri = "/addCustomer";
		Customer customer = new Customer();
		customer.setEmail("abc@abc.com");
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 200 status code", 200,status);
	}
	
	// Tests for "updateCustomer" method - 5 Test cases
	
	@Test
	public void updateCustomerTestCustomerwithEmailAsNull() throws Exception{
		String uri = "/updateCustomer";
		Customer customer = new Customer();
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri).param("id","1").content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "Incorrect Email. Please provide valid Email Id", errorMessage.getErrorMessage());
	}
	
	@Test
	public void updateCustomerTestCustomerwithEmailAsEmpty() throws Exception{
		String uri = "/updateCustomer";
		Customer customer = new Customer();
		customer.setEmail("   ");
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri).param("id","1").content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "Incorrect Email. Please provide valid Email Id", errorMessage.getErrorMessage());
	}
	
	@Test
	public void updateCustomerTestCustomerwithIncorrectId() throws Exception{
		String uri = "/updateCustomer";
		Customer customer = new Customer();
		String id = Integer.toString(Integer.MAX_VALUE);
		customer.setEmail("abc@bac.com");
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri).param("id",id).content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "No customer exists with Id "+id, errorMessage.getErrorMessage());
	}
	
	@Test
	public void updateCustomerTestUpdateWithExistingEmailOfOtherCustomer() throws Exception{
		String uri = "/updateCustomer";
		Customer customer = new Customer();
		customer.setEmail("Vijay@abc.com");
		customer.setName("abc");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri).param("id","1").content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "Email Id "+customer.getEmail()+" already exists"
									, errorMessage.getErrorMessage());
	}
	
	@Test
	public void updateCustomerTestUpdateSuccessFullyAndReturn() throws Exception{
		String uri = "/updateCustomer";
		Customer customer = new Customer();
		customer.setEmail("harsha1@abc.com");
		customer.setName("HARSHA");
		String payload = mapToJson(customer);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri).param("id","1").content(payload).contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		CustomerDetailsDTO customerDetailsDTO = mapFromJson(content,CustomerDetailsDTO.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 200 status code", 200,status);
		Assert.assertEquals("Failure - response should return updated email", customer.getEmail() 
									, customerDetailsDTO.getEmail());
	}
	
	
	// Tests for "deleteCustomer" method - 2 test cases
	
	@Test
	public void deleteCustomerWithIncorrectCustomerId() throws Exception{
		String uri = "/deleteCustomer";
		String id = Integer.toString(Integer.MAX_VALUE);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri).param("id",id)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		ErrorMessage errorMessage = mapFromJson(content,ErrorMessage.class);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 404 status code", 404,status);
		Assert.assertEquals("Failure - response should contain Error message", "No customer exists with Id "+id
									, errorMessage.getErrorMessage());
	}
	
	@Test
	public void deleteCustomerSuccessfully() throws Exception{
		String uri = "/deleteCustomer";
		String id = Integer.toString(1);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri).param("id",id)
												.accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		String flag = content.toString();
		System.out.println(flag);
		int status = result.getResponse().getStatus();
		Assert.assertEquals("Failure - expected 200 status code", 200,status);
		//Assert.assertEquals("Failure - response should contain 'true' boolean", "true", flag);
	}
	
}
