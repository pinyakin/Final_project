<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 02.06.2016
  Time: 4:35
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
<div class=" myclass">
        <h2><fmt:message key="event"/> id${race.id}</h2>
        <form id="form1">
        <div class="form-group">
            <label class="control-label col-xs-3" for="racename"><fmt:message key="event"/>:</label>
            <div class="col-xs-9">
                <input type="text" class="form-control"  id="racename" name="racename" value="${race.racename}" >
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="date"><fmt:message key="date"/>:</label>
            <div class="col-xs-9">
                <input type="text" class="form-control"  id="date" name="date" value="${race.date}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="hippodrome"><fmt:message key="hippodrome"/>:</label>
            <div class="col-xs-9">
                <input type="text" class="form-control"  id="hippodrome" name="hippodrome" value="${race.ippodrom}">
            </div>
        </div>
            <c:if test="${date>(race.date)&& (race.first.equals('-')||race.second.equals('-')||race.third.equals('-'))}">
                <div class="form-group">
                    <label class="control-label col-xs-3" for="first">1st</label>
                    <div class="col-xs-9">
                        <select id="first" class="form-control" name="first" >
                            <option>-</option>
                            <c:forEach var="item" items="${list}">
                            <option>${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-3" for="second">2nd</label>
                    <div class="col-xs-9">
                        <select id="second" class="form-control" name="second" >
                            <option>-</option>
                            <c:forEach var="item" items="${list}">
                            <option>${item.name}</option>
                            </c:forEach>
                            </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-3" for="third">3rd</label>
                    <div class="col-xs-9">
                        <select id="third" class="form-control" name="third" >
                            <option>-</option>
                            <c:forEach var="item" items="${list}">
                            <option>${item.name}</option>
                            </c:forEach>
                            </select>
                    </div>
                </div>
            </c:if>
        <c:if test="${date<race.date||(!race.first.equals('-')&&!race.second.equals('-')&&!race.third.equals('-'))}">
            <h5><fmt:message key="notstart"/></h5>
        <div class="form-group">
            <label class="control-label col-xs-3" for="first">1st</label>
            <div class="col-xs-9">
                <input type="text" class="form-control" readonly  name="first" value="${race.first}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="second">2nd</label>
            <div class="col-xs-9">
                <input type="text" class="form-control" readonly   name="second" value="${race.second}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="third">3rd</label>
            <div class="col-xs-9">
                <input type="text" class="form-control" readonly  name="third" value="${race.third}">
            </div>
        </div>
        </c:if>
        </form>
        <div class="form-group form-horizontal">
            <div class="col-xs-offset-3 col-xs-9">
                <button form="form1" formmethod="post" formaction="/eventcontrol?update=${race.id}" name="save" class="btn btn-primary"><fmt:message key="save"/></button>
            </div>
        </div>
        <div class="form-group form-horizontal">
            <div class="col-xs-offset-3 col-xs-9">
                <button form="form1" formmethod="post" formaction="/deleteEvent?delete=${race.id}" name="delete" class="btn btn-primary" ><fmt:message key="delete"/></button>
            </div>
        </div>
            <div class="form-group form-horizontal">
                <div class="col-xs-offset-3 col-xs-9">
                    <%--<form action="/addpart?id=${race.id}">--%>
                    <a href="/addpart?id=${race.id}" name="addpart" class="btn btn-primary" ><fmt:message key="addpart"/></a>
                    <%--</form>--%>
                </div>
        </div>
        <c:if test="${list.size()!=0}">
        <h2><fmt:message key="participants"/></h2>
        <table class="table">
        <thead>
        <th><fmt:message key="num"/></th>
        <th><fmt:message key="name"/></th>
        </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <form action="/deletePart?id=${item.id}&race=${race}" method="post">
                    <tr onclick="location.href='/horsecontrol?id=${item.id}'">
                        <td>${list.indexOf(item)+1}</td>
                        <td>${item.name}</td>
                        <td><button type="submit" name="name" class="btn-link"><fmt:message key="delete"/> </button></td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </c:if>
</div>
</div>