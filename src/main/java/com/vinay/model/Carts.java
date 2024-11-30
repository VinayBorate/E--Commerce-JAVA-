package com.vinay.model;

import java.sql.Timestamp;

public class Carts {
	
	private int cart_Id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int user_Id;
    private int veg_id;
    private double quantity_added;
    private double total_Price;
    private String order_status;
    
    
    public Carts() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	public Carts(int cart_Id, Timestamp createdAt, Timestamp updatedAt, int user_Id, int veg_id, double quantity_added,
			double total_Price , String order_status) {
		super();
		this.cart_Id = cart_Id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user_Id = user_Id;
		this.veg_id = veg_id;
		this.quantity_added = quantity_added;
		this.total_Price = total_Price;
		this.order_status = order_status;
	}
	
	
	
	public int getCart_Id() {
		return cart_Id;
	}
	public void setCart_Id(int cart_Id) {
		this.cart_Id = cart_Id;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public int getVeg_id() {
		return veg_id;
	}
	public void setVeg_id(int veg_id) {
		this.veg_id = veg_id;
	}
	public double getQuantity_added() {
		return quantity_added;
	}
	public void setQuantity_added(double quantity_added) {
		this.quantity_added = quantity_added;
	}
	public double getTotal_Price() {
		return total_Price;
	}
	public void setTotal_Price(double total_Price) {
		this.total_Price = total_Price;
	}


	public String getOrder_status() {
		return order_status;
	}


	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

    
    

}
