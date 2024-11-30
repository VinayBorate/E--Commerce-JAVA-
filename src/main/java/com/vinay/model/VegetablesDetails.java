package com.vinay.model;

import java.sql.Timestamp;

public class VegetablesDetails {
    
    private int vegId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int vendorId;
    private String vegName;
    private int quantity;
    private String description;
    private double pricePerPiece;
    private double discount_per_piece;
    private double net_price;
    private String vegPicName;
    private String vegCategory;
    
    
	public VegetablesDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public VegetablesDetails(int vendorId, String vegName, int quantity,
			String description, double pricePerPiece, String vegPicName, String vegCategory ,double discount_per_piece, double net_price) {
		super();

		this.vendorId = vendorId;
		this.vegName = vegName;
		this.quantity = quantity;
		this.description = description;
		this.pricePerPiece = pricePerPiece;
		this.vegPicName = vegPicName;
		this.vegCategory = vegCategory;
		this.discount_per_piece=discount_per_piece;
		this. net_price= net_price;
	}

	
	
	

	public VegetablesDetails(int vegId,int vendorId, String vegName, int quantity,
			String description, double pricePerPiece, String vegPicName, String vegCategory ,double discount_per_piece, double net_price) {
		super();
		this.vegId = vegId;
		this.vendorId = vendorId;
		this.vegName = vegName;
		this.quantity = quantity;
		this.description = description;
		this.pricePerPiece = pricePerPiece;
		this.discount_per_piece = discount_per_piece;
		this.net_price = net_price;
		this.vegPicName = vegPicName;
		this.vegCategory = vegCategory;
	}


	public int getVegId() {
		return vegId;
	}


	public void setVegId(int vegId) {
		this.vegId = vegId;
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


	public int getVendorId() {
		return vendorId;
	}


	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}


	public String getVegName() {
		return vegName;
	}


	public void setVegName(String vegName) {
		this.vegName = vegName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPricePerPiece() {
		return pricePerPiece;
	}


	public void setPricePerPiece(double pricePerPiece) {
		this.pricePerPiece = pricePerPiece;
	}


	public String getVegPicName() {
		return vegPicName;
	}


	public void setVegPicName(String vegPicName) {
		this.vegPicName = vegPicName;
	}


	public String getVegCategory() {
		return vegCategory;
	}


	public void setVegCategory(String vegCategory) {
		this.vegCategory = vegCategory;
	}


	public double getDiscount_per_piece() {
		return discount_per_piece;
	}


	public void setDiscount_per_piece(double discount_per_piece) {
		this.discount_per_piece = discount_per_piece;
	}


	public double getNet_price() {
		return net_price;
	}


	public void setNet_price(double net_price) {
		this.net_price = net_price;
	}


	@Override
	public String toString() {
		
		
		return "VegetablesDetails [vegId=" + vegId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", vendorId=" + vendorId + ", vegName=" + vegName + ", quantity=" + quantity + ", description="
				+ description + ", pricePerPiece=" + pricePerPiece + ", discount_per_piece=" + discount_per_piece
				+ ", net_price=" + net_price + ", vegPicName=" + vegPicName + ", vegCategory=" + vegCategory + "]";
	}
    
    
	
	
	
	

}

   