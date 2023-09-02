<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/messageUs.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Message Us</title>
</head>
<body>

<div style="color:#000!important;background-color:#ffc107!important;  border-radius:3px; text-decoration: none; padding: 7px 12px; text-align: center; margin:auto; margin-bottom:20px; width:20%; font-size: 30px;"">Message Us <i class='fas fa-comment-alt'></i></div>

<%
String msg = request.getParameter("msg");

if("valid".equals(msg))
{
%>
<h3 style="text-align:center; color:yellow;">Message successfully sent. Our team will contact you soon!</h3>
<% } %>

<%
if("invalid".equals(msg))
{
%>
<h3 style="text-align:center; ">Some thing Went Wrong! Try Again!</h3>
<% } %>

<form action="messageUsAction.jsp" method="post">
<input class="input-style" type="text" name="subject" placeholder="Subject" required>
<hr>
<textarea class="input-style" name="body" placeholder="Enter Your Message" required></textarea>
<button class="button" type="submit">Send <i class="far fa-arrow-alt-circle-right"></i></button>
</form>

<br><br><br>
</body>
</html>