<%@page import="com.vinay.model.OrdersDetails"%>
<%@page import="com.vinay.model.Revenues"%>
<%@page import="com.vinay.model.Carts"%>
<%@page import="com.vinay.serviceImpl.CartsServiceImpl"%>
<%@page import="com.vinay.model.VegetablesDetails"%>
<%@page import="com.vinay.serviceImpl.VegetablesServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.vinay.model.Orders"%>
<%@page import="com.vinay.serviceImpl.OrdersServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order_Confirm</title>

    <script src="${pageContext.request.contextPath}/js/main.js"></script>
      <%@include file="/components/links.jsp" %>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order_confirmation.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

<script type="text/javascript">
  window.history.forward();
  function noBack() {
    window.history.forward();
  }
</script>

</head>
<body onload="noBack();">




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
        <h1 class="text-center mb-4">Order Confirmation</h1>
        <div class="alert alert-success no-print" role="alert">
            Thank you for your order! Your fresh vegetables are on their way.
        </div>
        <div class="order-details">
        <%
        
        
        String order_id = (String) session.getAttribute("order_id");
         OrdersServiceImpl order_Service_impl = new OrdersServiceImpl(); 
         String order_date = order_Service_impl.getOrderDateByOrderId(order_id) ;
         VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();
         CartsServiceImpl cserviceImpl = new CartsServiceImpl();
            		
        List<Orders> orders_list = order_Service_impl.fetchOrdersByIdAndUserId(order_id, user.getId());
    
    
%>
     <h2> Order Id : <%= order_id %> </h2>
           
            <p>Order Date: <%=order_date %> </p>
            <p class="estimated-delivery">Estimated Delivery: Within 8 Hr</p>
        </div>
        <h2>Order Summary</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
            <%
            
            for(Orders order_item : orders_list)
            {
            	 VegetablesDetails veg_detail = vserviceImpl.fetchVegetableDetailsByCartId(order_item.getCartId());
            	 Carts  cart_detail = cserviceImpl.fetchCartDetailsByCartId(order_item.getCartId());
            %>
            
                <tr>
                    <td><%=veg_detail.getVegName() %></td>
                    <td><%=  cart_detail.getQuantity_added() %></td>
                    <td><%= veg_detail.getNet_price() %></td>
                    <td><%= cart_detail.getTotal_Price() %></td>
                </tr>
        <% } %>    
               
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3" class="text-end"><strong>Total:</strong></td>
                    <%
                     Revenues revenue =order_Service_impl.fetchRevenuesByIdAndUserId(order_id, user.getId());
                    double Total_Payment = revenue.getTotalPayment();
                    %>
                    <td><strong><%=revenue.getTotalPayment()%></strong></td>
                </tr>
                
                   <tr>
                    <td colspan="3" class="text-end"><strong>Tax(10%):</strong></td>

                    <td><strong><%=revenue.getTax()%></strong></td>
                </tr>
                
            </tfoot>
        </table>
        <h2>Shipping Information</h2>
        
        <%
        OrdersDetails order_details = order_Service_impl.fetchOrderDetailsByIdAndUserId(order_id, user.getId());
        %>
        <div id="shipping-info">
            <p>Name : <%=order_details.getFullName() %></p>
            <p>Address : <%=order_details.getAddress() %></p>
            <p>City :<%=order_details.getCity() %></p>
            <p>State :<%=order_details.getState() %></p>
            <p>ZIP : <%=order_details.getZip() %></p>
        </div>
        <div class="text-center mt-4 no-print">
        
            <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-success me-2">Return to Shop</a>
          <br>
            <button onclick="window.print()" class="btn btn-primary">Download Bill</button>
        </div>
    </div>

 <%
 
 /*----------DB CONNECTION CLOSE --------------------  */
 cserviceImpl.closeConnection();
 order_Service_impl.closeConnection();
 vserviceImpl.closeConnection();
 /*----------DB CONNECTION CLOSE --------------------  */
 %>
    <br><br>
<%@include file="../components/footer.jsp" %>
</body>
</html>