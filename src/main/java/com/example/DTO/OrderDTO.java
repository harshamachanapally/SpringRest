package com.example.DTO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.pojo.Customer;
import com.example.pojo.OrderLine;

public class OrderDTO {
	private int id;
	
	private int quantity;
	
	private double orderCost;
	
	private Date date;
	
	private String status;
	
	private String shippingAddress;
	
	private CustomerDetailsDTO customer;
	
	private List<OrderLineDTO> orderLines = new ArrayList<OrderLineDTO>();
	
	public OrderDTO(){
		
	}
	
	public OrderDTO(int id, int quantity, double orderCost, Date date, String shippingAddress, String status) {
		this.id = id;
		this.quantity = quantity;
		this.orderCost = orderCost;
		this.date = date;
		this.shippingAddress = shippingAddress;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public CustomerDetailsDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetailsDTO customer) {
		this.customer = customer;
	}

	public List<OrderLineDTO> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLineDTO> orderLines) {
		this.orderLines = orderLines;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
