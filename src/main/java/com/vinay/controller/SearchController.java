package com.vinay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.model.VegetablesDetails;
import com.vinay.service.VegetablesService;
import com.vinay.serviceImpl.VegetablesServiceImpl;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/Search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VegetablesService vegetablesService;
	
	public void init() throws ServletException {
		   
		vegetablesService = new VegetablesServiceImpl();
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String veg_name = request.getParameter("veg_name");
		String action = request.getParameter("action");
		
		 try {
			List<VegetablesDetails> list = vegetablesService.fetchAllVegetablesByName(veg_name);
			 HttpSession session = request.getSession(false);
			 if(list.size()>0)
			 {
				 session.setAttribute("vegetable_list", list);
				 session.setAttribute("action", action);
				 session.setAttribute("sucessmessage", "Vegetable Found");
				 response.sendRedirect(request.getContextPath() + "/search.jsp");
			 }
			 else
			 {
			 
				 session.setAttribute("errormessage", "No Vegetable Found");
				 response.sendRedirect(request.getContextPath() + "/search.jsp");
				 
				 
			 }
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
		
		
	}

}
