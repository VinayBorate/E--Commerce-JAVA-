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

            document.addEventListener('DOMContentLoaded', function() {
                document.querySelector('form').addEventListener('submit', async function(e) {
                    e.preventDefault(); // Prevent the form from submitting normally

                    // Encrypt the username and password before submitting the form
                    const username = document.getElementById('username').value;
                    const password = document.getElementById('password').value;

                    const publicKey = await importPublicKey(publicKeyPem);

                    const encryptedUsername = await encryptData(publicKey, username);
                    const encryptedPassword = await encryptData(publicKey, password);

                    // Set the encrypted values back to the input fields
                    document.getElementById('username').value = encryptedUsername;
                    document.getElementById('password').value = encryptedPassword;

                    // Submit the form after encrypting
                    this.submit();
                });
            });
        </script>
    </head>
    <body>

<%@include file="components/navbar.jsp" %>

<br><br>

  <!-- Content Section Start -->
    <div class="container mt-5 pt-5 content">
        <c:if test="${not empty sucessmessage}">
            <p class="text-success">${sucessmessage}</p>
            <c:remove var="sucessmessage" scope="session"/>
        </c:if>
        <c:if test="${not empty failureMessage}">
            <p class="text-danger">${failureMessage}</p>
            <c:remove var="failureMessage" scope="session"/>
        </c:if>
        <h2 class="text-center mb-4">Login</h2>
        <div class="row">
            <div class="offset-2 col-8 offset-2">

                <div class="w-50 w-md-50 w-sm-75 w-100 mx-auto" style="max-width: 400px;">
                    <form action="${pageContext.request.contextPath}/users" method="post">
                        <div class="mb-4 d-flex justify-content-between">
                            <label for="username" class="me-2 w-25 text-start">User Name:</label>
                            <input type="text" id="username" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Your User Name" name="username" required="required">
                        </div>
                        <div class="mb-4 d-flex justify-content-between">
                            <label for="password" class="me-2 w-25 text-start">Password:</label>
                            <input type="password" id="password" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Your Password" name="password" required="required">
                        </div>
                        <input type="hidden" value="login" name="action" />
                        <button class="w-100 btn border-secondary py-2 bg-white text-primary" type="submit">Login</button>
                    </form>
                </div>

            </div>
        </div>
        <br>
    </div>
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>

 </body>

</html>
