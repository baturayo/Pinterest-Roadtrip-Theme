<c:forEach var="notification" items="${requestScope.tripnotifications}">
    <p>${notification.getText()}</p>
</c:forEach>