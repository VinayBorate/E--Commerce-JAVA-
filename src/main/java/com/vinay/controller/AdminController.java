package com.vinay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.model.Users;
import com.vinay.service.AdminService;
import com.vinay.serviceImpl.AdminServiceImpl;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/Admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private AdminService adminService;
       
       public void init() {
       
    	   adminService = new AdminServiceImpl();
       }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String reject = request.getParameter("reject");
		String approve = request.getParameter("approve");
		String reapprove = request.getParameter("reapprove");
		String reactivate = request.getParameter("reactivate");
		String deactivate = request.getParameter("deactivate");
		int  user_id = Integer.parseInt(request.getParameter("user_id"));

	        
	       
	        
	        
		
if(reject!=null)
{
	adminService.rejectService(user_id);
	 response.sendRedirect("admin/home.jsp");
}
else if(approve!=null)
{
	adminService.approvalService(user_id);
	 response.sendRedirect("admin/home.jsp");
}
else if(reapprove!=null)
{
	adminService.reapproveService(user_id);
	 response.sendRedirect("admin/home.jsp");
}
else if(reactivate != null)
{
	adminService.reactivateService(user_id);
	 response.sendRedirect("admin/home.jsp");
}	
else if(deactivate!= null)
{
	adminService.deactivateService(user_id);
	response.sendRedirect("admin/home.jsp");

}
		
		
		
	}

}
