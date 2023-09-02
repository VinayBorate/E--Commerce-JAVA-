<%@page import="project.ConnectionProvider" %>
<%@page import="java.sql.*" %>
<%
String email=request.getParameter("email"); //"email" should be same as in login.jsp
String password=request.getParameter("password");
//From same page we are login for admin as welll as users so we will
//hard coding it  for admin through  if conition 
if("admin@gmail.com".equals(email) && "admin".equals(password))
{
session.setAttribute("email",email);	//Setting value of email in Session as it will be used in multiple places 
response.sendRedirect("admin/adminHome.jsp");//WE are moving in admin folder in which  adminHome.jsp is there 
//SO whenever Someone enter email as   admi@gmail.com and password as admin it will Send that emai's value  in Seession and 
//will redirect to adminHome.jsp page  
}

else //Writing code for other users than admin 
{
	int z =0 ;
	try 
	{
		Connection con = ConnectionProvider.getCon(); 
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from users where email ='"+email+"' and password='"+password+"'");//Checking wheteher users exits in user table or not 
		while (rs.next())
		{  z = 1;//User exits in table so z is set to 1
			session.setAttribute("email",email);
			response.sendRedirect("home.jsp");//so this for users other than admin if email matches with email from users tables and same for password it will redirect to home.jsp
		}
		
		if(z == 0 ) //User donot exits in table so z is  0
		{
			response.sendRedirect("login.jsp?msg=notexist");//So user donot exist so we redict to home.jsp along with notexist message 
		}
	}
	 catch (Exception e)
	{     System.out.println(e);
		 response.sendRedirect("login.jsp?msg=invalid");//if any errror ocuur jump to home.jsp and mes as invalid
	}
}


%>