<jsp:useBean id="messageRegistration" scope="request"
             class="java.lang.String"/>
<div class="mybody">
    <div class="myclass">

    <c:if test="${!messageRegistration.equals('')}">
        <h1><fmt:message key="${messageRegistration}"/></h1>
    </c:if>
<h2><fmt:message key="registration"/></h2>
<form action="/Registration" class="form-horizontal" method="post">
    <div class="form-group">
        <label class="control-label col-xs-3" for="lastName"><fmt:message key="surname"/>:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder=<fmt:message key="input_surname"/>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="firstName"><fmt:message key="name"/>:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder=<fmt:message key="input_name"/>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="fatherName"><fmt:message key="patronymic"/>:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="fatherName" name="fatherName" placeholder=<fmt:message key="input_patronymic"/>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3"><fmt:message key="birthday"/>:</label>
        <div class="col-xs-3">
              <input type="date" name="date">
         </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputEmail">Email:</label>
        <div class="col-xs-9">
            <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputPassword"><fmt:message key="password"/>:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="<fmt:message key="input_password"/>">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="phoneNumber"><fmt:message key="phone"/>:</label>
        <div class="col-xs-9">
            <input type="tel" pattern="\8\([0-9]{3}\)[0-9]{3}-[0-9]{2}-[0-9]{2}" class="form-control" id="phoneNumber" name="phoneNumber" placeholder=<fmt:message key="input_phone"/>>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="postalAddress"><fmt:message key="address"/>:</label>
        <div class="col-xs-9">
            <textarea rows="3" class="form-control" id="postalAddress" name="postalAddress" placeholder="<fmt:message key="input_address"/>"></textarea>
        </div>
    </div>
    <br />
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <input type="submit" class="btn btn-primary" value=<fmt:message key="registration"/>>
            <input type="reset" class="btn btn-default" value=<fmt:message key="clean_form"/>>
        </div>
    </div>
</form>
</div>
</div>
