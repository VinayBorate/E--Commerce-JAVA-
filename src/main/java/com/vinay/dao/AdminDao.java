package com.vinay.dao;

import java.util.List;

import com.vinay.model.Revenues;
import com.vinay.model.Users;

public interface AdminDao {

	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	  public List<Users> getAllApprovedUsers();
	  
	  public List<Users> getAllNotApprovedUsers();
	  
	  public List<Users> getAllRejectedUsers();
	  
	  public List<Users> getAllDeletedUsers();
	  
	  public List<Users> getAllActive() ;
	  
	  public void approveUser(int id);
	  
	  public void reapproveUser(int id);
	  
	  public void rejectUser(int id);
	  
	  public void reactivateAccount(int id);
	  
	  public void deactivateAccount(int id);
	  
	  Revenues getRevenueByOrderId(String orderId) ;
	  public List<Revenues> fetchAllRevenues();
	  
}
