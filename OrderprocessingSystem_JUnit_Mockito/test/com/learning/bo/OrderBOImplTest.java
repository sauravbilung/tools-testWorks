package com.learning.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.learning.bo.exception.BOException;
import com.learning.dao.OrderDAO;
import com.learning.dto.Order;

public class OrderBOImplTest {

	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;

	@Before
	public void setup() {
		// Mockito API to initialize the mocks. In our case OrderDAO object
		// "this" is object reference to the OrderBOImplTest
		// it will initialize the objects which are annotated with @Mock.
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}

	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {

		Order order = new Order();
		// setting expections(stubbing), what should happen when the methods
		// inside the method that we are testing are encountered. We set a particular
		// behaviour for that. In the below example we configured that the method should
		// return 1 when it is called and it should only return 1.
		// "when" is used to mock out a method call. We are mocking out
		// create() method inside placeOrder() method for testing.
		when(dao.create(order)).thenReturn(new Integer(1));
		// placeOrder will call the create() method and the value will be returned as 1
		// and the result will set out to be true.
		boolean result = bo.placeOrder(order);
		assertTrue(result);

		// Verifying that mocked out method is getting called(dao.create()) when
		// the placeOrder method is called.
		verify(dao).create(order);
	}

	@Test
	public void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException {

		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);
		assertFalse(result);
		verify(dao).create(order);
	}

	@Test(expected = BOException.class)
	public void placeOrder_Should_Throw_Exception() throws SQLException, BOException {

		// Exception should be thrown when placeOrder() is called
		Order order = new Order();
		// SQLException is expected inside the test method but ultimately
		// as an end result we are expected with BOException that's why
		// BOException is set in expected value.
		when(dao.create(order)).thenThrow(SQLException.class);
		boolean result = bo.placeOrder(order);
	}

	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(123);

		assertTrue(result);
		verify(dao).read(123);
		verify(dao).update(order);
	}

	@Test
	public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(123);

		assertFalse(result);
		verify(dao).read(123);
		verify(dao).update(order);
	}

	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throw_BO_Exception_On_Read() throws SQLException, BOException {

		when(dao.read(123)).thenThrow(SQLException.class);
		boolean result=bo.cancelOrder(123);

	}

	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throw_BO_Order_On_Update() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		boolean result=bo.cancelOrder(123);
	}

	@Test
	public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
		when(dao.delete(123)).thenReturn(1);
		boolean result=bo.deleteOrder(123);
		assertTrue(result);
		verify(dao).delete(123);
	}
	
	@Test
	public void deleteOrder_Should_Not_Delete_The_Order() throws SQLException, BOException {
		when(dao.delete(123)).thenReturn(0);
		boolean result=bo.deleteOrder(123);
		assertFalse(result);
		verify(dao).delete(123);
	}
	
	@Test(expected=BOException.class)
	public void deleteOrder_Should_Throw_BO_Exception() throws SQLException, BOException {
		when(dao.delete(123)).thenThrow(SQLException.class);
		boolean result=bo.deleteOrder(123);
	}
}
