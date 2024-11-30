package com.vinay.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.vinay.model.Users;
import com.vinay.model.VegetablesDetails;
import com.vinay.service.VegetablesService;
import com.vinay.serviceImpl.UsersServiceImpl;
import com.vinay.serviceImpl.VegetablesServiceImpl;

@WebServlet("/Vegetable")
@MultipartConfig
public class VegetablesController extends HttpServlet {
    private VegetablesService veg_service;

    public void init() {
        veg_service = new VegetablesServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {	
        	
        	  String action_type = request.getParameter("action_type");    // Handles vendor Add/Update Vegetables SECTION  FORM Button => "Add Vegetables"  or "Update"   (home.jsp -> Add/Update Vegetables Section)
        	   String action_type2 = request.getParameter("action_type2"); // Handles vendor Add/Update Vegetables SECTION  FORM Button => "Delete" (home.jsp -> Add/Update Vegetables Section)
        	   String action_type3 = request.getParameter("action_type3"); // Handles vendor Inventory  SECTION  FORM Button => "Update" or "Refill" (home.jsp -> Inventory Section) , we just update the stock 
        	   String restore_action = request.getParameter("restore_action");
        	   HttpSession session = request.getSession(false);
    
 /*--------CASE 0) RESTORE THE DELETED VEGETBALE BACK --------------------------------------*/       	   
       if((restore_action != null) && ("restore".equals(restore_action))) 
       {
    	   int veg_id = Integer.parseInt(request.getParameter("vegetable_id"));
    	   veg_service.restoreVegetableById(veg_id);
      	   session.setAttribute("sucessmessage", "Vegetable Restored Successfully");
         	 response.sendRedirect(request.getContextPath() + "/vendor/home.jsp");
       }
        	   
        	   
        	   
 /*----------CASE 1) DELETE SERVICE CALL ---------------------------------- */       	   
       else	if ((action_type2!= null) && ("delete".equals(action_type2))) {
           		 int veg_id = Integer.parseInt(request.getParameter("vegetable_id"));
           		 System.out.print("Vegetbale idDDD is "+veg_id);		 
           		veg_service.deleteVegetableById(veg_id);
           		
           	   session.setAttribute("sucessmessage", "Vegetable Deleted Successfully");
           	 response.sendRedirect(request.getContextPath() + "/vendor/home.jsp");
           		 
           	}
           	          
           	else if (action_type !=null)
           	{
 /*----------CASE 2) ADD  , CASE 3) UPDATE  SERVICE CALL  ---------------------------------- */           	
            String veg_name = request.getParameter("veg_name");
            String description = request.getParameter("description");
            String veg_category = request.getParameter("veg_category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price_per_piece = Double.parseDouble(request.getParameter("price_per_piece"));
            Part part = request.getPart("veg_pic_name");
            String veg_pic_name = part.getSubmittedFileName();         
            double discount_per_piece = Double.parseDouble(request.getParameter("discount_per_piece"));
            double net_price = Double.parseDouble(request.getParameter("net_price"));
           
            Users user = (Users) session.getAttribute("user");
            int vendorId = user.getId();
            VegetablesDetails veg_details = new VegetablesDetails(vendorId, veg_name, quantity, description, price_per_piece, veg_pic_name, veg_category , discount_per_piece, net_price); 
            String path = "F:\\VINAY JAVA PROJECT\\ECLLIPSE VINAY PROJECT\\E--Commerce-JAVA-\\src\\main\\webapp\\";

 
/*--------------- CASE 2) ADD   ------------------ */
            if ("add".equals(action_type)) {
                veg_service.addVegetable(veg_details, part, path);

                session.setAttribute("sucessmessage", "Vegetable Added Successfully !!!");
                response.sendRedirect(request.getContextPath() + "/vendor/home.jsp");
            }
 /*--------------- CASE 3) UPDATE   ------------------ */           
            else if ("update".equals(action_type)) {
            	
            	System.out.println("	UPDATE TRIGGERED ");
            	
            	 // System.out.println(veg_name+"="+ description+""+veg_category+"="+quantity+""+price_per_piece+"="+veg_pic_name+""+action_type+"="+discount_per_piece+"="+net_price);            	
            	  int veg_id =  Integer.parseInt(request.getParameter("veg_id"));
            	  System.out.println("Vegetbale id isSSSSS ===> "+veg_id);
                // Update logic here
            	  VegetablesDetails veg_details2 = new VegetablesDetails(veg_id,vendorId, veg_name, quantity, description, price_per_piece, veg_pic_name, veg_category , discount_per_piece, net_price); 
                  System.out.println("------------"); 
                  System.out.println("------------"); 
                  System.out.println("------------"); 
            	  System.out.println(veg_details2.toString());
            	  
            	  System.out.println("------------"); 
            	  
            	  System.out.println("------------"); 
            	  System.out.println("------------"); 
            	  veg_service.updateVegetable(veg_details2, part, path);
            	   session.setAttribute("sucessmessage", "Vegetable Updated Successfully  !!!");
            	  response.sendRedirect(request.getContextPath() + "/vendor/home.jsp");
            } 
            
            
           	}
           	else if (action_type3!=null) {
           	
           		//Invnetory section refill/update  stock only 
           		
           	}

        } catch (Exception e) {
          //  e.printStackTrace();
            HttpSession session = request.getSession(false);
            session.setAttribute("errormessage", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/vendor/home.jsp");
        }
        
    }
 
    
}