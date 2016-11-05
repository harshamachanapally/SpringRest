package com.example.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.pojo.Address;

public class CustomerDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String email;
	private List<AddressDTO> address = new ArrayList<AddressDTO>();
	
	CustomerDetailsDTO(){
		
	}
	
	public CustomerDetailsDTO(int id, String name, String email, List<AddressDTO> addressDTO) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = addressDTO;
	}

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

	public List<AddressDTO> getAddress() {
		return address;
	}

	public void setAddress(List<AddressDTO> address) {
		this.address = address;
	}
	
	
	
}
