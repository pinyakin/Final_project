<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 09.06.2016
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class=" myclass">
        <h2><fmt:message key="event"/> id${race.id}</h2>
        <form id="form1">
            <div class="form-group">
                <label class="control-label col-xs-3" for="racename"><fmt:message key="event"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" readonly  id="racename" name="racename" value="${race.racename}" >
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="date"><fmt:message key="date"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" readonly  id="date" name="date" value="${race.date}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="hippodrome"><fmt:message key="hippodrome"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" readonly  id="hippodrome" name="hippodrome" value="${race.ippodrom}">
                </div>
            </div>
                <div class="form-group">
                    <label class="control-label col-xs-3" for="first">1st</label>
                    <div class="col-xs-9">
                        <input type="text" id="first" class="form-control" readonly  name="first" value="${race.first}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-3" for="second">2nd</label>
                    <div class="col-xs-9">
                        <input type="text" id="second" class="form-control" readonly   name="second" value="${race.second}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-3" for="third">3rd</label>
                    <div class="col-xs-9">
                        <input type="text" id="third" class="form-control" readonly  name="third" value="${race.third}">
                    </div>
                </div>
        </form>
        <c:if test="${partList.size()<4}">
            <h2><fmt:message key="textAboutPart"/></h2>
            </c:if>
        <c:if test="${partList.size()>=4}">
        <h2><fmt:message key="participants"/></h2>
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
            <c:forEach var="item" items="${partList}">
                    <tr >
                        <td>${partList.indexOf(item)+1}</td>
                        <td>${item.name}</td>
                        <td><a href="/ticket?id=${item.id}&type=winB">${item.winB}</a></td>
                        <td><a href="/ticket?id=${item.id}&type=winL">${item.winL}</a></td>
                        <td><a href="/ticket?id=${item.id}&type=place">${item.place}</a></td>
                        <td><a href="/ticket?id=${item.id}&type=show">${item.show}</a></td>
                    </tr>
            </c:forEach>
            </tbody>
            </c:if>
        </table>
    </div>
</div>
