<%-- 
    Document   : checkpoint
    Created on : Apr 6, 2018, 1:25:52 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkpoint Page</title>
    </head>
    <body>
        <h1>Welcome to the checkpoint page!</h1>
        <p>Name: ${requestScope.Checkpoint.getName()}</p>
        <p>Description: ${requestScope.Checkpoint.getDescription()}</p>
        <p>X-coordinate: ${requestScope.Checkpoint.getX()}</p>
        <p>Y-coordinate ${requestScope.Checkpoint.getY()}</p>
    </body>
</html>
