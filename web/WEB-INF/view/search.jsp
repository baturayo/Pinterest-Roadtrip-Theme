<%-- 
    Document   : userPage
    Created on : Apr 5, 2018, 10:40:07 PM
    Author     : baturay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>



    <c:forEach items="${searchList}" var="searchItem">
 
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="well well-sm">
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive" />
                            </div>
                            <div class="col-sm-6 col-md-8">
                                <h4>
                                    ${searchItem.getFirstname()} ${searchItem.getSecondname()} 
                                  </h4>
                                <small><cite title="San Francisco, USA"> <i class="glyphicon glyphicon-map-marker">
                                </i></cite>${searchItem.getCountry().toUpperCase()}</small>
                                <p>
                                    <i class="glyphicon glyphicon-envelope"></i>${searchItem.getEmail()}
                                    <br />
                                    <i class="glyphicon glyphicon-user"></i>Follower: ${searchItem.getFollower().size()}
                                    <br />
                                    <i class="glyphicon glyphicon-user"></i>Following: ${searchItem.getFollowee().size()}</p>
                                <!-- Split button -->
                                <div class="btn-group">
                                    <a href="/RoadTrip/users/${searchItem.getUsername()}">
                                        <button class="btn btn-primary">Profile Page</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>
    </body>
</html>
