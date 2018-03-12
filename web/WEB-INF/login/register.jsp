<%-- 
    Document   : register
    Created on : Mar 10, 2018, 2:21:33 PM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        REGISTER
        <form name="registerform" action="main" method="POST">
            <div class="row">
                First name: <input type="text" name="firstName" required />
            </div>
            <div class="row">
                Second name: <input type="text" name="username" required/>
            </div>
            <div class="row">
                Country: <input type="text" name="country" value="" />
            </div>
            <div class="row">
                Gender: <select name="gender" required>
                    <option>Male</option>
                    <option>Female</option>
                    <option>Potato</option>
                </select>
            </div>
            <div class="row">
                Username: <input type="text" name="username" required/>
            </div>
            <div class="row">
                e-mail: <input type="email" name="mail" required/>
            </div>
            <div class="row">
                Password: <input type="password" name="password1" required/>
            </div>
            <div class="row">
                <input type="submit" value="Register" name="submit" />
            </div>
        </form>
    </body>
</html>
