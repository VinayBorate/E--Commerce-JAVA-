package com.vinay.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vinay.dao.VendorDao;
import com.vinay.model.VO.VendorOrdersVO;
import com.vinay.util.DBUtil;

public class VendorDaoImpl implements VendorDao{

	private Connection connection;

	public VendorDaoImpl() {
		super();
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
	
	
	 public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus) {
		 
	        List<VendorOrdersVO> vendorOrdersList = new ArrayList<VendorOrdersVO>();

	        // SQL query to retrieve orders for a given vendor_id and vendor_action_status
	        String query = "SELECT o.order_id, o.user_id, o.order_date, o.cart_id, c.quantity_added, c.veg_id, vd.veg_name, c.total_price " +
                    "FROM orders o " +
                    "JOIN carts c ON o.cart_id = c.cart_id AND o.user_id = c.user_id " +
                    "JOIN vegetables_details vd ON c.veg_id = vd.veg_id " +
                    "WHERE vd.vendor_id = ? AND o.vendor_action_status = ?";

	        // Assuming you have a method to get the database connection
	   
	      
	        ResultSet rs= null;
	        try (PreparedStatement ps = connection.prepareStatement(query)){
	           
	          
	            ps.setInt(1, vendor_id); // Set vendor_id parameter
	            ps.setString(2, vendorActionStatus); // Set vendor_action_status parameter

	             rs = ps.executeQuery();

	            while (rs.next()) {
	                VendorOrdersVO order = new VendorOrdersVO();
	                order.setOrder_id(rs.getString("order_id"));
	                order.setUser_id(rs.getInt("user_id"));
	                order.setOrder_date(rs.getTimestamp("order_date"));
	                order.setQuantity_added(rs.getDouble("quantity_added"));
	                order.setCart_id(rs.getInt("cart_id")); // Fetch cart_id
	                order.setVeg_id(rs.getInt("veg_id"));
	                order.setVeg_name(rs.getString("veg_name"));
	                order.setTotal_price(rs.getDouble("total_price"));

	                vendorOrdersList.add(order);
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

	        return vendorOrdersList;
	    }
	
	
	 
	    public boolean updateVendorActionStatus(String orderId, int userId, int cartId, String newVendorActionStatus) {
	        // SQL query to update the vendor_action_status field
	        String query = "UPDATE orders " +
	                       "SET vendor_action_status = ? " +
	                       "WHERE order_id = ? AND user_id = ? AND cart_id = ?";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            // Set parameters for the query
	            ps.setString(1, newVendorActionStatus);  // Set new vendor_action_status
	            ps.setString(2, orderId);                // Set order_id
	            ps.setInt(3, userId);                    // Set user_id
	            ps.setInt(4, cartId);                    // Set cart_id

	            // Execute the update query
	            int rowsAffected = ps.executeUpdate();

	            // Return true if the update was successful (i.e., at least one row was updated)
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;  // Return false if there was an exception or no rows were updated
	        }
	    }
	    
	    
	    public boolean updateQuantity(int vegId, String vegName, int vendorId, int newQuantity) {
	        // SQL query to update the quantity field
	        String query = "UPDATE vegetables_details " +
	                       "SET quantity = ? " +
	                       "WHERE veg_id = ? AND veg_name = ? AND vendor_id = ?";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            // Set parameters for the query
	            ps.setInt(1, newQuantity);        // Set new quantity
	            ps.setInt(2, vegId);              // Set veg_id
	            ps.setString(3, vegName);         // Set veg_name
	            ps.setInt(4, vendorId);           // Set vendor_id

	            // Execute the update query
	            int rowsAffected = ps.executeUpdate();

	            // Return true if the update was successful (i.e., at least one row was updated)
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;  // Return false if there was an exception or no rows were updated
	        }
	    }
	    
	    @Override
	    public double getQuantityByVegId(int vegId) {
	    	 String query = "SELECT quantity FROM vegetables_details WHERE veg_id = ?";
	    	 ResultSet rs=null;
	    	 double quantity = -1.0; // Initialize to -1 or some other value indicating not found
	    	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	    	        ps.setInt(1, vegId);
	    	         rs = ps.executeQuery();
	    	        if (rs.next()) {
	    	            quantity = rs.getDouble("quantity");
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
	    	    return quantity;
	    }
	    
	    @Override
	    public boolean updateInventoryQuantity(int vegId, double vegCount) {
	    	
	    	 String query = "UPDATE vegetables_details SET quantity = quantity - ? WHERE veg_id = ? AND quantity >= ?";
	    	    boolean isUpdated = false; // To track if the update was successful
	    	    
	    	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	    	        ps.setDouble(1, vegCount);
	    	        ps.setInt(2, vegId);
	    	        ps.setDouble(3, vegCount); // Check to ensure quantity doesn't go negative
	    	        
	    	        int affectedRows = ps.executeUpdate();
	    	        if (affectedRows > 0) {
	    	            isUpdated = true; // Update was successful
	    	        }
	    	    } catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
	    	    return isUpdated; // Return true if updated successfully, false otherwise
	    	}
	    
	    
}
