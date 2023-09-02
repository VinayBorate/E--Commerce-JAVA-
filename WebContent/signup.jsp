<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/sign-style.css">
  <title>Signup</title>
</head>

<body>

  <div class="wrapper">


    <%
  //Getting value of message msg  from signupAction.jsp 
  String msg =request.getParameter("msg");
if ("valid".equals(msg)) //IF MSG CONTAIN VALID THEN 
{ 
%>
<h4>Succesfully Registered !
<br>
Please Login From Login Page !
</h4>
<% }%>
<%
if("invalid".equals(msg))
{
%>
<h4>Something Went Wrong !</h4>
<%} %>
            <div class="title">
              SIGNUP
            </div>
            <div class="form">
              <form action="signupAction.jsp" method ="post">
                <div class="inputfield">
                  <label>Name</label>
                  <input class="input" type="text" name="name" placeholder="Enter Your  Name" required>
                </div>
                <div class="inputfield">
                  <label>Email</label>
                  <input class="input" type="email" name="email" placeholder="Enter Your Email" required>
                </div>
                <div class="inputfield">
                  <label>Mobile Number</label>
                  <input class="input" type="number" name="mobileNumber" placeholder="Enter Your Mobile Number" required> 
                </div>
                <div class="inputfield">
                  <label>Select Security Question</label>
                  <select class="input" name ="securityQuestions" required>
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
                  <input class="input" type="password" name="password" placeholder="Enter Password" required>
                </div>
                <div class="inputfield">
                  <input type="submit" value = "Signup" class="btn"> 
                </div>
              </form>
            </div>
            <br />

            <div>
              Already Registered ? Please Login ! <a href="login.jsp">Login</a>
            </div>
  </div>

</body>

</html>