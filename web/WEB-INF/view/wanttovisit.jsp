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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is a temporary page! this information will probably be added to the user page.</h1>
        <p> ${user.getUsername()} wants to visit </p>
        <c:forEach items="${wanttovisit}" var="checkpoint">
            <p> ${checkpoint.getName()} </p>
        </c:forEach>
    </body>
</html>
