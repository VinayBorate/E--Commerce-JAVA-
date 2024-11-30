package com.vinay.service;

public interface CheckoutService {
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	
	public void updateCartItem(int veg_id, int user_id, int cart_id, double quantity_added, double total_price);

}
