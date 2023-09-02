<%@page import="project.ConnectionProvider" %>
<%@page import="java.sql.*" %>
<%
String name=request.getParameter("name"); //"name" should be same as in signup.jsp
String email=request.getParameter("email"); 
String mobileNumber=request.getParameter("mobileNumber"); 
String securityQuestions=request.getParameter("securityQuestions"); 
String answer=request.getParameter("answer"); 
String password=request.getParameter("password"); 
//SO RIGHT NOW ABOVE WE HAVE INSERTED 6 VALUES SO REST WILL BE NULL
//TO AVOID SETTING REST TO NULL IN DB (as NULL CAUSE PROBLEM )
//so we do String address=""; THIS will set blank space instead of null 
String address="";
String city="";
String state="";
String country ="";
try
{
	Connection con = ConnectionProvider.getCon();
	PreparedStatement ps = con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?)");
	ps.setString(1,name);
	ps.setString(2,email);
	ps.setString(3,mobileNumber);
	ps.setString(4,securityQuestions);
	ps.setString(5,answer);
	ps.setString(6,password);
	ps.setString(7,address);
	ps.setString(8,city);
	ps.setString(9,state);
	ps.setString(10,country);
	ps.executeUpdate();//WILL INSERT DATA INTO DB
	response.sendRedirect("signup.jsp?msg=valid");//SENDING RESPONSE TO  signup.jsp PAGE AND WITH MESSAGE msg valid
	
	
}
catch(Exception e)
{System.out.println(e);
	response.sendRedirect("signup.jsp?msg=invalid");
}

%>