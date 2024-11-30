<%@page import="com.vinay.serviceImpl.UsersServiceImpl"%>
<%@page import="com.vinay.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

    <head>
    <title>Vinay Ecommerce Store</title>
        <%@include file="components/links.jsp" %>
        <%@include file="components/jslibraries.jsp" %>
        <link href="css/style.css" rel="stylesheet">
        <style> 
            .error {
                border: 2px solid red;
                background-color: #ffe6e6;
            }
        </style>
    </head>
    <body>

<%@include file="components/navbar.jsp" %>

  <!-- Content Section Start -->
        
    <div class="container mt-5 pt-5 content">
        <h2 class="text-center mb-4">Register</h2>
        <div class="row">
            <div class="offset-2 col-8 offset-2">
            
 <c:if test="${not empty checkmessage}">
    <p class="text-danger">${checkmessage}</p>
    <c:remove var="checkmessage" scope="request"/>
</c:if>     
 
 <c:if test="${not empty error}">
    <p class="text-info">${error}</p>
 </c:if>

<div class="w-50 w-md-50 w-sm-75 w-100 mx-auto" style="max-width: 500px;">
    <form id="registrationForm" action="${pageContext.request.contextPath}/users" method="post" onsubmit="return validateForm()">
        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="username" class="me-2 w-25 text-start">User Name:</label>
            <input type="text" id="username" name="username" required placeholder="Enter Your User Name" 
            class="form-control form-control-sm w-75 border-0 py-2 ${not empty sessionScope.error && sessionScope.error=='Username already exists.' ? 'error':''}">
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="email" class="me-2 w-25 text-start">Email:</label>
            <input type="email" required placeholder="Enter Your EmailId" name="email"  
            class="form-control form-control-sm w-75 border-0 py-2 ${not empty sessionScope.error && sessionScope.error=='Email Id already exists.' ? 'error':''}">
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="mobile" class="me-2 w-25 text-start">Mobile No:</label>
            <input type="tel" name="mobile" maxlength="10" required placeholder="Enter Your Mobile No" value="${sessionScope.mobile}" class="form-control form-control-sm w-75 border-0 py-2">
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="address" class="me-2 w-25 text-start">Address:</label>
            <input type="text" name="address" required placeholder="Enter Your Address" value="${sessionScope.address}" class="form-control form-control-sm w-75 border-0 py-2">
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="state" class="me-2 w-25 text-start">State:</label>
            <input type="text" name="state" required placeholder="Enter Your State" value="${sessionScope.state}" class="form-control form-control-sm w-75 border-0 py-2">
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="city" class="me-2 w-25 text-start">City:</label>
            <input type="text" name="city" required placeholder="Enter Your City" value="${sessionScope.city}" class="form-control form-control-sm w-75 border-0 py-2">
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="pincode" class="me-2 w-25 text-start">Pincode:</label>
            <input type="number"  min="10000" max="99999" oninput="this.value = this.value.slice(0,5)" name="pincode" required placeholder="Enter Your Pincode" value="${sessionScope.pincode}" class="form-control form-control-sm w-75 border-0 py-2">
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="password" class="me-2 w-25 text-start">Password:</label>
            <input type="password" id="password" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Your Password" name="password" required onkeyup="checkPasswordStrength()">
            <small id="passwordStrengthMessage" class="form-text position-absolute" style="right: 10px; color: red;"></small>
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="confirm-password" class="me-2 w-25 text-start">Confirm Password:</label>
            <input type="password" id="confirm-password" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Confirm Your Password" name="confrmpassword" required onkeyup="checkPasswordMatch()">
            <small id="passwordMatchMessage" class="form-text position-absolute" style="right: 10px; color: red;"></small>
        </div>

        <div class="mb-3 d-flex justify-content-between position-relative">
            <label for="usertype" class="me-2 w-25 text-start">User Type:</label>
            <select name="usertype" class="form-control form-control-sm w-75 border-0 py-2">
                <option value="customer" selected>Customer</option>
                <%
                UsersServiceImpl impl = new UsersServiceImpl();
             boolean b = impl.doesAdminUserExist();
                if(!b) {
                %>
                <option value="admin" selected>Admin</option>
                
                <%} %>
                <option value="vendor">Vendor</option>
            </select>
        </div>

        <div class="mb-3 d-flex align-items-center">
            <input type="checkbox" id="check" name="check" required class="me-2">
            <label for="check" class="mb-0">Agree to terms and Conditions</label>
        </div>

        <input type="hidden" value="register" name="action" />
    
        <button class="w-100 btn border-secondary py-2 bg-white text-primary" type="submit">Register</button>
    </form>
</div>

<br><br>
<script>
    const publicKeyPem = `
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA62p7h7YhhFxK5mCUwQ6KiG2QOmfrYcyZQqNPgy1R+BJM5tV7GmyyYAs3RuXx3zYHWTYOSF+0kapCMosWgR6dSpYFqbNHFmGXEzFihTgf15fVmZF+T96JQoTz6N2b/fPmInMkqgS3oIbO7s2KYjvMghFCMLZheMhiMedELnI3kn+Qt5EJdOASUCHET0qLnZztSHSbCXs5L3g3+4HsN9B9cE+pVzFaiNBg0eNNmpeg+HEhxAmoph5JhZlbdbCUzDR+IJjjCr3deZlRBPKELXo+7tq4tcbAwxJe/xdZbHvkfnf0c5H9M1897RKh8HJWDMAXf8nRlG0gv5I+w4Q65sPnrwIDAQAB
    `;

    async function importPublicKey(pem) {
        const binaryDer = str2ab(pem);
        return crypto.subtle.importKey(
            'spki',
            binaryDer,
            {
                name: 'RSA-OAEP',
                hash: { name: 'SHA-256' }
            },
            true,
            ['encrypt']
        );
    }

    function str2ab(str) {
        const binaryString = window.atob(str.replace(/\s/g, ''));
        const len = binaryString.length;
        const bytes = new Uint8Array(len);
        for (let i = 0; i < len; i++) {
            bytes[i] = binaryString.charCodeAt(i);
        }
        return bytes.buffer;
    }

    async function encryptData(publicKey, data) {
        const encryptedBuffer = await crypto.subtle.encrypt(
            {
                name: 'RSA-OAEP'
            },
            publicKey,
            new TextEncoder().encode(data)
        );
        return btoa(String.fromCharCode.apply(null, new Uint8Array(encryptedBuffer)));
    }

    document.getElementById('registrationForm').addEventListener('submit', async function (e) {
        e.preventDefault(); // Prevent the form from submitting normally

        // Encrypt the username, email, password, and confirm password
        const username = document.getElementById('username').value;
        const email = document.getElementsByName('email')[0].value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        const publicKey = await importPublicKey(publicKeyPem);

        const encryptedUsername = await encryptData(publicKey, username);
        const encryptedEmail = await encryptData(publicKey, email);
        const encryptedPassword = await encryptData(publicKey, password);
        const encryptedConfirmPassword = await encryptData(publicKey, confirmPassword);

        // Set the encrypted values back to the input fields
        document.getElementById('username').value = encryptedUsername;
        document.getElementsByName('email')[0].value = encryptedEmail;
        document.getElementById('password').value = encryptedPassword;
        document.getElementById('confirm-password').value = encryptedConfirmPassword;

        // Submit the form after encrypting
        this.submit();
    });

    //////
    
    
     // Function to check password strength
    function checkPasswordStrength() {
        const password = document.getElementById('password').value;
        const strengthMessage = document.getElementById('passwordStrengthMessage');
        const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}$/;

        if (password.length < 8) {
            strengthMessage.style.color = 'red';
            strengthMessage.textContent = 'Password is too short';
        } else if (!passwordRegex.test(password)) {
            strengthMessage.style.color = 'red';
            strengthMessage.textContent = 'Weak password:';
        } else {
            strengthMessage.style.color = 'green';
            strengthMessage.textContent = 'Strong password';
        }
    }

    // Function to check if passwords match
    function checkPasswordMatch() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;
        const matchMessage = document.getElementById('passwordMatchMessage');

        if (password !== confirmPassword) {
            document.getElementById('confirm-password').style.borderColor = 'red';
            matchMessage.style.color = 'red';
            matchMessage.textContent = 'Passwords do not match!';
        } else {
            document.getElementById('confirm-password').style.borderColor = 'green';
            matchMessage.style.color = 'green';
            matchMessage.textContent = 'Passwords match!';
        }
    }

    // Final form validation before submission
    function validateForm() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;
        const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}$/;

        // Check if passwords match
        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return false;
        }

        // Check if password is strong
        if (!passwordRegex.test(password)) {
            alert("Password must be at least 8 characters long, contain at least one uppercase letter, one number, and one special character.");
            return false;
        }

        return true; // Allow form submission
    }
    
    
</script>


 <c:remove var="error" scope="session"/> 
 <c:remove var="mobile" scope="session"/> 
 <c:remove var="address" scope="session"/> 
 <c:remove var="state" scope="session"/> 
  <c:remove var="city" scope="session"/> 
   <c:remove var="pincode" scope="session"/> 

            </div>
        </div>
    </div>
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>

    </body>

</html>
