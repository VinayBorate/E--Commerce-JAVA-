package com.vinay.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/vinayecommercedb";
	    private static final String USER = "root";
	    private static final String PASSWORD = "root";

	    public static Connection getConnection() {
	        try {
	        	
	     
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Database connection error");
	        }
	    }


}
