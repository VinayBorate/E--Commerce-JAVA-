<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/sign-style.css">
<title>Login</title>
</head>
<body>

	<div class="wrapper">
		<%
			String msg = request.getParameter("msg");
		if ("notexist".equals(msg)) {
		%>
		<h4>Incorrect Username or Password</h4>
		<%
			}
		%>

		<%
			if ("invalid".equals(msg)) {
		%>

		<h4>Some thing Went Wrong! Try Again !</h4>
		<%
			}
		%>
		<div class="title">LOGIN</div>
		<div class="form">
			<form action="loginAction.jsp" method="post">
				<div class="inputfield">
					<label>Email</label> <input class="input" type="email" name="email"
						placeholder="Enter Your Email" required>
				</div>
				<div class="inputfield">
					<label>Password</label> <input class="input" type="password"
						name="password" placeholder="Enter Your Password" required>
				</div>
				<div class="inputfield">
					<input type="submit" value="Login" class="btn">
				</div>
			</form>
		</div>
		<br />

		<div>
			Not Yet Registered ? Please SignUp ! <a href="signup.jsp">SignUp</a>	
		</div>
		<br>
		<div>
			Forgot Password ? Reset Here ! <a href="forgotPassword.jsp">Forgot
				Password</a>
		</div>
	</div>

</body>
</html>
