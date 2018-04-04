<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : login
    Created on : Mar 10, 2018, 2:21:25 PM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="708053299849-a1sjhsrfdcqv9ajs7h5453f9uv5thccl.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <!--script src="scripts/FacebookLogin.js"></script-->

        <script src="simplemap/mapdata.js"></script>
        <script src="simplemap/worldmap.js"></script>

        <title>Login</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Escape Road</a>
                    <a class="navbar-brand" data-toggle="modal" data-target="#loginmodal">Log In</a>
                    <a class="navbar-brand" href="register">Register</a>
                </div>
            </div>
        </nav>
        <h1>HELLO ${sessionScope.title}</h1>
        <div id="map"></div>
        <c:if test="${sessionScope.userId == null}" >

            <div class="modal fade" id="loginmodal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h4 class="modal-title">Welcome To Escape Road</h4>
                        </div>

                        <div class="modal-body">
                            <form name="loginForm" action="login" method="POST">
                                <div class="form-group">
                                    <input type="hidden" name="formName" value="LoginForm" readonly="readonly"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="username" name="username">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="password" name="password">
                                </div>
                                <button type="submit" class="btn btn-success">Log in</button>
                            </form>
                            <a href="register">Don't have an account? Register by clicking this link.</a>
                        </div>      
                    </div>
                </div>
            </div>

            <script>
                $(document).ready(function () {
                    $("#loginmodal").modal('show');
                });
            </script>
        </c:if>


        <c:if test="${requestScope.loginError != null}">
            <script>
                function alertLogin() {
                    alert("${requestScope.loginError}");
                }
                window.onload = alertLogin;
            </script>
        </c:if>
    </body>
</html>
