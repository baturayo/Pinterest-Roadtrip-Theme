<%-- 
    Document   : login
    Created on : Mar 10, 2018, 2:21:25 PM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form name="login-form" action="main" method="POST">
            <div class="row"> 
                username:<input type="text" name="username" required/>
            </div>
            <div class = "row">
                password:<input type="password" name="password" required/>
            </div>
            <div class = "row">
                <input type="submit" value="Login" name="Login" />
            </div>
        </form>
        <a href="register">Not a member yet?</a>
    </body>
</html>
