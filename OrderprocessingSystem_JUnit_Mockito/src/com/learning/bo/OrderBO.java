package com.learning.bo;

import com.learning.bo.exception.BOException;
import com.learning.dto.Order;

public interface OrderBO {

	 boolean placeOrder(Order order) throws BOException;
	 
	 boolean cancelOrder(int id) throws BOException;
	 
	 boolean deleteOrder(int id) throws BOException;
}
