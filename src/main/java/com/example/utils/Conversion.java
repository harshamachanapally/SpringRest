package com.example.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.DTO.AddressDTO;
import com.example.DTO.CustomerDetailsDTO;
import com.example.DTO.OrderDTO;
import com.example.DTO.OrderLineDTO;
import com.example.pojo.Address;
import com.example.pojo.Customer;
import com.example.pojo.Order1;
import com.example.pojo.OrderLine;

public class Conversion {
	
	public static OrderDTO OrderToOrderDTO(Order1 order) {
		OrderDTO orderDTO = new OrderDTO(order.getId(), order.getQuantity(), order.getOrderCost(), 
										 order.getDate(), order.getShippingAddress(),order.getStatus());
		orderDTO.setCustomer( CustomerToCustomerDetailsDTO( order.getCustomer(),"LAZY" ) );
		for(OrderLine orderLine : order.getOrderLines()) {
			orderDTO.getOrderLines().add( OrderLineToOrderLineDTO( orderLine ) );
		}
		
		return orderDTO;
	} 
	
	public static OrderLineDTO OrderLineToOrderLineDTO(OrderLine orderLine) {
		OrderLineDTO orderLineDTO = new OrderLineDTO(orderLine.getOrderLineId(),orderLine.getQuantity(),
													 orderLine.getCostperunit(),orderLine.getCost(),
													 orderLine.getProduct(),orderLine.getStatus());
		
		return orderLineDTO;
	}
	
	public static CustomerDetailsDTO CustomerToCustomerDetailsDTO(Customer customer, String type){
		List<AddressDTO> addressDTO = new ArrayList<AddressDTO>();
		if( type.equalsIgnoreCase("EAGER") ) {
			List<Address> addressList = customer.getAddress();
			if( !addressList.isEmpty() ) {
				for(Address address: addressList) {
					addressDTO.add(AddressToAddressDTO(address));
				}
			}
		}
		
		CustomerDetailsDTO customerDetailDTO = new CustomerDetailsDTO(customer.getId(), 
									customer.getName(), customer.getEmail(),addressDTO);
		
		return customerDetailDTO;
	}
	
	public static AddressDTO AddressToAddressDTO(Address address){
		AddressDTO addressDTO = new AddressDTO(address.getAddress_id(), address.getStreet(), address.getCity(),
				address.getState(), address.getPincode());
		
		return addressDTO;
	}
}
