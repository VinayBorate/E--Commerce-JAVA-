package com.vinay.model.VO;

import java.security.Timestamp;

public class VendorOrdersVO {
String order_id ; 
int user_id ; 
java.sql.Timestamp order_date;
double quantity_added ;
int veg_id ; 
String veg_name;
double total_price;
int cart_id; 

public VendorOrdersVO() {
	super();
	// TODO Auto-generated constructor stub
}
public VendorOrdersVO(String order_id, int user_id, java.sql.Timestamp order_date, double quantity_added, int veg_id,
		String veg_name, double total_price , int cart_id) {
	super();
	this.cart_id = cart_id;
	this.order_id = order_id;
	this.user_id = user_id;
	this.order_date = order_date;
	this.quantity_added = quantity_added;
	this.veg_id = veg_id;
	this.veg_name = veg_name;
	this.total_price = total_price;
}
public String getOrder_id() {
	return order_id;
}
public void setOrder_id(String order_id) {
	this.order_id = order_id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public java.sql.Timestamp getOrder_date() {
	return order_date;
}
public void setOrder_date(java.sql.Timestamp order_date) {
	this.order_date = order_date;
}
public double getQuantity_added() {
	return quantity_added;
}
public void setQuantity_added(double quantity_added) {
	this.quantity_added = quantity_added;
}
public int getVeg_id() {
	return veg_id;
}
public void setVeg_id(int veg_id) {
	this.veg_id = veg_id;
}
public String getVeg_name() {
	return veg_name;
}
public void setVeg_name(String veg_name) {
	this.veg_name = veg_name;
}
public double getTotal_price() {
	return total_price;
}
public void setTotal_price(double total_price) {
	this.total_price = total_price;
}
public int getCart_id() {
	return cart_id;
}
public void setCart_id(int cart_id) {
	this.cart_id = cart_id;
}




}