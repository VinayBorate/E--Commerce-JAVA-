package com.vinay.service;

import java.util.List;

import com.vinay.model.Carts;

public interface CartsService {

	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice);

	public List<Carts> fetchAllCartsByUserID(int user_id);
	
	public void deletePendingCart(int vegId, int userId);
	
	 public Carts fetchCartDetailsByCartId(int cartId); 
	 
	 public List<Carts> getCartsByOrderId(String orderId);
	
}
