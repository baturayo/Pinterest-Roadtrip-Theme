<%-- 
    Document   : userPage
    Created on : Apr 5, 2018, 10:40:07 PM
    Author     : baturay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <a href=${requestScope.url}>Name Surname: ${requestScope.name_surname}</a>
    <c:forEach items="${searchList}" var="searchItem">
        ${item}<br>
    </c:forEach>
</html>
