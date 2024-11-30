<%@page import="com.vinay.model.VegetablesDetails"%>
<%@page import="com.vinay.serviceImpl.VegetablesServiceImpl"%>
<%@page import="com.vinay.model.Carts"%>
<%@page import="java.util.List"%>
<%@page import="com.vinay.serviceImpl.CartsServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>Vinay Ecommerce Store</title>
    <%@include file="/components/links.jsp" %>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/cart.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css" type="text/css">
       <style>
    @keyframes fadeOut {
        0% { opacity: 1; } /* Fully visible */
        100% { opacity: 0; } /* Fully invisible */
    }
</style>
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

<c:if test="${not empty cartmessage}">
    <h4 class="text-danger" style="color: green; font-weight: bold; animation: fadeOut 1s ease-out forwards;">
        ${cartmessage}
    </h4>
    <c:remove var="cartmessage" scope="session"/>
</c:if>


<div class="container3 mt-5">
    <h1 class="text-center">Your Vegetable Cart</h1>

    <!-- Form to submit updated cart data -->
    <form id="cart-form" method="POST" onsubmit="return false;"> <!-- Prevent default form submission -->
        <div id="cart-items">
            <%
            
            CartsServiceImpl cartServiceImpl = new CartsServiceImpl();
            VegetablesServiceImpl vegServiceImpl = new VegetablesServiceImpl();
            List<Carts> list = cartServiceImpl.fetchAllCartsByUserID(user.getId());
            double cart_total = 0;
            for (Carts cartItem : list) {
                VegetablesDetails vegItem = vegServiceImpl.fetchVegetableById(cartItem.getVeg_id());
                cart_total += cartItem.getTotal_Price();
            %>
            <div class="card mb-3" data-price="<%=vegItem.getNet_price()%>" id="item-<%=cartItem.getCart_Id()%>">
                <div class="card-body">
                    <div class="row align-items-center">
                        <div class="col-md-6">
                            <h5 class="card-title"><%= vegItem.getVegName() %></h5>
                            <p class="card-text">Unit Price: <%=vegItem.getNet_price()%></p>
                            <p class="card-text">Total Price: $<span id="total_price_item-<%=cartItem.getCart_Id()%>"><%=cartItem.getTotal_Price()%></span></p>
                        </div>
                        <div class="col-md-6">
                            <div class="d-flex align-items-center justify-content-end">
                                <button type="button" class="btn btn-sm btn-secondary me-2" onclick="updateQuantity('item-<%=cartItem.getCart_Id()%>', -1)">-</button>
                                <input type="number" class="form-control quantity-input" name="quantity-<%=cartItem.getCart_Id()%>" id="quantity-item-<%=cartItem.getCart_Id()%>" value="<%=cartItem.getQuantity_added()%>" readonly>
                                <button type="button" class="btn btn-sm btn-secondary ms-2" onclick="updateQuantity('item-<%=cartItem.getCart_Id()%>', 1)">+</button>
                                 <button type="button" class="btn btn-danger btn-custom" onclick="submitForm('${pageContext.request.contextPath}/CartsController')">Remove Item </button>
            
                                <!-- Hidden fields to store vegetable data -->
                                <input type="hidden" name="veg_id-<%=cartItem.getCart_Id()%>" value="<%=vegItem.getVegId()%>">
                                <input type="hidden" name="price-<%=cartItem.getCart_Id()%>" value="<%=vegItem.getNet_price()%>">
                                <input type="hidden" name="unit_price-<%=cartItem.getCart_Id()%>" value="<%=vegItem.getNet_price()%>">
                               <!-- Hidden fields to delete item from cart   -->
                            <input type="hidden" name="action" value="delete">
                              <input type="hidden" name="veg_id_Delete" value="<%=cartItem.getVeg_id()%>">
                             <input type="hidden" name="user_id_Delete" value="<%=user_1.getId() %>">
                            
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </div>

        <!-- Total price section -->
        <div class="mt-4 text-end">
            <h4>Total: $<span id="total-price"><%=cart_total %></span></h4>
        </div>

        <!-- Buttons section -->
        <div class="mt-3 d-flex justify-content-end">
            <% if(list.size() == 0) { %>
                <h3 class="text-danger">Add At Least 1 item</h3>
            <% } else { %>
                <button type="button" class="btn btn-success btn-custom me-2" onclick="submitForm('${pageContext.request.contextPath}/Checkout')">Continue Shopping</button>
                <% } %>
        </div>
    </form>

    <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-primary btn-custom">Add More Items</a>
</div>

<br><br>
<%@include file="../components/footer.jsp" %>
<%@include file="../components/jslibraries.jsp" %>

<%
/*----------DB CONNECTION CLOSE --------------------  */
vegServiceImpl.closeConnection();
cartServiceImpl.closeConnection();
/*----------DB CONNECTION CLOSE --------------------  */
%>
<script>
function submitForm(action) {
    const form = document.getElementById('cart-form');
    form.action = action; // Set the action of the form
    form.submit(); // Submit the form
}
</script>
</body>
</html>
