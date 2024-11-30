package com.vinay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.model.Carts;
import com.vinay.model.Users;
import com.vinay.service.CartsService;
import com.vinay.service.CheckoutService;
import com.vinay.service.VegetablesService;
import com.vinay.serviceImpl.CartsServiceImpl;
import com.vinay.serviceImpl.CheckoutServiceImpl;
import com.vinay.serviceImpl.VegetablesServiceImpl;

/**
 * Servlet implementation class Orders
 */
@WebServlet("/Checkout")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	    private CheckoutService check_out_service;
	    
	    
 @Override
	public void init() throws ServletException {
   
     check_out_service = new CheckoutServiceImpl();
     
	};
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("CheckoutIS CALLEEDDDDDDD");
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");

        // Retrieve updated cart data from the form submission
        Enumeration<String> parameterNames = request.getParameterNames();
     //   List<Carts> updatedCart = new ArrayList<Carts>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // Check for cart item data (quantities, veg_ids, etc.)
            if (paramName.startsWith("quantity-")) {
                int cartId = Integer.parseInt(paramName.substring(9));
                double quantity = Double.parseDouble(request.getParameter(paramName));
                int vegId = Integer.parseInt(request.getParameter("veg_id-" + cartId));
                double unit_price_veg = Double.parseDouble(request.getParameter("unit_price-" + cartId));
                double total_price =  unit_price_veg * quantity;
               
                
                // double  total_price = quantity *  
                System.out.println("CART ID => "+ cartId);
                System.out.println("QUANTITY => "+ quantity);
                System.out.println("Vegtable id "+ vegId);
                System.out.println("UNI PRICE "+ unit_price_veg);
                // Update or add cart items 
                Carts cartItem = new Carts();
                cartItem.setVeg_id(vegId);
                cartItem.setQuantity_added(quantity);
                cartItem.setUser_Id(user.getId());
                cartItem.setTotal_Price(total_price);
                
                
                check_out_service .updateCartItem(vegId, user.getId(), cartId, quantity, total_price);

              
             
            }
        }

        response.sendRedirect("checkout.jsp");
	
	}
		
	}


