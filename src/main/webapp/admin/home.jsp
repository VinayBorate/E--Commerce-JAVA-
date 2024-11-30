<%@page import="com.vinay.model.Revenues"%>
<%@page import="com.vinay.util.RSAUtil_DECRYPTION"%>
<%@page import="com.vinay.serviceImpl.AdminServiceImpl"%>
<%@page import="com.vinay.serviceImpl.VegetablesServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.vinay.model.VegetablesDetails"%>
<%@page import="com.vinay.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL  -->
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@page isELIgnored="false"%>
 <!-- JSTL  -->
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Vinay Ecommerce Store</title>
         <%@include file="/components/links.jsp" %>
<script src="${pageContext.request.contextPath}/js/main.js" defer></script>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
     
     
     
     
       <%@include file="/components/links.jsp" %>
        
       
     
     
    </head>
    <body>

<%@include file="../components/navbar.jsp" %>
<br><br><br><br><br>
    <%
    Users user = (Users) session.getAttribute("user");
    if ((user != null) &&(user.getUsertype().equals("admin"))) {
%>
        Welcome, ADMIN	 <%= user.getUsername() %>!<br>
         ADMIN Email : <%= user.getEmail() %>
<%
    }else {
    	session.setAttribute("failureMessage", "Please Login");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;  // 
    }
%>
<br>


 <div id="container3">
        <div id="sidebar">
            <h2>Admin Dashboard</h2>
            <div class="menu-item" style="display:none;" onclick="showSection('profile')" >Profile</div>
            <div class="menu-item" onclick="showSection('approveRequests')">Approve Users Requests</div>
            <div class="menu-item" onclick="showSection('sales')">Revenue Generated</div>
            
            <div class="menu-item" onclick="showSection('feedback')">User Feedback</div>
        </div>
        <div id="content">
            <div id="profile" class="section" style="display:none;" >
                <h2>Profile</h2>

            </div>

            <div id="sales" class="section">
                <h2>Revenue Generated</h2>
                <table>
                    <tr>
                        <th>Order ID</th>
                        <th>Total Order Cost </th>
                        <th>Tax(Shop Revenue)</th>
                    </tr>
                    <%
                    AdminServiceImpl aserviceImpl = new AdminServiceImpl();
                  List<Revenues> revenue_list =   aserviceImpl.fetchAllRevenues();
                  
                  for(Revenues revenue : revenue_list)
                  {
                    %>
                    
                    <tr>
                        <td><%= revenue.getOrderId() %></td>
                        <td><%= revenue.getTotalPayment() %></td>
                        <td><%= revenue.getTax() %></td>
                    </tr>

<%} %>
                </table>
            </div>

            <div id="approveRequests"  class="section active">

                <br>
                <%
               
               
                List<Users> user_list = aserviceImpl.fetchAllNotApprovedUsers();
                if(user_list.size()!=0)
                {
                	 
                %>
                <h3>Pending Approvals</h3>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    
                  <% 
                    
                    
                    for(Users users_item : user_list)
                    { 
                    	
                    	String username = aserviceImpl.decrypt(users_item.getUsername());
                    	String email = aserviceImpl.decrypt(users_item.getEmail());
                    %>
                    
                    
                    <tr>
                        <td><%=username%></td>
                        <td><%=email%> </td>
                        <td>
                        
                        <form action="${pageContext.request.contextPath}/Admin" style="  display: contents;" method="post">
                       <input type="hidden" id="approve" name="approve" value="approve">
                        <input type="hidden" id="user_id" name="user_id" value="<%=users_item.getId()%>">
                       <input type="submit" name="Approve" value="Approve" style=" background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form> 
            
                            
                 <form action="${pageContext.request.contextPath}/Admin" style="
    display: contents;" method="post">
                       <input type="hidden" id="reject" name="reject" value="reject">
                    <input type="hidden" id="user_id" name="user_id" value="<%=users_item.getId()%>">
                       <input type="submit" name="Reject" value="Reject" style=" background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form>  
                        </td>
                    </tr>
        <%}%>
               <% }%> 
            
                   
                </table>

 <%

 List<Users> user_list2= aserviceImpl.fetchAllApprovedUsers();
 if(user_list2.size()!=0)
 {
 %>

                <h3>Approved Users</h3>
                <table>
                
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    
                    <%
                  
                    for(Users users_item : user_list2)
                    { 
                    	String username = aserviceImpl.decrypt(users_item.getUsername());
                    	String email = aserviceImpl.decrypt(users_item.getEmail());
                    %>
                    
                    <tr>
                         <td><%=username%></td>
                        <td><%=email%> </td>
                        <%
                        if(users_item.getIsapproved().equals("approved") && users_item.getIsactive().equals("active"))
                        {
                        %>
                       <td > <div class="badge bg-success" > <%=users_item.getIsapproved() %> </div> & <div class="badge bg-success"> <%=users_item.getIsactive() %></div> </td> 
                        
                        <%} %>
                        
                                                <%
                        if(users_item.getIsapproved().equals("approved") && users_item.getIsactive().equals("inactive"))
                        {
                        %>
                       <td > <div class="badge bg-success" > <%=users_item.getIsapproved() %> </div> & <div class="badge bg-danger"> <%=users_item.getIsactive() %></div> </td> 
                        
                        <%} %>
                        
                        <td>
                        
                        <form action="${pageContext.request.contextPath}/Admin" style="
    display: contents;" method="post">
                       <input type="hidden" id="deactivate" name="deactivate" value="deactivate">
                        <input type="hidden" id="user_id" name="user_id" value="<%=users_item.getId()%>">
                       <input type="submit" name="Delete User" value="DeleteUser" style=" background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form>  
                        
                         </td>
                    </tr>
                   
                           <%}

%>    

   <% }%> 
   
                </table>

 <%

 List<Users> user_list3= aserviceImpl.fetchAllRejectedUsers();
 if(user_list3.size()!=0)
 {
 %>
                <h3>Rejected Users</h3>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                 
                  <%
                    
                    for(Users users_item : user_list3)
                    { 
                  
                    	String username = aserviceImpl.decrypt(users_item.getUsername());
                    	String email = aserviceImpl.decrypt(users_item.getEmail());
                    %>
                    
                    <tr>
                           <td><%=username%></td>
                        <td><%=email%> </td>
                        <td><%=users_item.getIsapproved() %></td>
                        <td>
                        
                         <form action="${pageContext.request.contextPath}/Admin" style="
    display: contents;" method="post" >
                       <input type="hidden" id="reapprove" name="reapprove" value="reapprove">
                        <input type="hidden" id="user_id" name="user_id" value="<%=users_item.getId()%>">
                       <input type="submit" name="reapprove" value="Reapprove" style=" background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form>  
                        </td>
                    </tr>
        <%}

%>    


   <% }%> 
                </table>
          
          
          <%
          List<Users> user_list4= aserviceImpl.fetchAllDeletedUsers();
          if(user_list4.size()!=0){
          %>      
                
                <h3>Deleted  Users</h3>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                     
                  <%
                    
                    for(Users users_item : user_list4)
                    { 
                    	String username = aserviceImpl.decrypt(users_item.getUsername());
                    	String email = aserviceImpl.decrypt(users_item.getEmail());
                    %>
                    
                    <tr>
                          <td><%=username%></td>
                        <td><%=email%> </td>
                       <td class="badge bg-danger" ><%=users_item.getIsactive() %></td>
                        <td>
                        
                                                 <form action="${pageContext.request.contextPath}/Admin" style="
    display: contents;" method="post">
                       <input type="hidden" id="reactivate" name="reactivate" value="reactivate">
                        <input type="hidden" id="user_id" name="user_id" value="<%=users_item.getId()%>">
                       <input type="submit" name="reactivate" value="Reactivate" style=" background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form>  
                        
                        </td>
                    </tr>
                       <%}

%>    

   <% }%> 
                </table>
                
                
            </div>

            <div id="feedback" class="section">
                <h2>User Feedback</h2>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>User</th>
                        <th>Vegetable</th>
                        <th>Rating</th>
                        <th>Comment</th>
                    </tr>
                    <tr>
                        <td>2024-09-20</td>
                        <td>John Doe</td>
                        <td>Tomatoes</td>
                        <td>4</td>
                        <td>Great quality!</td>
                    </tr>
                    <tr>
                        <td>2024-09-21</td>
                        <td>Jane Smith</td>
                        <td>Potatoes</td>
                        <td>5</td>
                        <td>Very fresh</td>
                    </tr>
                    <tr>
                        <td>2024-09-22</td>
                        <td>Bob Johnson</td>
                        <td>Carrots</td>
                        <td>3</td>
                        <td>Good, but could be fresher</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>





<%@include file="../components/footer.jsp" %>

<%


/*---------CLOSE CONNECTION ----------------------- */

aserviceImpl.closeConnection();


%>

 </body>
               <script src="${pageContext.request.contextPath}/js/admin.js"></script>

 
</html>