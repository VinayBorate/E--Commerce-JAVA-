package com.vinay.service;

import java.util.List;

import com.vinay.model.Revenues;
import com.vinay.model.Users;

public interface AdminService {
	
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();

	  public List<Users>fetchAllApprovedUsers();
	  
	  public List<Users>fetchAllNotApprovedUsers();
	  
	  public List<Users>fetchAllRejectedUsers();
	  
	  public List<Users>fetchAllDeletedUsers();
	  
	  public List<Users>fetchAllActive() ;
	  
	  public void approvalService(int id);
	  
	  public void reapproveService(int id);
	  
	  public void rejectService(int id);

	  public void reactivateService(int id);
	  
	  public void deactivateService(int id);
	  
	  public Revenues fetchRevenueByOrderId(String orderId);
	  
	  public String decrypt(String s) throws Exception;
	  
	  public List<Revenues> fetchAllRevenues();
	  
	  
}
