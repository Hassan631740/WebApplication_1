<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        .form-container {
            width: 50%;
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .btn {
            display: inline-block;
            padding: 8px 12px;
            margin: 5px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-secondary {
            background-color: #607D8B;
        }
    </style>
</head>
<body>
    <div>
        <h2>User Form</h2>
        
        <div class="form-container">
            <form:form action="${pageContext.request.contextPath}/users/saveUser" modelAttribute="user" method="POST">
                <!-- Need to associate this data with user id -->
                <form:hidden path="id" />
                
                <div class="form-group">
                    <label>First Name:</label>
                    <form:input path="firstName" />
                </div>
                
                <div class="form-group">
                    <label>Last Name:</label>
                    <form:input path="lastName" />
                </div>
                
                <div class="form-group">
                    <label>Email:</label>
                    <form:input path="email" type="email" />
                </div>
                
                <div class="form-group">
                    <input type="submit" value="Save" class="btn" />
                    <a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">Cancel</a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>