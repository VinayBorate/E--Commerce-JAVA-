package com.vinay.service;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import com.vinay.model.VegetablesDetails;

public interface VegetablesService {
	
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	public void addVegetable(VegetablesDetails details, Part vegPicPart, String contextPath)throws Exception;
	
	 public List<VegetablesDetails> fetchAllVegetablesBycategory(String Category) throws Exception ; 
	    
	 public List<VegetablesDetails> fetchAllVegetablesByVendorId(int vendor_id) throws Exception ; 
	 public List<VegetablesDetails> fetchAllDeletedVegetablesByVendorId(int vendor_id) throws Exception;

	 public List<VegetablesDetails> fetchAllVegetablesInStockByVendorId(int vendor_id) throws Exception ; 

	 public List<VegetablesDetails> fetchAllVegetablesOutOfStockByVendorId(int vendor_id) throws Exception ; 

     public  VegetablesDetails fetchVegetableById(int veg_id) throws Exception ; 

     public List<String> fetchAllVendorsNames() throws Exception ; 
     
     public String getVendorUsernameByVegId(int vegId) throws Exception;
     
     public VegetablesDetails fetchVegetableDetailsByCartId(int cartId) throws Exception ;
     
     public List<VegetablesDetails> getVegetablesDetailsByOrderId(String orderId) throws Exception;
     
     public void updateVegetable(VegetablesDetails details, Part vegPicPart, String path) throws Exception;
     
     public boolean deleteVegetableById(int vegId) throws Exception;
     public boolean restoreVegetableById(int vegId) throws Exception;
     
     public VegetablesDetails get_A_VegetableById(int vegId) throws SQLException ;
     
     public String decrypt(String s) throws Exception;

     public List<VegetablesDetails> fetchAllVegetablesByName(String Name) throws Exception ; 
     
    
	    
}
