<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/addressPaymentForOrder-style.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Home</title>
<script>
if(window.history.forward(1) !=null)
   window.history.forward(1);</script> <!-- //will hide back button from window  -->
</head>
<body>
<br>
<div style="color:#000!important;background-color:#ffc107!important;  border-radius:3px; text-decoration: none; padding: 7px 12px; text-align: center; margin:auto; margin-bottom:20px; width:30%; font-size: 30px;">Payment Gateway <i class="fa fa-institution"></i></div>
<br>
<table>
<thead>
<%

int total = 0;
int sno = 0;
try{
	Connection con = ConnectionProvider.getCon();
	Statement st = con.createStatement();
	ResultSet rs1 = st.executeQuery("select sum(total) from cart where email='"+email+"' and address is NULL");
	while(rs1.next()){
		total=rs1.getInt(1);
	}

%>

          <tr>
          <th scope="col"><a href="myCart.jsp"><i class='fas fa-arrow-circle-left'> Back</i></a></th>
            <th scope="col" style="background-color: yellow;">Total: <i class="fa fa-inr"></i> <%out.println(total);%></th>
          </tr>
        </thead>
        <thead>
          <tr>
          <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Sub Total</th>
          </tr>
        </thead>
        <tbody>
        <%
		ResultSet rs2 = st.executeQuery("select * from product inner join cart on product.id=cart.product_id and cart.email='"+email+"' and cart.address is NULL");
     	while(rs2.next())
     	{
      %>
        
        
          <tr>
          <% sno = sno+1; %>
           <td><% out.println(sno); %></td>
            <td><%=rs2.getString(2) %></td>
            <td><%=rs2.getString(3) %></td>
            <td><i class="fa fa-inr"></i> <%=rs2.getString(4) %></td>
            <td> <%=rs2.getString(8) %> </td>
            <td><i class="fa fa-inr"></i> <%=rs2.getString(10) %></td>
            </tr>
       <%
       }
       ResultSet rs= st.executeQuery("select *from users where email = '"+email+"' ");
       while(rs.next())
       {
       %>  
        
        </tbody>
      </table>
      
<hr style="width: 100%">
<form action="addressPaymentForOrderAction.jsp" method= "post">


 <div class="left-div">
 <h3>Enter Address</h3>
 <input class="input-style" type ="text" name ="address" value ="<%=rs.getString(7)%>" placeholder="ENTER ADDRESS" required>

 </div>

<div class="right-div">
<h3>Enter city</h3>
 <input class="input-style" type ="text" name ="city" value ="<%=rs.getString(8)%>" placeholder="ENTER CITY" required>
</div> 

<div class="left-div">
<h3>Enter State</h3>
 <input class="input-style" type ="text" name ="state" value ="<%=rs.getString(9)%>" placeholder="ENTER STATE" required>

</div>

<div class="right-div">
<h3>Enter country</h3>
 <input class="input-style" type ="text" name ="country" value ="<%=rs.getString(10)%>" placeholder="ENTER COUNTRY" required>
</div>
<h4>*If there is no address its mean that you did not set you address!</h3>
<h4>*This address will also updated to your profile</h3>
<hr style="width: 100%">
<div class="left-div">
<h3>Select way of Payment</h3>
<select class="input-style" name="paymentMethod" >
<option value="Cash on delivery(COD)" > Cash on delivery(COD)</option>
<option value="Online Payment" > Online Payment</option>
</select>
 
</div>

<div class="right-div">
<h3>Pay online on this Shopping Site</h3>
<input class="input-style" type ="text" name ="transactionId"  placeholder="ENTER TRANSACTION ID" >

<h4>*If you select online Payment then enter you transaction ID here otherwise leave this blank</h3>
</div>
<hr style="width: 100%">

<div class="left-div">
<h3>Mobile Number</h3>
<input class="input-style" type ="text" name ="mobileNumber" value ="<%=rs.getString(3)%>" placeholder="ENTER MOBILE-NUMBER" required>

<h4>*This mobile number will also updated to your profile</h3>
</div>
<div class="right-div">
<h4>*If you enter wrong transaction id then your order will we can cancel!</h3>
<button class="button" type = "submit">PROCCED TO GENERATE BILL & SAVE<i class='far fa-arrow-alt-circle-right'></i></button>
<h4>*Fill form correctly</h3>
</div>
</form>
<%
       }  	
}

catch (Exception e)
{
System.out.println(e);
}%>

      <br>
      <br>
      <br>

</body>
</html>