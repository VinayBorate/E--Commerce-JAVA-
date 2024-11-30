package com.vinay.serviceImpl;

import java.util.List;

import com.vinay.dao.VendorDao;
import com.vinay.daoImpl.VendorDaoImpl;
import com.vinay.model.VO.VendorOrdersVO;
import com.vinay.service.VegetablesService;
import com.vinay.service.VendorService;
import com.vinay.util.RSAUtil_DECRYPTION;

public class VendorServiceImpl implements VendorService{
	
	private VendorDao dao;

	public VendorServiceImpl() {

		this.dao = new VendorDaoImpl();
	}
	
	
	   /* -------CLOSE  DB   CONNECTION -----------   */
	
	
	@Override
	public void closeConnection() {
		dao.closeConnection();
	}
	
	
	public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus)
	{
		
		return dao.getVendorOrders(vendor_id, vendorActionStatus);
		
	}

	
	 public boolean updateVendorActionStatus(String orderId, int userId, int cartId, String newVendorActionStatus)
	 {
		 
		 return dao.updateVendorActionStatus(orderId, userId, cartId, newVendorActionStatus);
	 }
	
	 
	 
	 @Override
	public boolean updateQuantity(int vegId, String vegName, int vendorId, int newQuantity) {
		// TODO Auto-generated method stub
		return dao.updateQuantity(vegId, vegName, vendorId, newQuantity);
	}
	 
	 
	 /*-----------------------INVENTORY LOGIC ----------------------*/
	 @Override
	public boolean checkandUpdateInventory(double user_ordered_quantity, int user_ordered_VEG_ID) {
		
		 
		double stock_veg_id = dao.getQuantityByVegId(user_ordered_VEG_ID); // FETCH VEG AVAILABLE  STOCK FIRST 
		
		if(stock_veg_id > user_ordered_quantity)
		{
			// STOCK AVALABLE SO REDUCE INVENTORY COUNT 
			boolean b = dao.updateInventoryQuantity(user_ordered_VEG_ID, user_ordered_quantity);
			return b; 
			
		}
		else 
		{
			// Not sufficient stock in inventory 
			return false;
		}
		 
		
	}
	 
	 

@Override
	public String decrypt(String s) throws Exception {
	
	RSAUtil_DECRYPTION obj = new RSAUtil_DECRYPTION();
	return (obj.decrypt(s));
	
	}




}
