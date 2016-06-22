<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 02.06.2016
  Time: 4:21
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="messageNewEvent" scope="request"
             class="java.lang.String"/>
<div class="mybody">
    <div class="myclass">
        <c:if test="${!messageNewEvent.equals('')}">
            <h1><fmt:message key="${messageNewEvent}"/></h1>
        </c:if>
        <h2><fmt:message key="registration"/></h2>
        <form action="/addEvent" class="form-horizontal" method="post">
            <div class="form-group">
                <label class="control-label col-xs-3" for="event"><fmt:message key="event"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="event" name="event" placeholder=<fmt:message key="event"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3"><fmt:message key="day"/>:</label>
                <div class="col-xs-3">
                    <input name="date" type="date" id="date" class="form-control">
                </div>
                <div class="col-xs-3">
                    <label class="control-label col-xs-3" for="time"><fmt:message key="time"/>:</label>
                    <div class="col-xs-9">
                        <input type="time" class="form-control" id="time" name="time">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="ippodrom"><fmt:message key="hippodrome"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="ippodrom" name="ippodrom" placeholder=<fmt:message key="hippodrome"/>>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input type="submit" class="btn btn-primary" value=<fmt:message key="registration"/>>
                    <input type="reset" class="btn btn-default" value=<fmt:message key="clean_form"/>>
                </div>
            </div>
        </form>
    </div>
</div>
