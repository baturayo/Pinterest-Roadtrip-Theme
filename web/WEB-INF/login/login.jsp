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
    <title>Login</title>
</head>
<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">P4RT</a>
            </div>
            <form class="navbar-form navbar-right" name="loginForm" action="login" method="POST">
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
        </div>
    </nav>        
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <img src="https://cf.bodyandmind.amsterdam/wp-content/uploads/2016/02/roadtrip.jpg" class="img-rounded" alt="rtimg">
            </div>
            <div class="col-sm-6">
                <h3>Make A New Account</h3>
                <form name="registerform" action="login" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="formName" value="RegisterForm" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        First name: <input type="text" name="firstName" required />
                    </div>
                    <div class="form-group">
                        Last name: <input type="text" name="lastName" required/>
                    </div>
                    <div class="form-group">
                        Country: <input type="text" name="country" value="" />
                    </div>
                    <div class="form-group">
                        Gender: <select name="gender" required>
                            <option>Male</option>
                            <option>Female</option>
                            <option>Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Username: <input type="text" name="username" required/>
                    </div>
                    <div class="form-group">
                        e-mail: <input type="email" name="email1" required/>
                    </div>
                    <div class="form-group">
                        Password: <input type="password" name="password1" required/>
                    </div>
                    <div class=form-group">
                        <button type="submit" class="btn btn-primary">Register</button>
                    </div>
                </form>

            </div>
        </div>
        <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
        <script>
        function onSignIn(googleUser) {
            // Useful data for your client-side scripts:
            var profile = googleUser.getBasicProfile();
            console.log(profile);
            console.log("ID: " + profile.getId()); // Don't send this directly to your server!
            console.log('Full Name: ' + profile.getName());
            console.log('Given Name: ' + profile.getGivenName());
            console.log('Family Name: ' + profile.getFamilyName());
            console.log("Image URL: " + profile.getImageUrl());
            console.log("Email: " + profile.getEmail());

            // The ID token you need to pass to your backend:
            var id_token = googleUser.getAuthResponse().id_token;
            console.log("ID Token: " + id_token);
        }
        ;
        </script>

        <a href="#" onclick="signOut();">Sign out</a>
        <script>
            function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    console.log('User signed out.');
                });
            }
        </script>
    </div>
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
