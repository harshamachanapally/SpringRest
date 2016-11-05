package com.example.Layerservice;

import java.text.ParseException;
import java.util.List;

import com.example.DTO.OrderDTO;
import com.example.pojo.Order1;



public interface OrderService {
	
	public OrderDTO addOrder(Order1 order) throws ParseException;
	
	public List<OrderDTO> getOrders();
	
	public OrderDTO getOrder(int id);
	
	public OrderDTO updateOrder(Order1 order);
	
	public boolean deleteOrder(int id);
	
}
