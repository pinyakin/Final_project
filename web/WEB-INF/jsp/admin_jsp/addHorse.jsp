<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 02.06.2016
  Time: 3:25
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="messageNewHorse" scope="request"
             class="java.lang.String"/>
<div class="mybody">
    <div class="myclass">
        <c:if test="${!messageNewHorse.equals('')}">
            <h1><fmt:message key="${messageNewHorse}"/></h1>
        </c:if>
        <h2><fmt:message key="registration"/></h2>
        <form action="/addHorse" class="form-horizontal" method="post">
            <div class="form-group">
                <label class="control-label col-xs-3" for="name"><fmt:message key="name"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="name" name="name" placeholder=<fmt:message key="input_horse"/>>
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