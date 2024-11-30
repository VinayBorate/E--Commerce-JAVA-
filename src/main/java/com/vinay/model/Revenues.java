package com.vinay.model;

public class Revenues {
	 private String orderId;     
	    private double totalPayment; 
	    private double tax;
		
	    
	    public Revenues(String orderId, double totalPayment, double tax) {
			super();
			this.orderId = orderId;
			this.totalPayment = totalPayment;
			this.tax = tax;
		}

	    
	    

		public Revenues() {
			super();
			// TODO Auto-generated constructor stub
		}




		public String getOrderId() {
			return orderId;
		}


		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}


		public double getTotalPayment() {
			return totalPayment;
		}


		public void setTotalPayment(double totalPayment) {
			this.totalPayment = totalPayment;
		}


		public double getTax() {
			return tax;
		}


		public void setTax(double tax) {
			this.tax = tax;
		}         
	    
	    
	    
}
