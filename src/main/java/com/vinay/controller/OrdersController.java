package com.vinay.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.model.Orders;
import com.vinay.service.CartsService;
import com.vinay.service.OrdersService;
import com.vinay.serviceImpl.CartsServiceImpl;
import com.vinay.serviceImpl.OrdersServiceImpl;

@WebServlet("/Orders")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    private OrdersService order_service;
	    
	    
 @Override
	public void init() throws ServletException {
	 order_service = new OrdersServiceImpl();
	};
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        String fullName = request.getParameter("fullName");
		        String address = request.getParameter("address");
		        String city = request.getParameter("city");
		        String state = request.getParameter("state");
		        int zip = Integer.parseInt(request.getParameter("zip"));
		        String cardName = request.getParameter("cardName");
		        String cardNumber = request.getParameter("cardNumber");
		        String expDate = request.getParameter("expDate");
		        int cvv = Integer.parseInt(request.getParameter("cvv"));
		       double  total_order_cost = Double.parseDouble(request.getParameter("total"));
		        // Process the order (e.g., save to database, send confirmation, etc.)
                int user_id = Integer.parseInt(request.getParameter("user_id"));
               
                String all_carts_ids= request.getParameter("all_carts_ids");
                // Redirect or forward to a confirmation page
		     //   response.sendRedirect("confirmation.jsp");
	
System.out.println("---------------");

System.out.println(fullName);
System.out.println(address);
System.out.println(city);
System.out.println(state);
System.out.println(zip);
System.out.println(cardName);
System.out.println(cardNumber);
System.out.println(expDate);
System.out.println(cvv);
System.out.println(total_order_cost);
System.out.println("USER ID :::"+user_id);

String[] carts_ids = all_carts_ids.split(",");

boolean b=false;
HttpSession session = request.getSession(false);
for(int i=0; i<carts_ids.length; i++)
{
System.out.println("carts_ids :::"+carts_ids[i]);
//check if payment is already done for any carts_id (we need this check as user goes back from order confirmation page to checkout page so we donot genrate order id again )
String cart_id = carts_ids[i];
int cart_id_int = Integer.parseInt(cart_id);
 b = order_service.checkIfOrderPaymentAlredyDoneByUserForCart(cart_id_int);
if(b==true)
	break;
}

if(b)
{
	System.out.println("Payment already done, REdircet to Myorders.jsp ");
	 session.setAttribute("Payment is already Done For The order", "ordermessage" );
	   response.sendRedirect("myorders.jsp");
}
else
{
	// lets placeorder
String order_id = order_service.placeOrder(fullName, address , city ,state ,zip ,cardName ,cardNumber, expDate , cvv , total_order_cost, carts_ids, user_id  );
							


        // we dont want new session , continue old session  
        session.setAttribute("order_id", order_id );
       response.sendRedirect("order_confirmation.jsp");





}

	}
		
	}


