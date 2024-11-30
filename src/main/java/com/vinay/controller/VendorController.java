package com.vinay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.service.VendorService;
import com.vinay.serviceImpl.VendorServiceImpl;

/**
 * Servlet implementation class VendorController
 */
@WebServlet("/VendorsController")
public class VendorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private VendorService vendorService;
	
   @Override
	public void init() throws ServletException {
	   vendorService = new VendorServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String vendor_action_status = request.getParameter("vendor_action_status");
		String inventory_action = request.getParameter("inventory_action");
		HttpSession session = request.getSession(false);
		
		if( vendor_action_status != null)
		{
			String order_id = request.getParameter("order_id");
			int  user_id = Integer.parseInt(request.getParameter("user_id"));
			int  cart_id = Integer.parseInt(request.getParameter("cart_id"));
			
			
			if(vendor_action_status.equals("approved"))
			{
				
				double user_ordered_quantity = Double.parseDouble(request.getParameter("user_ordered_quantity"));
				
				
				int  user_ordered_VEG_ID = Integer.parseInt(request.getParameter("user_ordered_VEG_ID"));
				
				boolean b1 = vendorService.checkandUpdateInventory(user_ordered_quantity, user_ordered_VEG_ID); // 
				if(b1 == true)
				{
				boolean b = vendorService.updateVendorActionStatus(order_id, user_id, cart_id, vendor_action_status);
				  session.setAttribute("sucessmessage", "ORDER  APPROVED !!!");
				}
				else
				{
					 session.setAttribute("failuremessage", "OUT OF STOCK !!!FIRST REFILL !!!");
					
				}
			}
			else if(vendor_action_status.equals("rejected"))
			{
				boolean b = vendorService.updateVendorActionStatus(order_id, user_id, cart_id, vendor_action_status);
				 session.setAttribute("failuremessage", "ORDER REJECTED SUCESSFULLY!!!");
			}
		
			 response.sendRedirect("vendor/myorders.jsp");
		
		}

		else if(inventory_action!= null)
		{
			String veg_name = request.getParameter("veg_name");
			int  veg_id = Integer.parseInt(request.getParameter("veg_id"));
			int  vendor_id = Integer.parseInt(request.getParameter("vendor_id"));
			int  new_quantity = Integer.parseInt(request.getParameter("newStock"));
			
			System.out.println("===================================");
			if(inventory_action.equals("update"))
			{
				
				System.out.println(veg_name );
				System.out.println( veg_id );
				System.out.println(vendor_id );
				System.out.println(new_quantity );
				boolean b = vendorService.updateQuantity(veg_id, veg_name, vendor_id, new_quantity);
				 session.setAttribute("sucessmessage", "Vegetable Stock Updated  Successfully !!!");
				response.sendRedirect("vendor/inventory.jsp");
			}
			else if (inventory_action.equals("refill"))
			{
				
				boolean b = vendorService.updateQuantity(veg_id, veg_name, vendor_id, new_quantity);
				session.setAttribute("sucessmessage", "Vegetable Stock Refilled  Successfully !!!");
				response.sendRedirect("vendor/inventory.jsp");
			}
						
		}
				
		
		
		
		
		
	}

}
