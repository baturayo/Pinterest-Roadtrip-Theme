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
        <p>URL: ${requestScope.uri}</p>
        <p>Contextpath: ${requestScope.context}</p>
        <p>ServletPath: ${requestScope.servlet}</p>
        <p>Pathinfo: ${requestScope.path}</p>
        <p>QueryString: ${requestScope.query}</p>


    </body>
</html>
