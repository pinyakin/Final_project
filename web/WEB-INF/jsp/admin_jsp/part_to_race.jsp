<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 03.06.2016
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.

  Date: 23.05.2016
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class="topch">
        <div class="block2">
            <table class="table"><thead>
                    <th>id</th>
                    <th><fmt:message key="name"/></th>
                </thead>
                <tbody>
                <c:if test="${list.size()!=0}">
                        <c:forEach var="item" items="${list}">
                            <form action="/addpart?id=${item.id}&race=${race}" method="post">
                            <tr onclick="location.href='/horseview?id=${item.id}'">
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td><button type="submit" name="name" class="btn-link"><fmt:message key="add"/> </button></td>
                            </tr>
                            </form>
                        </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>