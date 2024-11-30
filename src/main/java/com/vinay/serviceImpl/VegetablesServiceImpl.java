package com.vinay.serviceImpl;


import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import com.vinay.dao.VegetablesDao;
import com.vinay.daoImpl.VegetablesDaoImpl;
import com.vinay.exception.VegetablesException;
import com.vinay.model.VegetablesDetails;
import com.vinay.service.VegetablesService;
import com.vinay.util.RSAUtil_DECRYPTION;

public class VegetablesServiceImpl implements VegetablesService {
    
    private VegetablesDao vegDao;

    public VegetablesServiceImpl() {
        this.vegDao = new VegetablesDaoImpl();
    }

    
    /* -------CLOSE  DB   CONNECTION -----------   */
    @Override
    public void closeConnection() {
    	vegDao.closeConnection();
    	
    }
    
    
    
    

   
    
/* --------------------------------- SAVE VEGETBALE INTO DB  ------------------------------------*/ 
    
    @Override
    public void addVegetable(VegetablesDetails details, Part vegPicPart, String Path) throws Exception {
        
    	//bUSINESS LOGIC -> fIRST Check begetable is there or not ?
    	
    	
    	System.out.println("addVegetable CALLED ");
    	// Check if the vegetable already exists
        Optional<VegetablesDetails> existingVegetable = vegDao.checkVegetableExistsByVendor(details.getVendorId(), details.getVegName());
        if (existingVegetable.isPresent()) {
            throw new VegetablesException("Vegetable already exists.");
        }

        // Save vegetable details
        vegDao.saveVegetable(details);

        // Ensure the img directory exists and handle file upload
        if (vegPicPart != null && vegPicPart.getSize() > 0) {
            String path_final = Path + "\\img";
            
          System.out.println("HEYYYYY PATH ISSSSSS:::::::::::::"+path_final);
            File imgDir = new File(path_final);
            if (!imgDir.exists()) {
                boolean created = imgDir.mkdirs();
                if (created) {
                    System.out.println("Directory created: " + imgDir.getAbsolutePath());
                } else {
                    throw new VegetablesException("Failed to create directory.");
                }
            }

            String fileName = details.getVegPicName();
            vegPicPart.write(path_final + File.separator + fileName);
            System.out.println("Image saved at: " + path_final + File.separator + fileName);
        } else {
            throw new VegetablesException("No file uploaded.");
        }
    }
    
    
    
 /* --------------------------------- FTECH ALL VEGETBALE FROM  DB  ------------------------------------*/ 
    
    
    // we will fetch all vegtables for home page 
    
    public List<VegetablesDetails> fetchAllVegetablesBycategory(String Category) {
    	
    	return vegDao.getAllVegetablesByCategory(Category);
    }
    
    
    
    
    @Override
    public List<VegetablesDetails> fetchAllVegetablesByName(String name) throws Exception {
    	// TODO Auto-generated method stub
    	return vegDao.getAllVegetablesByName(name);
    }
   
/* --------------------------------- FTECH ALL VEGETBALE FROM  DB VIA SPECIFIC VENDOR   ------------------------------------*/   
    
    // we will fetch all veegtbales uploded by partticular vendor 
    @Override
    public List<VegetablesDetails> fetchAllVegetablesByVendorId(int vendor_id) throws Exception {
    	return vegDao.getAllVegetablesByVendorId(vendor_id);
    }
    
    
    
    // we will fetch all deleted veegtbales uploded by partticular vendor 
    @Override
    public List<VegetablesDetails> fetchAllDeletedVegetablesByVendorId(int vendor_id) throws Exception {
    	return vegDao.getAllDeletedVegetablesByVendorId(vendor_id);
    }
    
    
/* --------------------------------- FTECH IN STOCK VEGETBALE FROM  DB VIA SPECIFIC VENDOR   ------------------------------------*/   
    
    @Override
    public List<VegetablesDetails> fetchAllVegetablesInStockByVendorId(int vendor_id) throws Exception {
   
    	
    	
    	return vegDao.getAllInStockVegetablesByVendorId(vendor_id);
    }
    
 /* --------------------------------- FTECH OUT OF  STOCK VEGETBALE FROM  DB VIA SPECIFIC VENDOR   ------------------------------------*/   
    
    @Override
    public List<VegetablesDetails> fetchAllVegetablesOutOfStockByVendorId(int vendor_id) throws Exception {
    	
    	
    	return vegDao.getAllOutOfStockVegetablesByVendorId(vendor_id);
    }
    
    
    /* ----------------------------------------FETCH A VEGETBALES DETAIL BASED ON VEG ID -----------------------------*/
    // WE NEED THIS FUNCTION IN CART MODULE 
    
  @Override
public VegetablesDetails fetchVegetableById(int veg_id) throws Exception {
	// TODO Auto-generated method stub
	  
	  Optional<VegetablesDetails>  veg_details = vegDao.getVegetableById(veg_id);
		  return veg_details.get();
	  
}
    
  
  /*--------------FETCH ALL VENDORSas we need it on home page for sort by option ------------------------*/
    
    public List<String> fetchAllVendorsNames() throws Exception
    {
    	
    	return vegDao.getAllVendorsNames() ;
    	
    }  
  
  
    /*----------------------------FETCH VENDOR NAME BASED ON VEG ID-----------------*/
    
    @Override
    public String getVendorUsernameByVegId(int vegId) throws Exception {
    	// TODO Auto-generated method stub
    	return vegDao.getUsernameByVegId(vegId);
    }

    
    /* ------------- FETCH VEGETBALE DETAILS BY CART ID ---*/
    
    @Override
    public VegetablesDetails fetchVegetableDetailsByCartId(int cartId) throws Exception {
    	// TODO Auto-generated method stub
    	return vegDao.getVegetableDetailsByCartId(cartId);
    }
    
    
    
    public List<VegetablesDetails> getVegetablesDetailsByOrderId(String orderId) throws Exception
    {
       return vegDao.getVegetablesDetailsByOrderId(orderId);	
    }
    
    
    

/* ---------------------------UPDATE VEGETBALE INTO DB -----------------*/


public void updateVegetable(VegetablesDetails details, Part vegPicPart, String path) throws Exception {
    // Check if the vegetable exists for the vendor
    System.out.println("updateVegetableedd CALLED");
    
    // Check if the vegetable with the same name exists for the vendor
    Optional<VegetablesDetails> existingVegetable = vegDao.checkVegetableExistsByVendor(details.getVendorId(), details.getVegName());
    
//    if (existingVegetable.isPresent() && existingVegetable.get().getVegId() != details.getVegId()) {
//        throw new VegetablesException("Same vegetable already exists.");
//    }

    // Update vegetable details
    vegDao.updateVegetable(details);

    // Ensure the img directory exists and handle file upload
    if (vegPicPart != null && vegPicPart.getSize() > 0) {
        String pathFinal = path + "\\img";
        
        System.out.println("HEYYYYY PATH ISSSSSS:::::::::::::" + pathFinal);
        File imgDir = new File(pathFinal);
        if (!imgDir.exists()) {
            boolean created = imgDir.mkdirs();
            if (created) {
                System.out.println("Directory created: " + imgDir.getAbsolutePath());
            } else {
                throw new VegetablesException("Failed to create directory.");
            }
        }

        String fileName = details.getVegPicName();
        vegPicPart.write(pathFinal + File.separator + fileName);
        System.out.println("Image saved at: " + pathFinal + File.separator + fileName);
    }
}



    
    
    
    /*------------------Delete vegetbale --------------------------------- */
    
    
    
    @Override
    public boolean deleteVegetableById(int vegId) throws Exception {
    	// TODO Auto-generated method stub
    	return vegDao.deleteVegetableById(vegId);
    }
   
    @Override
    public boolean restoreVegetableById(int vegId) throws Exception {
    	// TODO Auto-generated method stub
    	return vegDao.restoreVegetableById(vegId);
    }
    
    
    public VegetablesDetails get_A_VegetableById(int vegId) throws SQLException 
    {
    	
    	return vegDao.get_A_VegetableById(vegId);
    	
    }
    
    
    @Override
	public String decrypt(String s) throws Exception {
	
	RSAUtil_DECRYPTION obj = new RSAUtil_DECRYPTION();
	return (obj.decrypt(s));
	
	}
    
    
}




