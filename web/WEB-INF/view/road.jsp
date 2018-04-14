<%-- 
    Document   : road
    Created on : Apr 14, 2018, 11:03:18 AM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>${requestScope.road.getName()}</h1>
<p>${requestScope.road.getDescription()}</p>
<p> contains:</p>

<c:forEach items="${requestScope.checkpoints}" var="checkpoint">
    <p>
    <a href="/RoadTrip/checkpoint?id=${checkpoint.getId()}">${checkpoint.getName()}</a>
    </p>
</c:forEach>