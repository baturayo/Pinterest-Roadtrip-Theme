<%-- 
    Document   : profilepage
    Created on : May 3, 2018, 9:14:32 PM
    Author     : baturay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <style type="text/css">
                        /***
            User Profile Sidebar by @keenthemes
            A component of Metronic Theme - #1 Selling Bootstrap 3 Admin Theme in Themeforest: http://j.mp/metronictheme
            Licensed under MIT
            ***/

            body {
              background: #F1F3FA;
            }

            /* Profile container */
            .profile {
              margin: 20px 0;
            }

            /* Profile sidebar */
            .profile-sidebar {
              padding: 20px 0 10px 0;
              background: #fff;
            }

            .profile-userpic img {
              float: none;
              margin: 0 auto;
              width: 50%;
              height: 50%;
              -webkit-border-radius: 50% !important;
              -moz-border-radius: 50% !important;
              border-radius: 50% !important;
            }

            .profile-usertitle {
              text-align: center;
              margin-top: 20px;
            }

            .profile-usertitle-name {
              color: #5a7391;
              font-size: 16px;
              font-weight: 600;
              margin-bottom: 7px;
            }

            .profile-usertitle-job {
              text-transform: uppercase;
              color: #5b9bd1;
              font-size: 12px;
              font-weight: 600;
              margin-bottom: 15px;
            }

            .profile-userbuttons {
              text-align: center;
              margin-top: 10px;
            }

            .profile-userbuttons .btn {
              text-transform: uppercase;
              font-size: 11px;
              font-weight: 600;
              padding: 6px 15px;
              margin-right: 5px;
            }

            .profile-userbuttons .btn:last-child {
              margin-right: 0px;
            }

            .profile-usermenu {
              margin-top: 30px;
            }

            .profile-usermenu ul li {
              border-bottom: 1px solid #f0f4f7;
            }

            .profile-usermenu ul li:last-child {
              border-bottom: none;
            }

            .profile-usermenu ul li a {
              color: #93a3b5;
              font-size: 14px;
              font-weight: 400;
            }

            .profile-usermenu ul li a i {
              margin-right: 8px;
              font-size: 14px;
            }

            .profile-usermenu ul li a:hover {
              background-color: #fafcfd;
              color: #5b9bd1;
            }

            .profile-usermenu ul li.active {
              border-bottom: none;
            }

            .profile-usermenu ul li.active a {
              color: #5b9bd1;
              background-color: #f6f9fb;
              border-left: 2px solid #5b9bd1;
              margin-left: -2px;
            }

            /* Profile Content */
            .profile-content {
              padding: 20px;
              background: #fff;
              min-height: 460px;
            }
        </style>
        <c:choose>
            <c:when test="${isBlocked == 0}">
                <div class="container">
                    <div class="row profile">
                        <div class="col-md-3">
                            <div class="profile-sidebar">
                                <!-- SIDEBAR USERPIC -->
                                <div class="profile-userpic">
                                    <img src="http://keenthemes.com/preview/metronic/theme/assets/admin/pages/media/profile/profile_user.jpg" class="img-responsive" alt="">
                                </div>
                                <!-- END SIDEBAR USERPIC -->
                                <!-- SIDEBAR USER TITLE -->
                                <div class="profile-usertitle">
                                    <div class="profile-usertitle-name">
                                            ${requestScope.name_surname}
                                    </div>
                                    <div class="profile-usertitle-job">
                                            ${requestScope.country}
                                    </div>
                                </div>
                                <!-- END SIDEBAR USER TITLE -->
                                <!-- SIDEBAR BUTTONS -->
                                <div class="profile-userbuttons">
                                    <form name="messageForm" method="POST">
                                        <input class="btn btn-success btn-sm" name = "sendMessage" type="submit" value="Message" />                      
                                    </form>
                                </div>
                                <div class="profile-userbuttons">
                                    <form name="followForm" method="POST">
                                            <c:choose>
                                            <c:when test="${canFollow == 1}">
                                              <input class="btn btn-success btn-sm" name = "follow" type="submit" value="Follow" />
                                            </c:when>
                                            <c:when test="${canFollow == 0}">
                                              <input  class="btn btn-danger btn-sm" name = "follow" type="submit" value="Unfollow"/>
                                            </c:when>

                                            <c:otherwise>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${canBlock == 1}">
                                               <input class="btn btn-danger btn-sm" name = "block" type="submit" value="Block" />
                                            </c:when>
                                            <c:when test="${canBlock == 0}">
                                                <input class="btn btn-success btn-sm" name = "block" type="submit" value="Unblock" />
                                            </c:when>
                                            <c:otherwise>    
                                            </c:otherwise>    
                                        </c:choose>
                                    </div>
                                </form>
                            

                                <!-- END SIDEBAR BUTTONS -->
                                <!-- SIDEBAR MENU -->
                                <div class="profile-usermenu">
                                    <ul class="nav">
                                        <li>
                                            <form id="followerForm" method="post">
                                                <input type="hidden" name="showFollowers" value="showFollowers" />
                                                <a href="#" onclick="document.getElementById('followerForm').submit();">
                                                    <i class="glyphicon glyphicon-user"></i>
                                                    Follower  ${requestScope.follower}</a>
                                            </form>
                            
                                        </li>
                                        <li>
                                            <form id="followeeForm" method="post">
                                                <input type="hidden" name="showFollowees" value="showFollowees" />
                                                <a href="#" onclick="document.getElementById('followeeForm').submit();">
                                                    <i class="glyphicon glyphicon-user"></i>
                                                    Following  ${requestScope.followee}</a>
                                            </form>
                                        </li> 
                                    </ul>
                                </div>
                                <br>
                                <div class="profile-usermenu">                                    
                                    <ul class="nav">
                                                                          
                                        <li class="active">
                                            <a href="#">
                                            <i class="glyphicon glyphicon-home"></i>
                                            Overview </a>
                                        </li>
                            
                                        <li>
                                            <a href="/RoadTrip/settings">
                                            <i class="glyphicon glyphicon-user"></i>
                                            Account Settings </a>
                                        </li>
                                        <li>
                                            <a href="#" target="_blank">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            Tasks </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                            <i class="glyphicon glyphicon-flag"></i>
                                            Help </a>
                                        </li>
                                    </ul>
                                </div>
                                            <!-- END MENU -->
                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="profile-content">
                                           Some user related content goes here...
                            </div>
                        </div>
                </div>
            </div>
            <center>
            <!--<strong>Powered By </strong>-->
            </center>
            <br>
            <br>
        </c:when>
        <c:when test="${isBlocked == 1}">
            You are banned!!
        </c:when>
        </c:choose>
    </body>
    
</html>
