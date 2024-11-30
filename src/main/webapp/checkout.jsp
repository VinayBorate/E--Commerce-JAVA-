
<%@page import="com.vinay.model.VegetablesDetails"%>
<%@page import="com.vinay.serviceImpl.VegetablesServiceImpl"%>
<%@page import="com.vinay.model.Carts"%>
<%@page import="java.util.List"%>
<%@page import="com.vinay.serviceImpl.CartsServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Vinay Ecommerce Store</title>
    <%@include file="/components/links.jsp" %>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css" type="text/css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
 
</head>


<body>
<%@include file="../components/navbar.jsp" %>
<br><br><br><br>
<%
    Users user = (Users) session.getAttribute("user");
if ((user != null) &&(user.getUsertype().equals("customer"))) {
%>
        Welcome, User <%= user.getUsername() %>!<br>
        Email : <%= user.getEmail() %>
<%
    } else {
        session.setAttribute("failureMessage", "Please Login");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;  // Ensure no further content is sent after redirect
    }
%>

<div class="container3">
        <h1 class="text-center mb-4">Checkout</h1>
        <form id="checkout-form" action="${pageContext.request.contextPath}/Orders" method="POST">
            <div class="row">
                <div class="col-md-8">
                    <h2 class="mb-3">Shipping Information</h2>
                    <div class="mb-3">
                        <label for="fullName" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="fullName" name="fullName" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" required>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="city" class="form-label">City</label>
                            <input type="text" class="form-control" id="city" name="city" required>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="state" class="form-label">State</label>
                            <input type="text" class="form-control" id="state" name="state" required>
                        </div>
                        <div class="col-md-2 mb-3">
                            <label for="zip" class="form-label">ZIP</label>
                            <input type="text" class="form-control" id="zip" name="zip" required>
                        </div>
                    </div>

                    <h2 class="mb-3 mt-4">Payment Information</h2>
                    <div class="mb-3">
                        <label for="cardName" class="form-label">Name on Card</label>
                        <input type="text" class="form-control" id="cardName" name="cardName" required>
                    </div>
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" required>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="expDate" class="form-label">Expiration Date</label>
                            <input type="text" class="form-control" id="expDate" name="expDate" placeholder="MM/YY" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cvv" class="form-label">CVV</label>
                            <input type="text" class="form-control" id="cvv" name="cvv" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="order-summary">
                        <h2 class="mb-3">Order Summary</h2>
                        <div id="order-items">
                        
                         <% 
                       
             VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();
      
            CartsServiceImpl cserviceImpl = new CartsServiceImpl();
                             		
        List<Carts> list = cserviceImpl.fetchAllCartsByUserID(user.getId());
        String carts_ids[] = new String[list.size()]; // we will send this cart ids to order page 
        int i=0;
        for(Carts cart_item : list)
        {      
        	carts_ids[i] = ""+(cart_item.getCart_Id());
        	
        	i++;
        	  VegetablesDetails veg_details= vserviceImpl.fetchVegetableById(cart_item.getVeg_id()) ;
        %>
                      
                            <div class="d-flex justify-content-between mb-2">
                                <span><%=veg_details.getVegName() %> (<%=cart_item.getQuantity_added() %>)</span>
                                <span>$<span class="item-price"><%=cart_item.getTotal_Price() %></span></span>
                            </div>
                            
                    <%} %>        
         <%
         String all_carts_ids = String.join(",",carts_ids);
         
         %>                   
                            
                        </div>
                        <hr>
                        <div class="d-flex justify-content-between">
                            <strong>Total:</strong>
                            <strong id="total-price">$<span id="total"></span></strong>
                           
                        </div>
                    </div>
                </div>
            </div>
          <input type="hidden" id="totalInput" name="total">
            <input type="hidden" id="user_id" name="user_id" value="<%=user.getId() %>">
             <input type="hidden" id="all_carts_ids" name="all_carts_ids" value="<%=all_carts_ids%>">
           
           <%
           if(list.size()>0){
           %>
            <button type="submit" class="btn btn-primary btn-lg mt-4">Place Order</button>
       <%} else { %>
        <span class="badge bg-warning"> Payment Already Done  !</span>
       
       <%} %>
        </form>
    </div>

<br><br>
<%@include file="../components/footer.jsp" %>
<%@include file="../components/jslibraries.jsp" %>
   
   <%
/*----------DB CONNECTION CLOSE --------------------  */
vserviceImpl.closeConnection();
cserviceImpl.closeConnection();
/*----------DB CONNECTION CLOSE --------------------  */
%>
</body>
 <script src="${pageContext.request.contextPath}/js/checkout.js"></script>
</html>