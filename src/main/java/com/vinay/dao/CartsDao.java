package com.vinay.dao;

import java.util.List;

import com.vinay.model.Carts;

public interface CartsDao {
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice);

	public List<Carts> getAllCartsByUserID(int user_id);
	
	
	public void removePendingCart(int vegId, int userId);
	
	 public Carts getCartDetailsByCartId(int cartId) ;
	 
	 public List<Carts> getCartsByOrderId(String orderId);
}
