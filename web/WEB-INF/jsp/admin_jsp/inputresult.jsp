<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 05.06.2016
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class="topch">
        <div class="block2">
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
    </div>
