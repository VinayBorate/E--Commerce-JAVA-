package com.vinay.dao;

import java.sql.SQLException;
import java.util.List;

import com.vinay.model.Orders;
import com.vinay.model.OrdersDetails;
import com.vinay.model.Revenues;

public interface OrdersDao {

	
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	
	void insertOrder(Orders order);


	void insertOrderDetails(OrdersDetails order_details);


	void updateCartOrder_status(int cart_id, int user_id, String string);


	void insertRevenues(Revenues revenues);

	
	public List<Orders> getOrdersByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public OrdersDetails getOrderDetailsByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public Revenues getRevenuesByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public String getOrderDateByOrderId(String orderId);
	
	public List<Orders> getAllOrdersByUserId(int userId) ;
	
	public Orders getOrderDetailsByOrderIdAndCartId(String orderId, int cartId) ;
	
public boolean checkIfOrderPaymentAlredyDoneByUserForCart(int cart_id);
	
}
