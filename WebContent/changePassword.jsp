<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file="changeDetailsHeader.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Change Password</title>
</head>
<body>
<div style="color:#000!important;background-color:#ffc107!important;  border-radius:3px; text-decoration: none; padding: 7px 12px; text-align: center; margin:auto; margin-bottom:20px; width:25%; font-size: 30px;">Change Password <i class='fas fa-pen-fancy'></i></div>
<%
String msg = request.getParameter("msg");
if("notMatch".equals(msg))
{
%>
<h3 class="alert">New password and Confirm password does not match!</h3>
<%}%>

<%
if("wrong".equals(msg))
{
%>
<h3 class="alert">Your old Password is wrong!</h3>
<%}%>

<%
if("done".equals(msg))
{
%>
<h3 class="alert">Password change successfully!</h3>
<%}%>

<%
if("invalid".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try again!</h3>
<%}%>

<form action="changePasswordAction.jsp" method="post">
<h3>Enter Old Password</h3>
<input class="input-style"  type="password" name="oldPassword" placeholder="Enter Old Password" required>
  <hr>
 <h3>Enter New Password</h3>
<input class="input-style"  type="password" name="newPassword" placeholder="New Password" required>
 <hr>
<h3>Enter Confirm Password</h3>
<input class="input-style"  type="password" name="confirmPassword" placeholder="Confirm Password" required>
<hr>
<button class="button" type="submit"> Save <i class='far fa-arrow-alt-circle-right'></i></button>
</form>

</body>
<br><br><br>
</html>