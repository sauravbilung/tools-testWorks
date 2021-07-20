package com.learning.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.learning.dao.OrderDAO;
import com.learning.dto.Order;

public class OrderBOImplTest {

	@Mock
	OrderDAO dao;

	@Before
	public void setup() {
		// Mockito API to initialize the mocks. In our case OrderDAO object
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException {
		OrderBOImpl bo = new OrderBOImpl();
		bo.setDao(dao);

		Order order = new Order();
		// setting expections, what should happen
		when(dao.create(order)).thenReturn(new Integer(0));
	}

}
