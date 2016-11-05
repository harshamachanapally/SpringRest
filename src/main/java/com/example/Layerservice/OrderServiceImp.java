package com.example.Layerservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.DTO.OrderDTO;
import com.example.DTO.OrderLineDTO;
import com.example.Layerdao.CustomerDao;
import com.example.Layerdao.OrderDao;
import com.example.Layerdao.ProductDao;
import com.example.pojo.Customer;
import com.example.pojo.Order1;
import com.example.pojo.OrderLine;
import com.example.pojo.Product;
import com.example.utils.Conversion;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	ProductDao productDao;
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public OrderDTO addOrder(Order1 order) throws ParseException {
		
		//setting the customer details to order
		Customer customer = customerDao.getCustomer(order.getCustomer().getId());
		order.setCustomer(customer);
		Calendar cal = Calendar.getInstance();
		order.setDate(cal.getTime());
		order.setStatus("Pending");
		//updating the product details for each orderline in the order and also updating the product quantity 
		for( OrderLine orderLine : order.getOrderLines() ) {
			// setting order for each orderLine
			orderLine.setOrder(order);
			orderLine.setStatus("Pending");
			// updating the product with new quantity and setting product details with orderLine
			Product product = productDao.getProduct(orderLine.getProduct().getProductId());
			product.setQuantity( product.getQuantity() - orderLine.getQuantity());
			productDao.updateProduct(product);
			orderLine.setProduct(product);
		}
		
		Order1 orderSaved = orderDao.addOrder(order);
		OrderDTO orderDTO = Conversion.OrderToOrderDTO(orderSaved);
		return orderDTO;
	}

	public List<OrderDTO> getOrders() {
		List<Order1> orders = orderDao.getOrders();
		List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
		for(Order1 order: orders) {
			ordersDTO.add(Conversion.OrderToOrderDTO(order));
		}
		return ordersDTO;
	}

	public OrderDTO getOrder(int id) {
		Order1 order = orderDao.getOrder(id);
		return Conversion.OrderToOrderDTO(order);
	}
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public OrderDTO updateOrder(Order1 order) {
		Order1 order1 = orderDao.updateOrder(order);
		return Conversion.OrderToOrderDTO(order1);
	}
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public OrderLineDTO updateOrderLine(OrderLine orderLine,int orderId) {
		OrderLine orderLine1 = orderDao.updateOrderLine(orderLine,orderId);
		return Conversion.OrderLineToOrderLineDTO(orderLine1);
	}
	
	@Transactional( propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public boolean deleteOrder(int id) {
		return orderDao.deleteOrder(id);
	}

}
