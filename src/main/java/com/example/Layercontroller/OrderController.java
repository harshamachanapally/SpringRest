package com.example.Layercontroller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.OrderDTO;
import com.example.Layerservice.OrderService;
import com.example.Layerservice.ProductService;
import com.example.pojo.Order1;
import com.example.pojo.Product;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/Orders",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO addOrder(@RequestBody Order1 order) throws ParseException {
		return orderService.addOrder(order);
	}
	
	@RequestMapping(value="/Orders",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> getOrders() {
		return orderService.getOrders();
	}
	
	@RequestMapping(value="/Orders/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO getOrder(@PathVariable("id") int id) {
		return orderService.getOrder(id);
	}
	
	@RequestMapping(value="/Orders/{id}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO updateProduct(@RequestBody Order1 order, @PathVariable("id") int id) {
		order.setId(id);
		return orderService.updateOrder(order);
	}	
	
	@RequestMapping(value="/Orders/{id}",method = RequestMethod.DELETE)
	public boolean deleteOrder(@PathVariable("id") int id) {
		return orderService.deleteOrder(id);
	}

}
