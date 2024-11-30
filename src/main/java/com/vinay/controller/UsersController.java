package com.vinay.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.model.Users;
import com.vinay.service.UsersService;
import com.vinay.serviceImpl.UsersServiceImpl;

@WebServlet("/users")
public class UsersController extends HttpServlet {
    private UsersService userService;

    @Override
    public void init() {
        userService = new UsersServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        

        if ("register".equals(action)) {
        
            String email = request.getParameter("email");
            String usertype = request.getParameter("usertype");          
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
            String state = request.getParameter("state");
            String city = request.getParameter("city");
            int pincode = Integer.parseInt(request.getParameter("pincode"));
            
            Users user = new Users();
            
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setUsertype(usertype);
            user.setMobileno(mobile);
           user.setAddress(address);
           user.setState(state);
           user.setCity(city);
           user.setPincode(pincode);           
            user.setIsapproved("notapproved"); // first time needed 
            user.setIsactive("active"); // later admin can disable user 

            HttpSession session = request.getSession();

            try {
                userService.registerUser(user);
                session.setAttribute("sucessmessage", "Registration successful! Wait for Admin Approval ");
                response.sendRedirect("login.jsp");
            } catch (Exception e) {
                session.setAttribute("error", e.getMessage());
                session.setAttribute("username", username);
                session.setAttribute("email", email);
                session.setAttribute("usertype", usertype);
                session.setAttribute("mobile",mobile );
                session.setAttribute("address",address );
                session.setAttribute("state",state );
                session.setAttribute("city",city );
                session.setAttribute("pincode",pincode);
                
                response.sendRedirect("register.jsp");
            }
        } else if ("login".equals(action)) {
            // Login logic

        	
        
            try {
            	
                Users user = userService.loginUser(username, password);

                
                
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if (user.getUsertype().equals("admin")) {
                    // Admin user
                    response.sendRedirect("admin/home.jsp");
                }
                
                else if (user.getUsertype().equals("vendor")) {
                	
                	if(user.getIsapproved().equals("approved") && user.getIsactive().equals("active"))
                    response.sendRedirect("vendor/home.jsp");
                	else if(user.getIsapproved().equals("notapproved"))
                	{
                		 session.setAttribute("failureMessage", "Wait For Admin Approval !!");
                		response.sendRedirect("login.jsp");
                	}
                	else if(user.getIsapproved().equals("approved") &&  user.getIsactive().equals("inactive") ) {
               		 session.setAttribute("failureMessage", "Account Suspended By Admin !!");
                		response.sendRedirect("login.jsp");
               	}
               	else if(user.getIsapproved().equals("rejected") ) {
              		 session.setAttribute("failureMessage", "Account Request Rejected By Admin !!");
               		response.sendRedirect("login.jsp");
              	}
                } else if(user.getUsertype().equals("customer")){
                    // Normal user (Customer)
                	
                	System.out.println("CUSTOMER LOGIN------");
                	if(user.getIsapproved().equals("approved") && user.getIsactive().equals("active"))
                	{
                		 session.setAttribute("sucessmessage","Welcome User !");
                        response.sendRedirect("home.jsp");
                	}
                    	else if(user.getIsapproved().equals("notapproved") &&  user.getUsertype() !="admin" )
                    	{
                    		  session.setAttribute("failureMessage", "Wait For Admin Approval !!");
                    		response.sendRedirect("login.jsp");
                    	}
                    	else if(user.getIsapproved().equals("approved") &&  user.getIsactive().equals("inactive") ) {
                    		 session.setAttribute("failureMessage", "Account Suspended By Admin !!");
                     		response.sendRedirect("login.jsp");
                    	}
                    	else if(user.getIsapproved().equals("rejected") ) {
                   		 session.setAttribute("failureMessage", "Account Request Rejected By Admin !!");
                    		response.sendRedirect("login.jsp");
                   	}
           
                }
            } catch (Exception e) {
            	 HttpSession session = request.getSession();
               session.setAttribute("failureMessage", e.getMessage());
               response.sendRedirect("login.jsp");
            }
        }
    }
}
