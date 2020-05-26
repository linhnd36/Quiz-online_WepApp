<%-- 
    Document   : signUp
    Created on : May 20, 2020, 10:14:01 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Sign Up Page</title>
    </head>
    <body class="bg_index">
        <div class="container">
            <form action="MainController" method="POST" class="login-form" style="height: 800px">
                <h1 style="margin-top: -50px">Quiz Online</h1>
                <h2>Sign Up</h2>
                <div class="txtb">
                    <h5>Email:</h5>
                    <input type="email" name="txtEmail" value="" />
                </div>
                <div class="txtb">
                    <h5>Name:</h5>
                    <input type="text" name="txtName" value="" />
                </div>
                <div class="txtb">
                    <h5>Password:</h5>
                    <input type="password" name="txtPassword" value="" />
                </div>
                <div class="txtb">
                    <h5>Confirm Password:</h5>
                    <input type="password" name="txtConfirmPassword" value="" />
                </div>
                <input type="submit" class="logbtn" value="Registration" name="action" />
                <div class="bottom-text">Back to Login <a href="signUp.jsp">Back</a></div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>