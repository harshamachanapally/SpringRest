package com.example.Layerdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.Layerrepository.AddressRepository;
import com.example.Layerrepository.CustomerRepository;
import com.example.exception.DataNotFoundException;
import com.example.exception.DuplicateRecordException;
import com.example.pojo.Address;
import com.example.pojo.Customer;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CustomerDaoImp implements CustomerDao {

	
	/*SessionFactory sessionFactory;
	
	@Autowired
	  public DaoLayerImp(EntityManagerFactory factory) {
	    if(factory.unwrap(SessionFactory.class) == null){
	      throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.sessionFactory = factory.unwrap(SessionFactory.class);
	  }
	
	@PersistenceContext
	EntityManager entityManager;

	   protected Session getCurrentSession()  {
	      return entityManager.unwrap(Session.class);
	   }*/
	@Autowired
		CustomerRepository res;
	    
	    @Autowired
	    AddressRepository addres;
		
		@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
		@CachePut(value="customers",key="#result.id")
		public Customer addCustomer(Customer customer) {
			
			if( customer.getId() != 0 )
				throw new DataNotFoundException("Customer cannot provide customerId");
			if( customer.getEmail() == null || customer.getEmail().trim().length() == 0)
				throw new DataNotFoundException("Incorrect Email. Please provide valid Email Id");
			if(res.findByEmail(customer.getEmail())!=null)
				throw new DuplicateRecordException("Email "+customer.getEmail()+" already exists");
			
			for( Address add: customer.getAddress() ) {
				add.setCustomer(customer);
			}
			
			Customer cust = res.save(customer);
			
			return cust;
			
		}
		//@Cacheable(value="customers")
		public List<Customer> getCustomers() {
			List<Customer> customers = (List<Customer>) res.findAll(); 
			
			if(customers.size()==0)
				throw new DataNotFoundException("No customers to show");
			
			return customers;
			
		}
		
		@Cacheable(value="customers",key="#id")
		public Customer getCustomer(int id) {
			Customer cust = res.findOne(id);
			//cust.getAddress().size();
			if(cust==null)
				throw new DataNotFoundException("No customer exists with Id "+id);
			
			return cust;
		}
		
		//@Cacheable(value="customers",key="#result.id")
		public Customer getCustomerByEmail(String email) {
			Customer cust = res.findByEmail(email);
			if(cust==null)
				throw new DataNotFoundException("No customer exists with email id - "+email);
			
			return cust;
		}
		
		@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
		@CachePut(value="customers",key="#result.id")
		public Customer updateCustomer(Customer customer) {
			
			if( customer.getEmail() == null || customer.getEmail().trim().length() == 0)
				throw new DataNotFoundException("Incorrect Email. Please provide valid Email Id");
			Customer customer1 = res.findOne(customer.getId());
			if ( customer1!=null ) {
				if ( !customer.getEmail().equalsIgnoreCase(customer1.getEmail() ) ) 
					if ( res.findByEmail(customer.getEmail()) != null )
						throw new DataNotFoundException("Email Id "+customer.getEmail()+" already exists");
				
				customer1.setEmail(customer.getEmail());
				customer1.setName(customer.getName());
				return customer1;
			}

			throw new DataNotFoundException("No customer exists with Id "+customer.getId());
		}
		
		@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
		@CacheEvict(value="customers",key="#id")
		public boolean deleteCustomer(int id) {
			
			if(res.findOne(id)!=null){
				System.out.println("DaoLayer");
				res.delete(id);
				return true;
			}
				
			throw new DataNotFoundException("No customer exists with Id "+id);
		}
		
		@CacheEvict(value="customers",allEntries=true)
		public void evictCahce(){
			System.out.println("DAO Evict Cache");
		}
		
		@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
		public Address addAddress(Address address) {
			
			addres.save(address);
			
			return address;
		}
		
		@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
		public Address updateAddress(Address address) {
			if( addres.findOne(address.getAddress_id()) == null)
				throw new DataNotFoundException("Incorrect address details ");
			
			addres.save(address);
			
			return address;
		}

		@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
		public boolean deleteAddress(int addressId) {
			Address address = addres.findOne(addressId);
			if( address != null) {
				addres.delete(address);
				return true;
			}	
			throw new DataNotFoundException("No such address exits or already been deleted");
		}
		
		

}
