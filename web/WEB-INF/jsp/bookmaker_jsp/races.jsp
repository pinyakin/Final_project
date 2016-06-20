<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 07.06.2016
  Time: 4:03
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class="topch">
        <div class="block2">
            <table class="table">
                <thead>
                    <th><fmt:message key="date"/></th>
                    <th><fmt:message key="event"/></th>
                    <th><fmt:message key="hippodrome"/></th>
                </thead>
                <tbody>
                <c:forEach var="race_item" items="${races}">
                    <tr onclick="location.href='/setCoeff?id=${race_item.id}'">
                        <td>${race_item.date}</td>
                        <td>${race_item.racename}</td>
                        <td>${race_item.ippodrom}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="block1">
            <div class="nav-collapse block4" >
                <ul class="nav nav-pillsR">
                    <li><a class="black" href="/bookmakerPanel?time=day"><fmt:message key="forday"/></a></li>
                </ul>
            </div>
            <br>
            <div class="nav-collapse block5" >
                <ul class="nav nav-pillsR">
                    <li><a class="black" href="/bookmakerPanel?time=week"><fmt:message key="forweek"/></a></li>
                </ul>
            </div>
            <br>
            <div class="nav-collapse block6" >
                <ul class="nav nav-pillsR">
                    <li><a class="black" href="/bookmakerPanel?time=month"><fmt:message key="formonth"/></a></li>
                </ul>
            </div>
            <br>
            <div class="nav-collapse block6" >
                <ul class="nav nav-pillsR">
                    <li><a class="black" href="/bookmakerPanel?time=all"><fmt:message key="all"/></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>