package com.vinay.daoImpl;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.vinay.dao.OrdersDao;
import com.vinay.model.Orders;
import com.vinay.model.OrdersDetails;
import com.vinay.model.Revenues;
import com.vinay.util.DBUtil;

public class OrdersDaoImpl implements OrdersDao{
	private Connection connection;

	public OrdersDaoImpl() {
		this.connection = DBUtil.getConnection();
	}

	
	//--------------------------------------------------------------------------------------//
		//--------------------------------------------------------------------------------------//
	   /*------------------CLOSE COnnection When All DAO Operations are done ----------------*/	
	   //--------------------------------------------------------------------------------------//	
		//--------------------------------------------------------------------------------------//
	public void closeConnection() {
	        if (connection != null) {
	            try {
	                connection.close();
	                System.out.println("Database connection closed For Admin DAO");
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	//--------------------------------------------------------------------------------------//
	//--------------------------------------------------------------------------------------//
	//--------------------------------------------------------------------------------------//
	
	
	
	
	
	
	//1) 	Table =>"Orders "  update cart status =>payment_status_by_user "payment done"
	
	
	//2) Table => carts    intially the order_status "Incomplete" as user add item in cart 
	// so after final order set      order_status "Compelted"
	// we will fetch only those carts in UI whose   order_status "Incomplete"
	
	
	//3) Tbale 
	
	
	@Override
	public void insertOrder(Orders order) {
		// TODO Auto-generated method stub
	
		 String query = "INSERT INTO orders (order_id, user_id , cart_id, payment_status_by_user, vendor_action_status) VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            
	            ps.setString(1, order.getOrderId());
	            ps.setInt(2, order.getUserId());
	            ps.setInt(3, order.getCartId());
	            ps.setString(4, order.getPaymentStatusByUser());
	            ps.setString(5, order.getVendorActionStatus());

	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		
	
	
	
	
	@Override
	public void insertOrderDetails(OrdersDetails order_details) {
		
		 String query = "INSERT INTO order_details (order_id, full_name, address, city, state, zip, card_name, card_number, exp_date, cvv, user_id, total_order_cost) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        
        // Set the parameters for the query
        ps.setString(1, order_details.getOrderId());
        ps.setString(2, order_details.getFullName());
        ps.setString(3, order_details.getAddress());
        ps.setString(4, order_details.getCity());
        ps.setString(5, order_details.getState());
        ps.setInt(6, order_details.getZip());
        ps.setString(7, order_details.getCardName());
        ps.setString(8, order_details.getCardNumber());
        ps.setString(9, order_details.getExpDate());
        ps.setInt(10, order_details.getCvv());
        ps.setInt(11, order_details.getUserId());
        ps.setDouble(12, order_details.getTotalOrderCost());
        ps.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

		
	}
		
	

	
	@Override
	public void updateCartOrder_status(int cart_id, int user_id, String order_status) {
		
		
		String query = "UPDATE carts SET order_status = ? WHERE cart_id = ? AND user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // Set the parameters
            ps.setString(1, order_status);
            ps.setInt(2,  cart_id);
            ps.setInt(3, user_id);

            // Execute the update
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Order status updated successfully!");
            } else {
                System.out.println("No records found with the given cart_id and user_id.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
		// TODO Auto-generated method stub
		
	}
	
	
	

	@Override
	public void insertRevenues(Revenues revenues) {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO revenues (order_id, total_payment, tax) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // Set the parameters for the query
            ps.setString(1, revenues.getOrderId());
            ps.setDouble(2, revenues.getTotalPayment());
            ps.setDouble(3, revenues.getTax());

            // Execute the insert operation
            ps.executeUpdate();

            System.out.println("Revenue inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*-----------------When user goes to Order confirmation page we fetch these details  getOrderByIdAndUserId  ,  getOrderDetailsByIdAndUserId , getRevenuesByIdAndUserId-------------------*/
	
	public List<Orders> getOrdersByIdAndUserId(String orderId, int userId) throws SQLException {
	    List<Orders> ordersList = new ArrayList<Orders>();
	    String query = "SELECT * FROM orders WHERE order_id = ? AND user_id = ?";
	    ResultSet rs = null;
	    try (PreparedStatement ps = connection.prepareStatement(query)) {

	        ps.setString(1, orderId);
	        ps.setInt(2, userId);

	         rs = ps.executeQuery();

	        while (rs.next()) {
	            Orders order = new Orders();
	            order.setOrderId(rs.getString("order_id"));
	            order.setUserId(rs.getInt("user_id"));
	            order.setCartId(rs.getInt("cart_id"));
	            order.setPaymentStatusByUser(rs.getString("payment_status_by_user"));
	            order.setVendorActionStatus(rs.getString("vendor_action_status"));
	            order.setOrder_date(rs.getTimestamp("order_date"));
	            // Add the order to the list
	            ordersList.add(order);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error fetching orders by order_id and user_id", e);
	    }
        finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}

	    return ordersList;
	}

	
	

public OrdersDetails getOrderDetailsByIdAndUserId(String orderId, int userId) throws SQLException {
    OrdersDetails orderDetails = null;
    String query = "SELECT od.*, o.user_id " +
                   "FROM order_details od " +
                   "JOIN orders o ON od.order_id = o.order_id " +
                   "WHERE o.order_id = ? AND o.user_id = ?";
    ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        
        ps.setString(1, orderId);
        ps.setInt(2, userId);
        
         rs= ps.executeQuery();
        
        if (rs.next()) {
            orderDetails = new OrdersDetails();
            orderDetails.setOrderId(rs.getString("order_id"));
            orderDetails.setFullName(rs.getString("full_name"));
            orderDetails.setAddress(rs.getString("address"));
            orderDetails.setCity(rs.getString("city"));
            orderDetails.setState(rs.getString("state"));
            orderDetails.setZip(rs.getInt("zip"));
            orderDetails.setCardName(rs.getString("card_name"));
            orderDetails.setCardNumber(rs.getString("card_number"));
            orderDetails.setExpDate(rs.getString("exp_date"));
            orderDetails.setCvv(rs.getInt("cvv"));
            orderDetails.setUserId(rs.getInt("user_id"));
            orderDetails.setTotalOrderCost(rs.getDouble("total_order_cost"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error fetching order details by order_id and user_id", e);
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
    
    return orderDetails;
}

public Revenues getRevenuesByIdAndUserId(String orderId, int userId) throws SQLException {
    Revenues revenues = null;
    String query = "SELECT r.*, o.user_id " +
                   "FROM revenues r " +
                   "JOIN orders o ON r.order_id = o.order_id " +
                   "WHERE o.order_id = ? AND o.user_id = ?";

    ResultSet rs =null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        
        ps.setString(1, orderId);
        ps.setInt(2, userId);
        
         rs = ps.executeQuery();
        
        if (rs.next()) {
            revenues = new Revenues();
            revenues.setOrderId(rs.getString("order_id"));
            revenues.setTotalPayment(rs.getDouble("total_payment"));
            revenues.setTax(rs.getDouble("tax"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error fetching revenues by order_id and user_id", e);
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
    
    System.out.println("=============================================================");
    System.out.println(revenues.getOrderId());
    System.out.println(revenues.getTax());
    System.out.println(revenues.getTotalPayment());
    return revenues;
}



// Function to fetch order_date by order_id
public String getOrderDateByOrderId(String orderId) {
    String query = "SELECT order_date FROM orders WHERE order_id = ?";
    String orderDateString = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Format pattern for date

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, orderId);  // Set order_id as a parameter

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Retrieve the order_date from the result set and format it as a string
                java.sql.Timestamp orderDate = rs.getTimestamp("order_date");
                if (orderDate != null) {
                    orderDateString = dateFormat.format(orderDate); // Format Timestamp to String
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orderDateString;  // Return the formatted order_date as a String
}
	

/* ------------GET ALL ORDERS BY USER ID ----------------------------*/
public List<Orders> getAllOrdersByUserId(int userId) {
    List<Orders> ordersList = new ArrayList<>();

    String query = "SELECT * FROM orders WHERE user_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userId);  // Set user_id as a parameter

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Orders order = new Orders();
                
                // Populate the Orders object from the result set
                order.setOrderId(rs.getString("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setCartId(rs.getInt("cart_id"));
                order.setPaymentStatusByUser(rs.getString("payment_status_by_user"));
                order.setVendorActionStatus(rs.getString("vendor_action_status"));
                order.setOrder_date(rs.getTimestamp("order_date"));

                // Add the populated Orders object to the list
                ordersList.add(order);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ordersList;  // Return the list of Orders
}
	
public Orders getOrderDetailsByOrderIdAndCartId(String orderId, int cartId) {
    Orders order = null;

    // Query to fetch order details from orders table
    String query = "SELECT order_id, user_id, cart_id, order_date, payment_status_by_user, vendor_action_status " +
                   "FROM orders " +
                   "WHERE order_id = ? AND cart_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, orderId);  // Set the order_id as a parameter
        ps.setInt(2, cartId);      // Set the cart_id as a parameter

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Create and populate Orders object
                order = new Orders();
                order.setOrderId(rs.getString("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setCartId(rs.getInt("cart_id"));
                order.setOrder_date(rs.getTimestamp("order_date"));
                order.setPaymentStatusByUser(rs.getString("payment_status_by_user"));
                order.setVendorActionStatus(rs.getString("vendor_action_status"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return order;  // Return the Orders object or null if not found
}
	
		
@Override
public boolean checkIfOrderPaymentAlredyDoneByUserForCart(int cart_id) {
	
	
	String query = "SELECT order_status FROM carts WHERE cart_id = ?";
	 ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, cart_id);
         rs = ps.executeQuery();
        
        // Check if the result set has any rows
        if (rs.next()) {
            String orderStatus = rs.getString("order_status");
            return "paid".equalsIgnoreCase(orderStatus); // return true if the order status is "paid"
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
    return false; // return false if order status is not "paid" or if an exception occurs
}


	}
	
	
	
