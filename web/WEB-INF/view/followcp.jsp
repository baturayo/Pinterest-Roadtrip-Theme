<%-- 
    Document   : wanttovisit
    Created on : Apr 12, 2018, 10:21:10 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FollowCP</title>
    </head>
    <body>
        <h5> ${user.getUsername()} is following these checkpoints: </h5>
        <c:forEach items="${followcp}" var="checkpoint">
            <div class="well">
                <a href="/RoadTrip/checkpoint?id=${checkpoint.getId()}"> <p>${checkpoint.getName()}</p></a>
                <p>This checkpoint is on the following roads:</p>
                <ul class="list-inline">
                    <c:forEach items="${checkpoint.getRoads()}" var="road">
                        <a href="/RoadTrip/road/${road.getId()}"><li class="list-group-item">${road.getName()}</li></a>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </body>
</html>
