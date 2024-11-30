<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.vinay.model.Carts"%>
<%@page import="com.vinay.serviceImpl.CartsServiceImpl"%>
<%@page import="com.vinay.model.VegetablesDetails"%>
<%@page import="com.vinay.serviceImpl.VegetablesServiceImpl"%>
<%@page import="com.vinay.model.Revenues"%>
<%@page import="com.vinay.model.Orders"%>
<%@page import="java.util.List"%>
<%@page import="com.vinay.serviceImpl.OrdersServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Orders</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myorders.css" type="text/css">   
       <%@include file="/components/links.jsp" %>     
    </head>
<body>

<%@include file="../components/navbar.jsp" %>
<br><br><br>

<%
    Users user = (Users) session.getAttribute("user");
if ((user != null) &&(user.getUsertype().equals("customer"))) {
%>

<%
    } else {
        session.setAttribute("failureMessage", "Please Login");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;  // Ensure no further content is sent after redirect
    }
%>

    <div class="container3">
        <h1 class="text-center">My Orders</h1>
        <div id="orders-container3">
    
     
          
<%
        
       

OrdersServiceImpl order_Service_impl = new OrdersServiceImpl();
List<Orders>  order_list = order_Service_impl.fetchAllOrdersByUserId(user.getId());
if(order_list.size()>0)
{
	

HashSet<String> order_ids = new HashSet<String>();

for(Orders order_item : order_list)
{
    order_ids.add(order_item.getOrderId());
}

ArrayList<String> order_ids_list= new ArrayList<String>(order_ids);
%>

<%
            //for(Orders order_item : order_list)
            for(String order_id : order_ids_list)
            {
                  Revenues revenue =order_Service_impl.fetchRevenuesByIdAndUserId(order_id, user.getId());
                  String order_date = order_Service_impl.getOrderDateByOrderId(order_id) ;
                %>              

    <c:if test="${not empty ordermessage}">
    <h4 class="text-danger" style="color: green; font-weight: bold; animation: fadeOut 1s ease-out forwards;">
        ${ordermessage}
    </h4>
    <c:remove var="ordermessage" scope="session"/>
</c:if> 
            <!-- Order #VEG-123456 -->
            <div class="order-card">
                <div class="order-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Order #VEG-<%=order_id %>></h5>
                    
                </div>
                <div class="order-body">
                    <p><strong>Date:</strong> <%=order_date %></p>
                    <p><strong>Total:</strong> <%=revenue.getTotalPayment() %></p>
                    <p><strong>Tax:</strong> <%=revenue.getTax()%></p>
                </div>
                <div class="order-footer text-end">
                    <button class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#<%=order_id %>">View Details</button>
                </div>



                <!-- Modallllllllll-->

                <div class="modal fade" id="<%=order_id %>" tabindex="-1" aria-labelledby="orderDetailsModalLabel1" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="orderDetailsModalLabel1">Order Details for #VEG-123456</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p><strong>Order Date:</strong> <%= order_date %></p>
                                
                                <p><strong>Total:</strong> <%= revenue.getTotalPayment() %></p>
                                <p><strong>Tax:</strong> <%= revenue.getTax()%></p>
                                <h6>Items:</h6>
                                <ul>
                                <%
                                VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();
                               
                              
                                CartsServiceImpl cserviceImpl = new CartsServiceImpl();
                                List<Carts> carts_items = cserviceImpl.getCartsByOrderId(order_id);
                             
                                for(Carts carts_item : carts_items)
                                {
                                	System.out.println("Veg id====>  "+ carts_item.getVeg_id());
                                }
                               
                                for(Carts carts_item : carts_items)
                                {
                                	 System.out.println("Carts item size "+ carts_item.getVeg_id());
                                     VegetablesDetails veg_list = vserviceImpl.get_A_VegetableById(carts_item.getVeg_id());
                                     Orders order =  order_Service_impl.getOrderDetailsByOrderIdAndCartId(order_id, carts_item.getCart_Id());
                                %>
                                    <li><%=veg_list.getVegName()%> - Quantity: <%=carts_item.getQuantity_added() %> , Price: <%= carts_item.getTotal_Price() %> , 
                                    
                                    <% String s =order.getVendorActionStatus();
                                    if(s.equals("pending"))
                                    {   
                                    %>
                                     <span class="badge bg-warning"><%=order.getVendorActionStatus() %></span>  </li>
                                    <% }
                                    else if(s.equals("approved")) {
                                    %>
                                     <span class="badge bg-success"><%=order.getVendorActionStatus() %></span>  </li>
                                    
                                    <%} else if(s.equals("rejected")){
                                        
                                        %>
                                         <span class="badge bg-danger"><%=order.getVendorActionStatus() %></span>  </li>
                                         
                                        <%} %>
                                    
                                    
                                    
                               <% 
                                }
                                %>
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>



<!-- Modallllllllll-->

<%              
                
            }
%>
          



            </div>





        </div>
        
        <%} else { %>
        <br><br>
       <h2 class="text-danger"> No Orders Found.... </h2>
          
        <%} %>
    </div>



    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<br>
<%@include file="../components/footer.jsp" %>
 <%@include file="../components/jslibraries.jsp" %>

<%
/*----------DB CONNECTION CLOSE --------------------  */

order_Service_impl.closeConnection();

/*----------DB CONNECTION CLOSE --------------------  */
%>


</body>
  <script src="${pageContext.request.contextPath}/js/myorders.js"></script>
  
</html>