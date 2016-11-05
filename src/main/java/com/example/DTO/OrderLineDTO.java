package com.example.DTO;


import com.example.pojo.Order1;
import com.example.pojo.Product;

public class OrderLineDTO {
	
	private int orderLineId;
	
	private int quantity;
	
	private double costperunit;
	
	private double cost;
	
	private String status;
	
	private Product product;
		
	public OrderLineDTO(){
		
	}

	public OrderLineDTO(int orderLineId, int quantity, double costperunit, double cost, Product product, String status) {
		this.orderLineId = orderLineId;
		this.quantity = quantity;
		this.costperunit = costperunit;
		this.cost = cost;
		this.product = product;
		this.status = status;
	}

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

	public double getCostperunit() {
		return costperunit;
	}

	public void setCostperunit(double costperunit) {
		this.costperunit = costperunit;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
