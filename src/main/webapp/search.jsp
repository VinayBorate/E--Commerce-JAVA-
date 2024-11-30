<%@page import="com.vinay.serviceImpl.VegetablesServiceImpl"%>
<%@page import="com.vinay.model.VegetablesDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.vinay.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Vinay Ecommerce Store</title>
         <%@include file="components/links.jsp" %>
        <%@include file="components/jslibraries.jsp" %>
        <link href="css/style.css" rel="stylesheet">
         <script src="${pageContext.request.contextPath}/js/main.js" defer></script>
    </head>
    <body>

<%@include file="components/navbar.jsp" %>



  <!-- Content Section Start -->
    <div class="container mt-5 pt-5 content">  
       <br>
       <br>
 <c:if test="${not empty sucessmessage}">
       <h4 class="text-success" style="color: green; font-weight: bold; animation: fadeOut 1s ease-out forwards;">${sucessmessage}</h4>
        <c:remove var="sucessmessage" scope="session"/> 
    </c:if>

    <c:if test="${not empty errormessage}">
        <p class="text-danger">${errormessage}</p>
        <c:remove var="errormessage" scope="session"/> 
    </c:if>
    
    
    
    
      <div class="container-fluid bg-light py-3 mt-5" style="margin-top: 3rem !important;">
     
          <form class="d-flex" role="search" action="${pageContext.request.contextPath}/Search" method="post">
            <input
              class="form-control me-2 "
              type="search"
              placeholder="Search for products..."
              aria-label="Search"
              name="veg_name"
            />
            <input type="hidden"  name="action" value="search" />
            <button class="btn btn-success search-hover" type="submit">Search</button>
          </form>
       
      </div>
       
    </div>
    
    
    <%
               
    List<VegetablesDetails> list = (List<VegetablesDetails>)session.getAttribute("vegetable_list");
    VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();  
    Users user = (Users) session.getAttribute("user");
    if(session.getAttribute("action")!=null)
    for(VegetablesDetails veg_item : list)
    {       
    	String vendor_name = vserviceImpl.getVendorUsernameByVegId(veg_item.getVegId());
    	vendor_name = vserviceImpl.decrypt(vendor_name);
    %>  
      <div class="product-card" data-vendor="<%=vendor_name %>" data-price="<%=veg_item.getNet_price()%>">
        <span class="discount-tag"><%=veg_item.getDiscount_per_piece()%>Rs OFF</span>
        <img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" style="width:50px ; height:50px;">
        <h3><%=veg_item.getVegName()%></h3>
         <p class=""><%=veg_item.getDescription()%></p>
        <p class="price">
          <strong><%=veg_item.getNet_price()%></strong> <span class="old-price"><%=veg_item.getPricePerPiece() %></span>
        </p>
<%   if (user != null) {
%>
             <form style="display: contents;" action="${pageContext.request.contextPath}/CartsController" method="post">
<input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
<input type="hidden" id="user_id" name="user_id" value="<%=user.getId()%>"> 
           <select name="quantity_added">
          <option value="1">1kg</option>
          <option value="0.5">0.5kg</option>
          <option value="2">2kg</option>
            <option value="5">5kg</option>
        </select>
<% if(veg_item.getQuantity()<=0) { %>
<span class="badge bg-warning"> Out Of Stock !</span>
<%}else{ %>
<input type="submit"  name="add_to_cart" value="Add to Cart" style="max-width: 100%; background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
<%} %>
</form> 
<%
}

else { 
%>    	
	
	<select name="quantity_added">
          <option value="1">1kg</option>
          <option value="0.5">0.5kg</option>
          <option value="2">2kg</option>
            <option value="5">5kg</option>
        </select>
        
        <% if(veg_item.getQuantity()<=0) { %>
<span class="badge bg-warning"> Out Of Stock !</span>
<%}else{ %>
        <button type="button" onclick="myFunction()" > Add to Cart </button>
        <%} %>
<%     	
}
%>           

      </div>

  <%}

%>
  
   <c:remove var="vegetable_list" scope="session"/> 
    <c:remove var="action" scope="session"/> 
        
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>


<%
/*----------DB CONNECTION CLOSE --------------------  */
vserviceImpl.closeConnection();
/*----------DB CONNECTION CLOSE --------------------  */
%>
 </body>

</html>