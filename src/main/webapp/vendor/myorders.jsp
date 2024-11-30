<%@page import="com.vinay.model.VO.VendorOrdersVO"%>
<%@page import="com.vinay.serviceImpl.VendorServiceImpl"%>
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
    <title>Vendor Inventory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vendor.css" type="text/css">   
    <%@include file="/components/links.jsp" %>     
                       <style>
    @keyframes fadeOut {
        0% { opacity: 2; } /* Fully visible */
        100% { opacity: 0; } /* Fully invisible */
    }
</style>  
</head>
<body>

<%@include file="../components/navbar.jsp" %>
<br><br><br><br><br>
<%
    Users user = (Users) session.getAttribute("user");
if ((user != null) &&(user.getUsertype().equals("vendor"))) {
%>
        Welcome, Vendor <%= user.getUsername() %>!<br>
        Vendor Email : <%= user.getEmail() %>
<%
    } else {
        session.setAttribute("failureMessage", "Please Login");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;  // Ensure no further content is sent after redirect
    }
%>
<br>
<div id="container2">
    <div id="content">
        <div id="inventory" class="section active">
            <h2>Orders DashBoard</h2>
            <br>
    <c:if test="${not empty sucessmessage}">
       <h4 class="text-success" style="color: green; font-weight: bold; animation: fadeOut 1.5s ease-out forwards;">${sucessmessage}</h4>
        <c:remove var="sucessmessage" scope="session"/> 
    </c:if>   
    
        <c:if test="${not empty failuremessage}">
       <h4 class="text-danger" style="color: green; font-weight: bold; animation: fadeOut 1.5s ease-out forwards;">${failuremessage}</h4>
        <c:remove var="failuremessage" scope="session"/> 
    </c:if>           
            <!-- In Stock Table -->
            <h3>Pending</h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Order Date</th>
                    <th>Action</th>
                </tr>
                <%
            
                VendorServiceImpl vendorservice_impl = new VendorServiceImpl();
               List<VendorOrdersVO> vendor_order_list = vendorservice_impl.getVendorOrders(user.getId(),"pending");
                
               for(VendorOrdersVO vendor_order :vendor_order_list )
               {
                %>
               
                    <tr>
                        <td><%=vendor_order.getVeg_name() %></td>
                        <td><%= vendor_order.getQuantity_added()%></td>
                        <td> <%= vendor_order.getOrder_date() %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/VendorsController" method="post">
                             <input type="hidden" id="vendor_action_status" name="vendor_action_status" value="approved">
                             <input type="hidden" id="order_id" name="order_id" value="<%=vendor_order.getOrder_id() %>">
                             <input type="hidden" id="user_id" name="user_id" value="<%=vendor_order.getUser_id() %>">
                             <input type="hidden" id="cart_id" name="cart_id" value="<%=vendor_order.getCart_id() %>">
                             <input type="hidden" id="user_ordered_quantity" name="user_ordered_quantity" value="<%=vendor_order.getQuantity_added()%>">
                             <input type="hidden" id="user_ordered_VEG_ID" name="user_ordered_VEG_ID" value="<%=vendor_order.getVeg_id()%>">
                            
                            <input type="submit" value="Approve">
                            </form>
    						  <form action="${pageContext.request.contextPath}/VendorsController" method="post">
                             <input type="hidden" id="vendor_action_status" name="vendor_action_status" value="rejected">
                             <input type="hidden" id="order_id" name="order_id" value="<%=vendor_order.getOrder_id() %>">
                             <input type="hidden" id="user_id" name="user_id" value="<%=vendor_order.getUser_id() %>">
                             <input type="hidden" id="cart_id" name="cart_id" value="<%=vendor_order.getCart_id() %>">
                            <input type="submit" value="Reject">
                            </form>
                             
                        </td>
                    </tr>
     <%} %>          
            </table>
         <br> <br>
            <!-- ApprovedTable -->
        
            <h3>Approved</h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Order Date</th>
                    <th>Status</th>
                </tr>
                <%
            
               List<VendorOrdersVO> vendor_order_list2 = vendorservice_impl.getVendorOrders(user.getId(),"approved");
                
               for(VendorOrdersVO vendor_order :vendor_order_list2 )
               {
                %>
               
                    <tr>
                        <td><%=vendor_order.getVeg_name() %></td>
                        <td><%= vendor_order.getQuantity_added()%></td>
                        <td> <%= vendor_order.getOrder_date() %></td>
                        <td>
                            Approved
                        </td>
                    </tr>
     <%} %>          
            </table>
         <br> <br>
             <!-- Rejected -->
             
        
            <h3>Rejected</h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Order Date</th>
                    <th>Status</th>
                </tr>
                <%
            
             
               List<VendorOrdersVO> vendor_order_list3 = vendorservice_impl.getVendorOrders(user.getId(),"rejected");
                
               for(VendorOrdersVO vendor_order :vendor_order_list3 )
               {
                %>
               
                    <tr>
                        <td><%=vendor_order.getVeg_name() %></td>
                        <td><%= vendor_order.getQuantity_added()%></td>
                        <td> <%= vendor_order.getOrder_date() %></td>
                        <td>
                           Rejected
                        </td>
                    </tr>
     <%} %>          
            </table>
        
            
           <br> <br>
        </div>
    </div>
</div>


<%@include file="../components/footer.jsp" %>
<%
/*---------CLOSE CONNECTION ----------------------- */
vendorservice_impl.closeConnection();
%>
</body>
<script src="${pageContext.request.contextPath}/js/vendor.js"></script>
</html>
