<%-- 
    Document   : visited
    Created on : Apr 12, 2018, 10:00:16 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is a temporary page! this information will probably be added to the user page.</h1>
        <p> ${user.getUsername()} has visited: </p>
        <c:forEach items="${visited}" var="checkpoint">
            <p> ${checkpoint.getName()} </p>
        </c:forEach>
    </body>
</html>
