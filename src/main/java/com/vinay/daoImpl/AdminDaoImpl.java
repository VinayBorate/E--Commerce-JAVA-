package com.vinay.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;

import com.vinay.dao.AdminDao;
import com.vinay.model.Revenues;
import com.vinay.model.Users;
import com.vinay.util.DBUtil;

public class AdminDaoImpl implements AdminDao {

	private Connection connection;
	
	public AdminDaoImpl() {
		
		
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




/* -----------------FETCH ALL APPROVED USERS Where isapproved="approved" ---------------------------*/
	
	public List<Users> getAllApprovedUsers() {
        List<Users> approvedUsersList = new ArrayList<Users>();
        String query = "SELECT id, username, email, isapproved , isactive FROM users WHERE isapproved = 'approved' AND usertype <> 'admin'";
      
// Using try(Resources) { }  so that ResultSet, Statement are closed     
        try(PreparedStatement ps =  connection.prepareStatement(query); 
        		  ResultSet rs = ps.executeQuery();)  {
           
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setIsapproved(rs.getString("isapproved"));
                user.setIsactive(rs.getString("isactive"));

                approvedUsersList.add(user); // Add user details to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return approvedUsersList; // Return the list of approved users
               
    }
  
  
  
  
  /* -----------------FETCH NOT APPROVED USERS  Where isapproved="notapproved" ---------------------------*/
  
	public List<Users> getAllNotApprovedUsers() {
        List<Users> notApprovedUsersList = new ArrayList<Users>();
        String query = "SELECT id ,username, email, isapproved FROM users WHERE isapproved = 'notapproved' AND usertype <> 'admin' ";

        try (PreparedStatement ps =  connection.prepareStatement(query); 
      		  ResultSet rs = ps.executeQuery();) {
            

            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setIsapproved(rs.getString("isapproved"));

                notApprovedUsersList.add(user); // Add user details to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return notApprovedUsersList; // Return the list of not approved users
    }
  
	
	
  
  
  /* -----------------FETCH ALL REJECTED  USERS  Where  isapproved ="rejected" ---------------------------*/
  
	public List<Users> getAllRejectedUsers() {
        List<Users> rejectedUsersList = new ArrayList<>();
        String query = "SELECT id, username, email, isapproved FROM users WHERE isapproved = 'rejected' AND usertype <> 'admin'";

        try (PreparedStatement ps =  connection.prepareStatement(query); 
      		  ResultSet rs = ps.executeQuery();) {
            

            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setIsapproved(rs.getString("isapproved"));

                rejectedUsersList.add(user); // Add user details to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rejectedUsersList; // Return the list of rejected users
    }
  
  
  
  /* ------------------FETCH ALL DELETED USERS  =>  WHERE isapproved ="approved" , AND isactive="inactive"   --------------------------- */
  
  
	  public List<Users> getAllDeletedUsers() {
	        List<Users> deletedUsersList = new ArrayList<>();
	        String query = "SELECT id, username, email, isapproved, isactive FROM users WHERE isapproved = 'approved' AND isactive = 'inactive' AND usertype <> 'admin'";

	        try (PreparedStatement ps =  connection.prepareStatement(query); 
	        		  ResultSet rs = ps.executeQuery();) {
	            
	            while (rs.next()) {
	                Users user = new Users();
	                user.setId(rs.getInt("id"));
	                user.setUsername(rs.getString("username"));
	                user.setEmail(rs.getString("email"));
	                user.setIsapproved(rs.getString("isapproved"));
	                user.setIsactive(rs.getString("isactive"));

	                deletedUsersList.add(user); // Add user details to the list
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return deletedUsersList; // Return the list of deleted users
	    }
  
  /*--------------------FTECH ALL ACTIVE USERS => WHERE isapproved="approved" , isactive="active"  ----------------------*/
  
  
	  public List<Users> getAllActive() {
	        List<Users> activeUsersList = new ArrayList<>();
	        String query = "SELECT id , username, email, isapproved, isactive FROM users WHERE isapproved = 'approved' AND isactive = 'active'";

	        try (PreparedStatement ps =  connection.prepareStatement(query); 
	        		  ResultSet rs = ps.executeQuery();)  {
	           

	            while (rs.next()) {
	                Users user = new Users();
	                user.setId(rs.getInt("id"));
	                user.setUsername(rs.getString("username"));
	                user.setEmail(rs.getString("email"));
	                user.setIsapproved(rs.getString("isapproved"));
	                user.setIsactive(rs.getString("isactive"));

	                activeUsersList.add(user); // Add user details to the list
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return activeUsersList; // Return the list of active users
	    }
  
	
	  
	  
	  /*----------------Approve button  based on id   set isapproved="approved" --------------------*/
	  
	  public void approveUser(int id) {
	        String query = "UPDATE users SET isapproved = 'approved', updated_at = NOW() WHERE id = ?";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, id);  // Set the user id

	            int rowsUpdated = ps.executeUpdate();  // Execute the update query

	            if (rowsUpdated > 0) {
	                System.out.println("User with ID " + id + " has been approved successfully.");
	            } else {
	                System.out.println("No user found with ID " + id);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  

  
  
  /* Reapprove Users */
  
  
	  public void reapproveUser(int id) {
	        String query = "UPDATE users SET isapproved = 'approved', updated_at = NOW() WHERE id = ? AND isapproved != 'approved'";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, id);  // Set the user id

	            int rowsUpdated = ps.executeUpdate();  // Execute the update query

	            if (rowsUpdated > 0) {
	                System.out.println("User with ID " + id + " has been reapproved successfully.");
	            } else {
	                System.out.println("User with ID " + id + " is already approved or not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
  
	  
	  
	  /*----------------Reject button  based on id   set isapproved="rejected" --------------------*/
	   
	  public void rejectUser(int id) {
	        String query = "UPDATE users SET isapproved = 'rejected', updated_at = NOW() WHERE id = ?";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, id);  // Set the user id

	            int rowsUpdated = ps.executeUpdate();  // Execute the update query

	            if (rowsUpdated > 0) {
	                System.out.println("User with ID " + id + " has been rejected.");
	            } else {
	                System.out.println("User with ID " + id + " not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
  
  
  /* ----------------- Reactivate Account based on  id   set isactive="active" -----------------*/
  
	  public void reactivateAccount(int id) {
	        String query = "UPDATE users SET isactive = 'active', updated_at = NOW() WHERE id = ?";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, id);  // Set the user id

	            int rowsUpdated = ps.executeUpdate();  // Execute the update query

	            if (rowsUpdated > 0) {
	                System.out.println("Account with ID " + id + " has been reactivated.");
	            } else {
	                System.out.println("Account with ID " + id + " not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  
	  /* ----------------- Deactivate Account based on  id   set isactive="inactive" -----------------*/
	  
	  public void deactivateAccount(int id) {
	        String query = "UPDATE users SET isactive = 'inactive', updated_at = NOW() WHERE id = ?";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, id);  // Set the user id

	            int rowsUpdated = ps.executeUpdate();  // Execute the update query

	            if (rowsUpdated > 0) {
	                System.out.println("Account with ID " + id + " has been deactivated.");
	            } else {
	                System.out.println("Account with ID " + id + " not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }  
	  
	  
	  
	  
 /*------------------------ FETCH SALES REVENUE GENERATED ---------------------------*/
	  
	  public Revenues getRevenueByOrderId(String orderId) {
	        Revenues revenue = null;
	        String query = "SELECT * FROM revenues WHERE order_id = ?";
	        ResultSet rs = null;
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setString(1, orderId); 

	             rs= ps.executeQuery(); 

	            if (rs.next()) {
	                
	                revenue = new Revenues();
	                revenue.setOrderId(rs.getString("order_id"));
	                revenue.setTotalPayment(rs.getDouble("total_payment"));
	                revenue.setTax(rs.getDouble("tax"));
	            } else {
	                System.out.println("Revenue for order_id: " + orderId + " not found.");
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

	        return revenue;  
	    }
	  
  
	  
	  
	  @Override
	public List<Revenues> fetchAllRevenues() {
		  List<Revenues> revenueList = new ArrayList<>();
		    String query = "SELECT order_id, total_payment, tax FROM revenues";
		    
		    try (PreparedStatement ps = connection.prepareStatement(query)) {
		        ResultSet rs = ps.executeQuery();
		        
		        // Loop through the result set and map each row to the Revenues model
		        while (rs.next()) {
		            Revenues revenue = new Revenues();
		            revenue.setOrderId(rs.getString("order_id"));
		            revenue.setTotalPayment(rs.getDouble("total_payment"));
		            revenue.setTax(rs.getDouble("tax"));
		            
		            // Add the revenue object to the list
		            revenueList.add(revenue);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return revenueList; // Return the list of revenues
	}
	  
	  
  }
	
	

