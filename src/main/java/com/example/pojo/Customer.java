package com.example.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email",unique = true)
	@NotNull
	@Email
	private String email;
	
	@OneToMany(mappedBy = "customer",fetch=FetchType.LAZY, cascade = {CascadeType.ALL},orphanRemoval = true)
	private List<Address> address = new ArrayList<Address>();
	
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL},orphanRemoval = true)
	private List<Order1> order = new ArrayList<Order1>();
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Order1> getOrder() {
		return order;
	}

	public void setOrder(List<Order1> order) {
		this.order = order;
	}
	
	@Override
	public boolean equals(Object obj) {
		Customer cust = (Customer) obj;
		if( this.getEmail().equalsIgnoreCase(cust.getEmail()) && this.getName().equalsIgnoreCase(cust.getName()) )
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 31 * this.getName().hashCode() * this.getEmail().hashCode();
		
		return hashCode;
	}
	
}
