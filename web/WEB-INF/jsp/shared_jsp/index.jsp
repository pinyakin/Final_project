<div class="mybody">
<div>
    <img class="back" src="/image/back.jpg">
</div>
<div class="text onlytop">
    <h4><fmt:message key="hello"/></h4>
    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="date"/></th>
            <th><fmt:message key="event"/></th>
            <th><fmt:message key="hippodrome"/></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="race_item" items="${races}">
        <tr onclick="location.href='/eventcontrol?id=${race_item.id}'">
            <td>${race_item.date}</td>
            <td>${race_item.racename}</td>
            <td>${race_item.ippodrom}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</body>
</html>