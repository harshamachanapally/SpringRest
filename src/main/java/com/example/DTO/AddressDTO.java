package com.example.DTO;

import java.io.Serializable;

public class AddressDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private int address_id;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String pincode;

	AddressDTO(){
		
	}
	
	public AddressDTO(int address_id, String street, String city, String state, String pincode) {
		this.address_id = address_id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	
}
