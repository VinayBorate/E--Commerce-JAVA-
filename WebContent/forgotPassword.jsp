<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/sign-style.css">
  <title>ForgotPassword</title>
</head>

<body>

  <div class="wrapper">


    <%
 String msg = request.getParameter("msg");
 if ("done".equals(msg))
 {
 %>  
<h4>Password Changed Successfully!</h4>
<%} %>

<%
 if ("invalid".equals(msg))
 {
 %>  
<h4>Some thing Went Wrong! Try Again !</h4>
<%} %>
            <div class="title">
             FORGOT PASSWORD
            </div>
            <div class="form">
              <form action="forgotPasswordAction.jsp" method= "post">
                <div class="inputfield">
                  <label>Email</label>
                  <input class="input" type="email" name="email" placeholder="Enter Your Email" required>
                </div>
                <div class="inputfield">
                  <label>Mobile Number</label>
                  <input class="input" type="number" name="mobileNumber" placeholder="Enter Your Mb Number" required> 
                </div>
                <div class="inputfield">
                  <label>Select Security Question</label>
                  <select class="input" name ="securityQuestion" required>
                    <option value= "What is the name of your favourite food?">What is the name of your favourite food?</option> 
                    <option value= "What is your favourite hobby?">What is your favourite hobby?</option>
                    <option value= "What is name of town where you were born?">What is name of town where you were born?</option>
                  </select>
                </div>
                <div class="inputfield">
                  <label>Answer For Security Question</label>
                  <input class="input" type="text" name="answer" placeholder="Enter Answer" required>
                </div>
                <div class="inputfield">
                  <label>Password</label>
                  <input class="input" type="password" name="newPassword" placeholder="Enter Your New Password" required>
                </div>
                <div class="inputfield">
                  <input type="submit" value = "Update Password" class="btn"> 
                </div>
              </form>
            </div>
            <br />
			
			<div>
              Do Want To Create A New Account ? <a href="signup.jsp">Signup</a>
            </div>
            <br>
            <div>
              Want To Login Again ? Go To Login ! <a href="login.jsp">Login</a>
            </div>
            
             
  </div>

</body>

</html>