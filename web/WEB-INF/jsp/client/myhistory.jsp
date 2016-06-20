<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 05.06.2016
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class="topch">
        <div class="block222">
            <table class="table">
                <thead>
                <tr>
                    <th>id</th>
                    <th><fmt:message key="event"/></th>
                    <th><fmt:message key="date"/></th>
                    <th><fmt:message key="horse"/></th>
                    <th><fmt:message key="bet"/></th>
                    <th><fmt:message key="coef"/></th>
                    <th><fmt:message key="win"/></th>
                    <th><fmt:message key="type"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${raceName.get(list.indexOf(item))}</td>
                        <td>${item.date}</td>
                        <td>${item.horse}</td>
                        <td>${item.money}</td>
                        <td>${item.coefficient}</td>
                        <td>${item.win}</td>
                        <td>${item.typeBet}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>
