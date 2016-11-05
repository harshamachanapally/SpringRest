package com.example.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderLineId;
	
	@Column
	private int quantity;
	
	@Column
	private double costperunit;
	
	@Column
	private double cost;
	
	@Column
	private String status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="orderId")
	private Order1 order;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;

	public int getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Order1 getOrder() {
		return order;
	}

	public void setOrder(Order1 order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getCostperunit() {
		return costperunit;
	}

	public void setCostperunit(double costperunit) {
		this.costperunit = costperunit;
	}
	
	
}
