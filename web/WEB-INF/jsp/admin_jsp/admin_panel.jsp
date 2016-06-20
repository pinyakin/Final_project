<%--
  Created by IntelliJ IDEA.

  Date: 23.05.2016
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class="topch">
        <c:if test="${i==2}">
            <div class="block0">
                <div class="nav-collapse block4" >
                    <ul class="nav nav-pillsR">
                        <li><a class="black" href="/addHorse"><fmt:message key="add"/></a></li>
                    </ul>
                </div>
            </div>
        </c:if>
        <c:if test="${i==1}">
            <div class="block0">
                <div class="nav-collapse block4" >
                    <ul class="nav nav-pillsR">
                        <li><a class="black" href="/Registration"><fmt:message key="add"/></a></li>
                    </ul>
                </div>
            </div>
        </c:if>
        <c:if test="${i==3}">
            <div class="block0">
                <div class="nav-collapse block4" >
                    <ul class="nav nav-pillsR">
                        <li><a class="black" href="/addEvent"><fmt:message key="add"/></a></li>
                    </ul>
                </div>
            </div>
        </c:if>
        <div class="block2">
            <table class="table">
                <thead>
                    <c:if test="${i==1}">
                        <th>id</th>
                        <th><fmt:message key="surname"/></th>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="patronymic"/></th>
                        <th>email</th>
                        <th><fmt:message key="phone"/></th>
                        <th><fmt:message key="balance"/></th>
                    </c:if>
                    <c:if test="${i==3}">
                        <th>id</th>
                        <th><fmt:message key="event"/></th>
                        <th><fmt:message key="date"/></th>
                        <th><fmt:message key="hippodrome"/></th>
                    </c:if>
                    <c:if test="${i==2}">
                        <th>id</th>
                        <th><fmt:message key="name"/></th>
                    </c:if>
                </thead>
                <tbody>
                <c:if test="${entity[0]!=null}">
                <c:if test="${i==1}">
                    <form method="post">
                <c:forEach var="item" items="${entity}">
                    <tr onclick="location.href='/profControle?id=${item.id}';">
                        <td>${item.id}</td>
                        <td>${item.lastName}</td>
                        <td>${item.firstName}</td>
                        <td>${item.patronymic}</td>
                        <td>${item.email}</td>
                        <td>${item.tel}</td>
                        <td>${item.balance}</td>
                    </tr>
                </c:forEach>
                    </form>
                </c:if>
                    <c:if test="${i==2}">
                        <c:forEach var="item" items="${entity}">
                            <tr onclick="location.href='/horsecontrol?id=${item.id}'">
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${i==3}">
                        <c:forEach var="item" items="${entity}">
                            <tr onclick="location.href='/eventcontrol?id=${item.id}'">
                                <td>${item.id}</td>
                                <td>${item.racename}</td>
                                <td>${item.date}</td>
                                <td>${item.ippodrom}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="block1">
            <div class="nav-collapse block4" >
                <ul class="nav nav-pillsR">
                    <li><a class="black" href="/adminPanel?i=1"><fmt:message key="clients"/></a></li>
                </ul>
            </div>
            <br>
            <div class="nav-collapse block5" >
                <ul class="nav nav-pillsR">
                    <li><a class="black" href="/adminPanel?i=2"><fmt:message key="horses"/></a></li>
                </ul>
            </div>
            <br>
            <div class="nav-collapse block6" >
                <ul class="nav nav-pillsR">
                    <li><a class="black" href="/adminPanel?i=3"><fmt:message key="events"/></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>