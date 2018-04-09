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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>

        <title>Checkpoint Page</title>
    </head>
    <body>
        <h1>Welcome to the checkpoint page!</h1>
        <p>Name: ${requestScope.Checkpoint.getName()}</p>
        <p>Description: ${requestScope.Checkpoint.getDescription()}</p>
        <p>X-coordinate: ${requestScope.Checkpoint.getX()}</p>
        <p>Y-coordinate ${requestScope.Checkpoint.getY()}</p>
        
    <c:forEach begin="0" end="2" var="photo">
        <p>"${requestScope.fotos[photo].getUrl()}"</p>
        <p> aa </p>
    </c:forEach>
        
        
  
    </body>
</html>
