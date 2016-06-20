<jsp:useBean id="messageSignIn" scope="request"
             class="java.lang.String"/>

<c:if test="${!messageSignIn.equals('')}">
    <h1><fmt:message key="${messageSignIn}"/></h1>
</c:if>
<br><br>
<div class="mybody">
<div class="alert alert-warning  myclassformt">
<form action="/Login" class="form-horizontal" method="post">
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Email</label>
        <div class="col-xs-10">
            <input type="text" class="form-control" name="email" id="inputEmail" placeholder=<fmt:message key="input_login"/>>
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2"><fmt:message key="password"/></label>
        <div class="col-xs-10">
            <input type="password" class="form-control" name="password" id="inputPassword" placeholder=<fmt:message key="password"/>>
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
            <button type="submit" class="btn btn-primary"><fmt:message key="signin"/></button>
        </div>
    </div>
</form>
</div>
</div>
<%--
--%>
