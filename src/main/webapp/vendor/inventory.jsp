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
            <h2>Inventory DashBoard</h2>
             <br>
      <c:if test="${not empty sucessmessage}">
       <h4 class="text-success" style="color: green; font-weight: bold; animation: fadeOut 1.5s ease-out forwards;">${sucessmessage}</h4>
        <c:remove var="sucessmessage" scope="session"/> 
    </c:if>         
            <!-- In Stock Table -->
            <h3>In Stock</h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Last Updated</th>
                    <th>Action</th>
                </tr>
                
                <% 
                VegetablesServiceImpl vserviceImpl2 = new VegetablesServiceImpl();
                List<VegetablesDetails> list2 = vserviceImpl2.fetchAllVegetablesInStockByVendorId(user.getId());
                
                for(VegetablesDetails veg_item : list2) {       
                %>
                    <tr>
                        <td><%=veg_item.getVegName() %></td>
                        <td><%=veg_item.getQuantity() %></td>
                        <td><%=veg_item.getUpdatedAt() %></td>
                        <td>
                            <button class="button" onclick="openDialog('update', '<%=veg_item.getVegName() %>', <%=veg_item.getQuantity() %>, <%=veg_item.getVegId() %>, <%=veg_item.getVendorId() %>)">Update</button>
                        </td>
                    </tr>
                <% } %>       
            </table>
        <br><br>
            <!-- Out of Stock Table -->
            <h3>Out of Stock</h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Last Updated</th>
                    <th>Action</th>
                </tr>
                <% 
                List<VegetablesDetails> list3 = vserviceImpl2.fetchAllVegetablesOutOfStockByVendorId(user.getId());
                
                for(VegetablesDetails veg_item : list3) {       
                %>
                    <tr>
                        <td><%=veg_item.getVegName() %></td>
                        <td><%=veg_item.getQuantity() %></td>
                        <td><%=veg_item.getUpdatedAt() %></td>
                        <td>
                            <button class="button" onclick="openDialog('refill', '<%=veg_item.getVegName() %>', 0, <%=veg_item.getVegId() %>, <%=veg_item.getVendorId() %>)">Refill</button>
                        </td>
                    </tr>
                <% } %>  
            </table>
             <br>
        </div>
    </div>
</div>

<!-- Dialog Box -->
<div id="dialog" style="display: none;">
    <div style="background-color: white; padding: 20px; border: 1px solid #ccc; width: 300px; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1000;">
        <h3 id="dialogTitle"></h3>
        <form id="stockForm" action="${pageContext.request.contextPath}/VendorsController" method="post">
            <input type="hidden" id="inventory_action" name="inventory_action" value="" required>
            <input type="hidden" id="veg_id" name="veg_id" value="" required>
            <input type="hidden" id="veg_name" name="veg_name" value="" required>
            <input type="hidden" id="vendor_id" name="vendor_id" value="" required>
            <input type="number" id="newStock" name="newStock" placeholder="Stock" required>
            <button type="submit" class="button">Submit</button>
            <button type="button" class="button" onclick="closeDialog()">Cancel</button>
        </form>
    </div>
    <div style="background: rgba(0, 0, 0, 0.5); position: fixed; top: 0; left: 0; right: 0; bottom: 0; z-index: 999;"></div>
</div>

<%@include file="../components/footer.jsp" %>

<%
/*---------CLOSE CONNECTION ----------------------- */
vserviceImpl2.closeConnection();
/*---------CLOSE CONNECTION ----------------------- */
%>
</body>
<script src="${pageContext.request.contextPath}/js/vendor.js"></script>
</html>
