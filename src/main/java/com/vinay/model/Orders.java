package com.vinay.model;

import java.sql.Timestamp;

public class Orders {
    private String orderId;
    private int userId;
    private int cartId;
    private String paymentStatusByUser;
    private String vendorActionStatus;
    private Timestamp order_date;
    
    
    public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Orders(String orderId, int userId, int cartId, String paymentStatusByUser, String vendorActionStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.cartId = cartId;
        this.paymentStatusByUser = paymentStatusByUser;
        this.vendorActionStatus = vendorActionStatus;
    }


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getPaymentStatusByUser() {
		return paymentStatusByUser;
	}

	public void setPaymentStatusByUser(String paymentStatusByUser) {
		this.paymentStatusByUser = paymentStatusByUser;
	}

	public String getVendorActionStatus() {
		return vendorActionStatus;
	}

	public void setVendorActionStatus(String vendorActionStatus) {
		this.vendorActionStatus = vendorActionStatus;
	}


	public Timestamp getOrder_date() {
		return order_date;
	}


	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

    






}
