package com.vinay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.model.VegetablesDetails;
import com.vinay.service.CartsService;
import com.vinay.service.VegetablesService;
import com.vinay.serviceImpl.CartsServiceImpl;
import com.vinay.serviceImpl.VegetablesServiceImpl;

@WebServlet("/CartsController")
public class CartsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VegetablesService veg_service;
    private CartsService carts_service;
    public void init() {
        veg_service = new VegetablesServiceImpl();
        carts_service = new CartsServiceImpl();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
		
		 
		 String action = request.getParameter("action");
		 HttpSession session = request.getSession(false);
		 
		 if(action!=null)
		 {
		 if(action.equals("delete"))
		 {
			 int veg_id_Delete =  Integer.parseInt(request.getParameter("veg_id_Delete"));
			 int user_id_Delete = Integer.parseInt(request.getParameter("user_id_Delete")); 
			 
			 // we will delete the pending item from cart 
			 
			 carts_service.deletePendingCart(veg_id_Delete, user_id_Delete);
			 session.setAttribute("cartmessage", "ITEM SUCESSFULLY REMOVED CART !!!");
			 System.out.println("VEG ID " + veg_id_Delete );
			 System.out.println("user_id_Delete " +user_id_Delete );
			 
			 response.sendRedirect(request.getContextPath() + "/cart.jsp");
			 
			 
		 }
		 }
		 else
		 {
			 int veg_id =  Integer.parseInt(request.getParameter("vegetable_id"));
			 int user_id = Integer.parseInt(request.getParameter("user_id")); 
			 double quantity_added = Double.parseDouble(request.getParameter("quantity_added"));
			
		System.out.println("veg ID : "+veg_id );
		System.out.println("user id " + user_id);
		System.out.println("quantity_added " + quantity_added);
	
			VegetablesDetails veg_details = veg_service.fetchVegetableById(veg_id);
			System.out.println("Price======= " + veg_details.getNet_price());
			 double total_price  = veg_details.getNet_price() * quantity_added;
			System.out.println("Total cost "+ total_price) ;
	
				carts_service.saveOrUpdateCart(veg_id, user_id, quantity_added, total_price);
				 session.setAttribute("cartmessage", "ITEM SUCESSFULLY ADDED TO CART !!!");
				response.sendRedirect(request.getContextPath() + "/home.jsp");
				
		 }	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
