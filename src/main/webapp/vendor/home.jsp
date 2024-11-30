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
        <title>Vendor DashBoard</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vendor.css" type="text/css">
        <%@include file="/components/links.jsp" %>   
               <style>
    @keyframes fadeOut {
        0% { opacity: 1; } /* Fully visible */
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
    <div id="sidebar">
        <h2>Vendor Dashboard</h2>
        <div class="menu-item" onclick="showSection('sales')">Sales</div>
        <div class="menu-item" onclick="showSection('addUpdate')">Add/Update Vegetables</div>         
        <div class="menu-item" onclick="showSection('feedback')">User Feedback</div>
    </div>
    <div id="content">
    
    <c:if test="${not empty sucessmessage}">
       <h4 class="text-success" style="color: green; font-weight: bold; animation: fadeOut 1s ease-out forwards;">${sucessmessage}</h4>
        <c:remove var="sucessmessage" scope="session"/> 
    </c:if>

    <c:if test="${not empty errormessage}">
        <p class="text-danger">${errormessage}</p>
        <c:remove var="errormessage" scope="session"/> 
    </c:if>

    <div id="addUpdate" class="section active">
        <h2>Add/Update Vegetables</h2>
 <div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10 col-12">
            <form action="${pageContext.request.contextPath}/Vegetable" method="post" id="vegetableForm" enctype="multipart/form-data">
                <input type="hidden" id="formAction" name="action_type" value="add">
                <input type="hidden" id="veg_id" name="veg_id" value="">

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetableName" class="me-2 w-25 text-start">Vegetable Name:</label>
                    <input type="text" name="veg_name" id="vegetableName" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Vegetable Name" required>
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetableCategory" class="me-2 w-25 text-start">Category:</label>
                    <select id="vegetableCategory" name="veg_category" class="form-control form-control-sm w-75 border-0 py-2" required>
                        <option value="" disabled selected>Select Category</option>
                        <option value="Leafy Green">Leafy Green</option>
                        <option value="Cruciferous">Cruciferous</option>
                        <option value="Root">Root</option>
                        <option value="Seasonal">Seasonal</option>
                    </select>
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetableStock" class="me-2 w-25 text-start">Quantity:</label>
                    <input type="number" id="vegetableStock" name="quantity" class="form-control form-control-sm w-75 border-0 py-2" placeholder="In Kg" required>
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetableDescription" class="me-2 w-25 text-start">Description:</label>
                    <textarea id="vegetableDescription" name="description" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Description"></textarea>
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetablePrice" class="me-2 w-25 text-start">Price:</label>
                    <input type="number" name="price_per_piece" id="vegetablePrice" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Price per Kg" required>
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetableDiscount" class="me-2 w-25 text-start">Discount:</label>
                    <input type="number" name="discount_per_piece" id="vegetableDiscount" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Discount per Kg" required oninput="calculateNetPrice()">
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetableNetPrice" class="me-2 w-25 text-start">Net Price:</label>
                    <input type="number" name="net_price" id="vegetableNetPrice" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Net Price (After Discount)" readonly>
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <label for="vegetableImage" class="me-2 w-25 text-start">Image:</label>
                    <input type="file" name="veg_pic_name" id="vegetableImage" class="form-control form-control-sm w-75 border-0 py-2" required>
                </div>

                <div class="d-flex justify-content-between">
                    <button type="button" class="btn btn-secondary w-45 py-2" onclick="resetForm()">Reset</button>
                    <button type="submit" class="btn btn-primary w-45 py-2" id="submitButton">Add Vegetables</button>
                </div>
            </form>
        </div>
    </div>
</div>
 

        
        <div class="table-responsive">
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Category</th>
                    <th>Stock (kg)</th>
                    <th>Description</th>
                    <th>Price (Per Piece)</th>
                    <th>Discount (Per Piece)</th>
                    <th>Net Price</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>

                <% 
                    VegetablesServiceImpl vserviceImpl = new VegetablesServiceImpl();
                    List<VegetablesDetails> list = vserviceImpl.fetchAllVegetablesByVendorId(user.getId());
                    for (VegetablesDetails veg_item : list) {       
                %>
                    <tr>
                        <td style="display: none;"><%=veg_item.getVegId()%></td>
                        <td><%=veg_item.getVegName() %></td>
                        <td><%=veg_item.getVegCategory() %></td>
                        <td><%=veg_item.getQuantity() %></td>  
                        <td><%=veg_item.getDescription() %></td>
                        <td><%=veg_item.getPricePerPiece() %></td>
                        <td><%=veg_item.getDiscount_per_piece() %></td>
                        <td><%=veg_item.getNet_price() %></td>
                        <td><img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="<%=veg_item.getVegPicName() %>" width="50"></td>
                        <td>
                            <button class="button" style="max-width:55% !important;" onclick="populateForm(<%=veg_item.getVegId()%>, '<%=veg_item.getVegName() %>', '<%=veg_item.getVegCategory() %>', <%=veg_item.getQuantity() %>, '<%=veg_item.getDescription() %>', <%=veg_item.getPricePerPiece() %>, <%=veg_item.getDiscount_per_piece() %>)">Update</button>
                            <form style="display: contents;" action="${pageContext.request.contextPath}/Vegetable" method="post">
                                <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
                                <input type="submit" name="action_type2"  value="delete" style="max-width: 100%; background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
                            </form> 
                        </td>
                    </tr>
                <% } %>    
                
                
                
                                <% 
                
                    List<VegetablesDetails> list2 = vserviceImpl.fetchAllDeletedVegetablesByVendorId(user.getId());
                    for (VegetablesDetails veg_item : list2) {       
                %>
                    <tr>
                        <td style="display: none;"><%=veg_item.getVegId()%></td>
                        <td><%=veg_item.getVegName() %></td>
                        <td><%=veg_item.getVegCategory() %></td>
                        <td><%=veg_item.getQuantity() %></td>  
                        <td><%=veg_item.getDescription() %></td>
                        <td><%=veg_item.getPricePerPiece() %></td>
                        <td><%=veg_item.getDiscount_per_piece() %></td>
                        <td><%=veg_item.getNet_price() %></td>
                        <td><img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="<%=veg_item.getVegPicName() %>" width="50"></td>
                        <td>
                            <form style="display: contents;" action="${pageContext.request.contextPath}/Vegetable" method="post">
                                <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
                                <input type="submit" name="restore_action" value="restore" style="max-width: 100%; background-color: #FFA07A; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
                            </form> 
                        </td>
                    </tr>
                <% } %>  
                
                   
            </table>
        </div>
    </div>

    <div id="sales" class="section">
        <h2>Sales</h2>
        <table>
            <tr>
                <th>Order Id</th>
                  <th>Order Date</th>
                <th>Vegetable Name</th>
                <th>Quantity Sold (kg)</th>
                <th>Total Amount</th>              
            </tr>
           <%
            
                VendorServiceImpl vendorservice_impl = new VendorServiceImpl();
               List<VendorOrdersVO> vendor_order_list = vendorservice_impl.getVendorOrders(user.getId(),"approved");
               for(VendorOrdersVO vendor_order :vendor_order_list )
               {     
           %>
           
             <tr>
             <td> <%= vendor_order.getOrder_id() %></td>
              <td> <%= vendor_order.getOrder_date() %></td>
                        <td><%=vendor_order.getVeg_name() %></td>
                        <td><%= vendor_order.getQuantity_added()%></td>
                        <td><%= vendor_order.getTotal_price()%></td>
                       
           
           </tr>
           
           
            <%} %>  
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

<script src="${pageContext.request.contextPath}/js/vendor.js"></script>
<%
/*---------CLOSE CONNECTION ----------------------- */
vserviceImpl.closeConnection();
vendorservice_impl.closeConnection();
/*---------CLOSE CONNECTION ----------------------- */
%>
</body>
</html>
