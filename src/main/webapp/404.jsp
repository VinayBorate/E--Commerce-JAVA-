<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found | Vinay Ecommerce Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #1a1a1a;
            color: #ffffff;
            font-family: 'Arial', sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            overflow: hidden;
        }
        .error-container {
            text-align: center;
            max-width: 600px;
            padding: 20px;
        }
        .error-code {
            font-size: 120px;
            font-weight: bold;
            color: #4CAF50;
            text-shadow: 0 0 10px rgba(76, 175, 80, 0.7);
            margin-bottom: 20px;
        }
        .error-message {
            font-size: 24px;
            margin-bottom: 30px;
        }
        .home-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 15px 30px;
            font-size: 18px;
            border-radius: 50px;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }
        .home-button:hover {
            background-color: #45a049;
            box-shadow: 0 0 15px rgba(76, 175, 80, 0.5);
            transform: translateY(-3px);
        }
        .vegetable-icon {
            font-size: 48px;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        @keyframes float {
            0% { transform: translateY(0px); }
            50% { transform: translateY(-20px); }
            100% { transform: translateY(0px); }
        }
        .floating {
            animation: float 3s ease-in-out infinite;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="vegetable-icon floating">
            <i class="fas fa-carrot"></i>
        </div>
        <div class="error-code">404</div>
        <div class="error-message">Oops!..........</div>
        <p class="mb-4">The page you're looking for couldn't be found.</p>
        <a href="home.jsp" class="home-button">
            <i class="fas fa-home me-2"></i>Return to Home
        </a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
