package com.vinay.serviceImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.vinay.dao.OrdersDao;
import com.vinay.daoImpl.OrdersDaoImpl;
import com.vinay.daoImpl.UsersDAOImpl;
import com.vinay.model.Orders;
import com.vinay.model.OrdersDetails;
import com.vinay.model.Revenues;
import com.vinay.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {

	private OrdersDao order_dao;

	public OrdersServiceImpl() {
        this.order_dao = new OrdersDaoImpl();             
    }


	/* -------CLOSE  DB   CONNECTION -----------   */
	@Override
	public void closeConnection() {
		order_dao.closeConnection();
		
	}
	
	

	//Logic : As user click on PLACE ORDER BUTTON  , WE DO FOUR THINGS 

		//1) Table =>"Orders "  update cart status =>payment_status_by_user "payment done"

		//2) Table => Order_details  
		
		//3) Table => carts    intially the order_status "Incomplete" as user add item in cart 
		// so after final order set      order_status "Compelted"
		// we will fetch only those carts in UI whose   order_status "Incomplete"

		//4) Table => Revenue 
	
	
	
	

	
	
@Override
public String placeOrder(String fullName, String address, String city, String state, int zip,
		String cardName, String cardNumber, String expDate, int cvv, double total_order_cost , String [] cart_ids , int user_id) {

	
	// Logic for order_id generation :
	String order_id=null;
	String order_id_generated = UUID.randomUUID().toString().replace("-","").substring(0,5);
	DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	String currentDateTime = LocalDateTime.now().format(f);
	order_id = order_id_generated + currentDateTime;
	System.out.println(order_id);
	
	
	/* 1) Table =>"Orders "  update cart status =>payment_status_by_user "payment done"   */

	for(int i=0 ; i<cart_ids.length; i++)
	{	
		int cart_id = Integer.parseInt(cart_ids[i]);		
	Orders order = new Orders(order_id, user_id,  cart_id, "paid", "pending");
	order_dao.insertOrder(order);
	}
	
	
	
	//2) Table => Order_details  

	
	OrdersDetails order_details = new OrdersDetails(order_id, fullName, address, city, state, zip, cardName, cardNumber, expDate, cvv, user_id, total_order_cost);
	order_dao.insertOrderDetails(order_details);
	
	//3) Table => carts  Update order_status to "Completed"
	
	for(int i=0 ; i<cart_ids.length; i++)
	{	
		int cart_id = Integer.parseInt(cart_ids[i]);	
	order_dao.updateCartOrder_status(cart_id ,user_id, "paid");
	
	}
	
	
	// 4) Shop revenue Update 
	double tax = 56;
	Revenues revenues = new Revenues(order_id, total_order_cost,tax);
	
	order_dao.insertRevenues(revenues);
	
	
	
	return order_id;
}
	
	


/* As user go to order confimation page we need these 3 functions */

@Override
public List<Orders> fetchOrdersByIdAndUserId(String orderId, int userId) throws SQLException {
	// TODO Auto-generated method stub
	return order_dao.getOrdersByIdAndUserId(orderId, userId);
}

@Override
public OrdersDetails fetchOrderDetailsByIdAndUserId(String orderId, int userId) throws SQLException {
	// TODO Auto-generated method stub
	return order_dao.getOrderDetailsByIdAndUserId(orderId, userId);
}

@Override
public Revenues fetchRevenuesByIdAndUserId(String orderId, int userId) throws SQLException {
	// TODO Auto-generated method stub
	return order_dao.getRevenuesByIdAndUserId(orderId, userId);
}


@Override
public String getOrderDateByOrderId(String orderId) {
	// TODO Auto-generated method stub
	return order_dao.getOrderDateByOrderId(orderId);
}
	

public List<Orders> fetchAllOrdersByUserId(int userId) 
{
	return order_dao.getAllOrdersByUserId(userId);

}



@Override
public Orders getOrderDetailsByOrderIdAndCartId(String orderId, int cartId) {
	// TODO Auto-generated method stub
	return order_dao.getOrderDetailsByOrderIdAndCartId(orderId, cartId);
}




@Override
public boolean checkIfOrderPaymentAlredyDoneByUserForCart(int cart_id) {
	// TODO Auto-generated method stub
	return order_dao.checkIfOrderPaymentAlredyDoneByUserForCart(cart_id);
}

}
