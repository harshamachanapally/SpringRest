package com.example.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Order1 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int quantity;
	
	@Column
	private double orderCost;
	
	@Column
	private Date date;
	
	@Column
	private String status;
	
	@Column
	private String shippingAddress;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OrderLine> orderLines;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		quantity = 0;
		for(OrderLine orderLine : orderLines){
			quantity = quantity + orderLine.getQuantity();
		}
		return quantity;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	
	
	
}
