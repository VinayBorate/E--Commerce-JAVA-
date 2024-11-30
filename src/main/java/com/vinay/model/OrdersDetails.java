package com.vinay.model;

public class OrdersDetails {
	
    private String orderId;
    private String fullName;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String cardName;
    private String cardNumber;
    private String expDate;
    private int cvv;
    private int userId;
    private double totalOrderCost;
	
    
    
    
    public OrdersDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersDetails(String orderId, String fullName, String address, String city, String state, int zip,
			String cardName, String cardNumber, String expDate, int cvv, int userId, double totalOrderCost) {
		super();
		this.orderId = orderId;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.cvv = cvv;
		this.userId = userId;
		this.totalOrderCost = totalOrderCost;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalOrderCost() {
		return totalOrderCost;
	}

	public void setTotalOrderCost(double totalOrderCost) {
		this.totalOrderCost = totalOrderCost;
	}
    
    
    
    
    
    
	
	
	

}
