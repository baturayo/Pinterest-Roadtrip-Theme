<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : login
    Created on : Mar 10, 2018, 2:21:25 PM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--script src="scripts/FacebookLogin.js"></script-->
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="708053299849-a1sjhsrfdcqv9ajs7h5453f9uv5thccl.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>        <title>Register</title>

        <script src="/RoadTrip/simplemap/mapdata.js"></script>
        <script src="/RoadTrip/simplemap/worldmap.js"></script>

        <h1>HELLO ${sessionScope.title}</h1>
        <div id="map"></div>
        <div class="modal fade" id="loginmodal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Welcome To Escape Road</h4>
                    </div>

                    <div class="modal-body">
                        <form name="loginForm" action="/RoadTrip/login" method="POST">
                            
                            <script>
                              function onSignIn(googleUser) {
                                // Useful data for your client-side scripts:
                                var profile = googleUser.getBasicProfile();
                                console.log("ID: " + profile.getId()); // Don't send this directly to your server!
                                console.log('Full Name: ' + profile.getName());
                                console.log('Given Name: ' + profile.getGivenName());
                                console.log('Family Name: ' + profile.getFamilyName());
                                console.log("Image URL: " + profile.getImageUrl());
                                console.log("Email: " + profile.getEmail());

                                // The ID token you need to pass to your backend:
                                var id_token = googleUser.getAuthResponse().id_token;
                                console.log("ID Token: " + id_token);
                              };
                            </script>
                            <div class="form-group">
                                <input type="hidden" name="formName" value="LoginForm" readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="email" name="username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="password" name="password">
                            </div>
                            <button type="submit" class="btn btn-success">Log in</button>
                            <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
                        </form>
                        <a href="register">Don't have an account? Register by clicking this link.</a>
                    </div>      
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.userId == null}">
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
