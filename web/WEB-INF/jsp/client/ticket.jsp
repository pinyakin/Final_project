<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 09.06.2016
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class=" myclass">
        <h2><fmt:message key="event"/> id${race.id}</h2>
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
            <form action="/ticket?id=${part.id}&type=${type}" method="post">
            <table class="table">
                <thead>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="coef"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="sum"/></th>
                </thead>
                <tbody>
                    <tr >
                        <td>${part.name}</td>
                        <c:if test="${type.equals('winB')}">
                            <td>${part.winB}</td>
                            <td>Win(Back)</td>
                        </c:if>
                        <c:if test="${type.equals('winL')}">
                            <td>${part.winL}</td>
                            <td>Win(Lay)</td>
                        </c:if>
                        <c:if test="${type.equals('place')}">
                            <td>${part.place}</td>
                            <td>Place</td>
                        </c:if>
                        <c:if test="${type.equals('show')}">
                            <td>${part.show}</td>
                            <td>Show</td>
                        </c:if>
                        <td><input type="number" step="0.01" min="0" name="money"/></td>
                        <td><button type="submit" name="name" class="btn-link"><fmt:message key="confirm"/> </button></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>

