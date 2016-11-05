package com.example.Layerdao;

import java.util.List;

import com.example.pojo.Order1;
import com.example.pojo.OrderLine;


public interface OrderDao {
	
	public Order1 addOrder(Order1 order);
	
	public List<Order1> getOrders();
	
	public Order1 getOrder(int id);
	
	public Order1 updateOrder(Order1 order);
	
	public boolean deleteOrder(int id);

	public OrderLine updateOrderLine(OrderLine orderLine, int orderId);
	
}
