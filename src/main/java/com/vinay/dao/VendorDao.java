package com.vinay.dao;

import java.util.List;

import com.vinay.model.VO.VendorOrdersVO;

public interface VendorDao {
	
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	
	
	
	public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus);
	 public boolean updateVendorActionStatus(String orderId, int userId, int cartId, String newVendorActionStatus);
	 public boolean updateQuantity(int vegId, String vegName, int vendorId, int newQuantity);
	 
	  public double getQuantityByVegId(int vegId);
	  
	  public boolean updateInventoryQuantity(int vegId, double vegCount);

}
