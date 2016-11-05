package com.example.Layerdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.Layerrepository.OrderLineRepository;
import com.example.Layerrepository.OrderRepository;
import com.example.exception.DataNotFoundException;
import com.example.pojo.Order1;
import com.example.pojo.OrderLine;

@Repository
@Transactional( propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class OrderDaoImp implements OrderDao {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderLineRepository orderLineRepository;
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@CachePut(value="orders", key="#result.id")
	public Order1 addOrder(Order1 order) {
		Order1 orderSaved = orderRepository.save(order);
		return orderSaved;
	}
	
	@Cacheable(value="orders")
	public List<Order1> getOrders() {
		List<Order1> orders = (List<Order1>) orderRepository.findAll();
		return orders;
	}
	
	@Cacheable(value="orders", key="#id")
	public Order1 getOrder(int id) {
		Order1 order = orderRepository.findOne(id);
		if(order==null)
			throw new DataNotFoundException("No order exists with id "+id);
		return order;
	}
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@CachePut(value="orders", key="#result.id")
	public Order1 updateOrder(Order1 order) {
		Order1 order1 = orderRepository.findOne(order.getId());
		for( OrderLine orderLine : order.getOrderLines() ) {
			updateOrderLine(orderLine,order.getId());
		}
		order1.setStatus(order.getStatus());
		return order1;
	}
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@CacheEvict(value="orders", key="#id")
	public boolean deleteOrder(int id) {
		if( orderRepository.findOne(id) != null ) {
			orderRepository.delete(id);
			return true;
		}
		throw new DataNotFoundException("No order exits with id "+id);
	}
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public OrderLine updateOrderLine(OrderLine orderLine,int orderId) {
		OrderLine orderLine1 = orderLineRepository.findOne(orderLine.getOrderLineId());
		if( orderLine1 != null && orderLine1.getOrder().getId() == orderId ) {
			orderLine1.setStatus(orderLine.getStatus());
			orderLineRepository.save(orderLine1);
			return orderLine1;
		}
		throw new DataNotFoundException("Orderline doesn't exit or order id "+orderId+" doesn't have have this orderline");
	}
}
