<%-- 
    Document   : userPage
    Created on : Apr 5, 2018, 10:40:07 PM
    Author     : baturay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <style type="text/css">
#ProfilePage
{
    /* Move it off the top of the page, then centre it horizontally */
    margin: 50px auto;
    width: 635px;

/* For visibility. Delete me */
border: 1px solid red;
}

#LeftCol
{
    /* Move it to the left */
    float: left;

    width: 200px;
    text-align: center;

    /* Move it away from the content */
    margin-right: 20px;

/* For visibility. Delete me */
border: 1px solid brown;
}

#Photo
{
    /* Width and height of photo container */
    width: 200px;
    height: 200px;

/* For visibility. Delete me */
border: 1px solid green;
}

#PhotoOptions
{
    text-align: center;
    width: 200px;

/* For visibility. Delete me */
border: 1px solid brown;
}

#Info
{
    width: 400px;
    text-align: center;

    /* Move it to the right */
    float: right;

/* For visibility. Delete me */
border: 1px solid blue;
}

#Info strong
{
    /* Give it a width */
    display: inline-block;
    width: 100px;

/* For visibility. Delete me */
border: 1px solid orange;
}

#Info span
{
    /* Give it a width */
    display: inline-block;
    width: 250px;

/* For visibility. Delete me */
border: 1px solid purple;
}
</style>

<div id="ProfilePage">
    <div id="LeftCol">
        <div id="Photo"></div>
        <div id="ProfileOptions">
        a
        </div>
    </div>

    <div id="Info">
        <form name="followForm" method="POST">
            <c:choose>
                <c:when test="${canFollow == 1}">
                  <input name = "follow" onClick="window.location.reload();" type="submit" value="Follow!" />
                </c:when>
                <c:when test="${canFollow == 0}">
                  <input  name = "unfollow" onClick="window.location.reload();" type="submit" value="Unfollow!"/>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </form>
        <p>
            <strong>Name Surname:</strong>
            <span>${requestScope.name_surname}</span>
        </p>
        <p>
            <strong>Followee:</strong>
            <span>${requestScope.followee}</span>
        </p>
        <p>
            <strong>Follower:</strong>
            <span>${requestScope.follower}</span>
        </p>
        <p>
            <strong>Name:</strong>
            <span>Sirjon</span>
        </p>
        <p>
            <strong>Name:</strong>
            <span>Sirjon</span>
        </p>
    </div>

    <!-- Needed because other elements inside ProfilePage have floats -->
    <div style="clear:both"></div>
</div>
    </body>
</html>
