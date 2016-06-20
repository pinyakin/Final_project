<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 07.06.2016
  Time: 4:08
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 02.06.2016
  Time: 4:35
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class=" myclass">
        <h3>id${race.id}</h3>
        <h3>${race.racename}</h3>
        <h3><fmt:message key="date"/> ${race.date}</h3>
        <h3><fmt:message key="hippodrome"/> ${race.ippodrom}</h3>
        <table class="table">
            <thead>
            <th><fmt:message key="num"/></th>
            <th><fmt:message key="name"/></th>
            <th>Win(Back)</th>
            <th>Win(Lay)</th>
            <th>Place</th>
            <th>Show</th>
            </thead>
            <tbody>
            <c:forEach  var="item" items="${partList}" >
                    </tr>
                <form action="/setCoeff?race=${item.idrace}&horse=${item.idhorse}" method="post">
                    <tr>
                        <td>${partList.indexOf(item)+1}</td>
                        <td>${item.name}</td>
                        <td><input value="${item.winB}" min="1" type="number" step="0.01" name="winB"/></td>
                        <td><input value="${item.winL}" min="1" type="number" step="0.01" name="winL"/></td>
                        <td><input value="${item.place}"min="1" type="number" step="0.01" name="place"/></td>
                        <td><input value="${item.show}" min="1" type="number" step="0.01" name="showbet"/></td>
                        <td><button type="submit" name="name" class="btn-link"><fmt:message key="confirm"/> </button></td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
            </table>
    </div>
</div>
