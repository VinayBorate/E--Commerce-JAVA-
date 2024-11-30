<%@page import="com.vinay.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>




<!-- Navbar start -->
<div class="container-fluid fixed-top">
    <div class="px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="home.jsp" class="navbar-brand">
                <img src="${pageContext.request.contextPath}/img/V_MartLogoNew2.png" alt="${pageContext.request.contextPath}/img/V_MartLogoNew2.png">
            </a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars" style="color: black !important;"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto d-flex align-items-center">
                    <!-- Home Icon -->
                    <a href="home.jsp" class="nav-item nav-link" aria-current="page">
                        <i class="fa fa-home fa-2x fa-icon-colour"></i>
                    </a>

                    <!-- Show Login & Register if User is Not Logged In -->
                    <%
                        Users user_1 = (Users) session.getAttribute("user");
                        if (user_1 == null) {
                    %>
                    <a href="login.jsp" class="nav-item nav-link fa-icon-colour">Login</a>
                    <a href="register.jsp" class="nav-item nav-link fa-icon-colour">Register</a>
                    <%
                        } else if (user_1.getUsertype().equals("customer")) {
                    %>  

                    <!-- Cart Icon -->
                    <a href="cart.jsp" class="nav-item nav-link position-relative" aria-label="Shopping Cart">
                        <i class="fa fa-shopping-cart fa-2x fa-icon-colour"></i>
                    </a>

                    <!-- My Orders Icon -->
                    <a href="myorders.jsp" class="nav-item nav-link" aria-label="My Orders">
                        <i class="fas fa-box fa-2x fa-icon-colour"></i>
                    </a>
                    
                    <!-- Profile Icon with Hover Effect -->
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link position-relative" id="profileDropdown" aria-label="Profile" role="button" data-bs-toggle="dropdown">
                            <i class="fas fa-user-circle fa-2x fa-icon-colour"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end p-3" aria-labelledby="profileDropdown" style="min-width: 200px;">
                            <p><strong>Username:</strong> <%= user_1.getUsername() %></p>
                            <p><strong>Email:</strong> <%= user_1.getEmail() %></p>
                            <p><strong>Type :</strong> <%= user_1.getUsertype() %></p>
                            <!-- Go to Profile Link -->
                            <a href="profile.jsp" class="dropdown-item mt-2">Go to Profile</a>
                        </div>
                    </div>
                    
                    <%
                        } else if (user_1.getUsertype().equals("vendor")){
                    %>


                    <!-- My Orders Icon -->
                    <a href="myorders.jsp" class="nav-item nav-link" aria-label="My Orders">
                        <i class="fas fa-box fa-2x fa-icon-colour"></i>
                    </a>
                    
                      <!-- Inventory Icon -->
                    <a href="inventory.jsp" class="nav-item nav-link" aria-label="Inventory">
                        <i class="fas fa-boxes fa-2x fa-icon-colour"></i>
                    </a>
                    
                    
                    
                    <!-- Profile Icon with Hover Effect -->
                   <div class="nav-item dropdown">
    <a href="#" class="nav-link position-relative" id="profileDropdown" aria-label="Profile" role="button" data-bs-toggle="dropdown">
        <i class="fas fa-user-circle fa-2x fa-icon-colour"></i>
    </a>
    <div class="dropdown-menu dropdown-menu-end p-3" aria-labelledby="profileDropdown" style="min-width: 200px;">
        <p><strong>Username:</strong> <%= user_1.getUsername() %></p>
        <p><strong>Email:</strong> <%= user_1.getEmail() %></p>
        <p><strong>Type :</strong> <%= user_1.getUsertype() %></p>
        <a href="profile.jsp" class="dropdown-item mt-2">Go to Profile</a>
    </div>
</div>

<style>
    /* CSS to show dropdown on hover */
    .nav-item.dropdown:hover .dropdown-menu {
        display: block;
    }
</style>


<%} else if (user_1.getUsertype().equals("admin")){ %>

 <div class="nav-item dropdown">
    <a href="#" class="nav-link position-relative" id="profileDropdown" aria-label="Profile" role="button" data-bs-toggle="dropdown">
        <i class="fas fa-user-circle fa-2x fa-icon-colour"></i>
    </a>
    <div class="dropdown-menu dropdown-menu-end p-3" aria-labelledby="profileDropdown" style="min-width: 200px;">
        <p><strong>Username:</strong> <%= user_1.getUsername() %></p>
        <p><strong>Email:</strong> <%= user_1.getEmail() %></p>
        <p><strong>Type :</strong> <%= user_1.getUsertype() %></p>
        <a href="profile.jsp" class="dropdown-item mt-2">Go to Profile</a>
    </div>
</div>

<style>
    /* CSS to show dropdown on hover */
    .nav-item.dropdown:hover .dropdown-menu {
        display: block;
    }
</style>

<%} %>

                    <!-- About Icon -->
                    <a href="about.jsp" class="nav-item nav-link fa-icon-colour">About</a>
                </div>

                <!-- Right Side Icons -->
                <div class="d-flex m-3 me-0 align-items-center">
                    <!-- Search Icon -->
                    <a href="search.jsp" class="position-relative me-4 my-auto" aria-label="Search">
                        <i class="fas fa-search fa-2x fa-icon-colour"></i>
                    </a>

<% if (user_1 != null) {
    %>
                    <!-- Logout Icon -->
                    <a href="${pageContext.request.contextPath}/logout" class="my-auto" aria-label="Log Out">
                        <i class="fas fa-power-off fa-2x danger-colour"></i>
                    </a>
                    
                    <%} %>
                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Navbar End -->
