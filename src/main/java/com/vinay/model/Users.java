package com.vinay.model;

public class Users {

	 private int id;
	    private String username;
	    private String password;
	    private String email;
	    private String isapproved;
	    private String isactive;
	    private String usertype;
	    
	    
	    private String mobileno;
	    private String address;
	    private String state;
	    private String city;
	    private int pincode ;
	    
	    
	    //Constructor
		public Users() {
			super();
			// TODO Auto-generated constructor stub
		}

	    //Constructor with fields 
		public Users(int id, String username, String password, String email, String isapproved, String isactive,
				String usertype) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.email = email;
			this.isapproved = isapproved;
			this.isactive = isactive;
			this.usertype = usertype;
		}
		
		

		
		public Users(int id, String username, String password, String email, String isapproved, String isactive,
				String usertype, String mobileno, String address, String state, String city, int pincode) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.email = email;
			this.isapproved = isapproved;
			this.isactive = isactive;
			this.usertype = usertype;
			this.mobileno = mobileno;
			this.address = address;
			this.state = state;
			this.city = city;
			this.pincode = pincode;
		}

		//Getter Setter
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getIsapproved() {
			return isapproved;
		}

		public void setIsapproved(String isapproved) {
			this.isapproved = isapproved;
		}

		public String getIsactive() {
			return isactive;
		}

		public void setIsactive(String isactive) {
			this.isactive = isactive;
		}

		public String getUsertype() {
			return usertype;
		}

		public void setUsertype(String usertype) {
			this.usertype = usertype;
		}

		public String getMobileno() {
			return mobileno;
		}

		public void setMobileno(String mobileno) {
			this.mobileno = mobileno;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPincode() {
			return pincode;
		}

		public void setPincode(int pincode) {
			this.pincode = pincode;
		}
		
	    
		
}
