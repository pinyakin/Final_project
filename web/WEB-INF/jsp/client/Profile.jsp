<div class="mybody">
    <div class="myclass">
        <h2><fmt:message key="profile"/></h2>
        <form action="/profileupdate" class="form-horizontal" method="post">
            <div class="form-group">
                <label class="control-label col-xs-3" for="lastName"><fmt:message key="surname"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${client.lastName}" placeholder=<fmt:message key="input_surname"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="firstName"><fmt:message key="name"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="firstName" value="${client.firstName}" name="firstName" placeholder=<fmt:message key="input_name"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="fatherName"><fmt:message key="patronymic"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" value="${client.patronymic}" id="fatherName" name="fatherName" placeholder=<fmt:message key="input_patronymic"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="inputEmail">Email:</label>
                <div class="col-xs-9">
                    <input type="email" class="form-control" value="${client.email}" id="inputEmail" name="inputEmail" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="inputPassword"><fmt:message key="password"/>:</label>
                <div class="col-xs-9">
                    <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="<fmt:message key="input_new_password"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="phoneNumber"><fmt:message key="phone"/>:</label>
                <div class="col-xs-9">
                    <input type="tel" class="form-control" value="${client.tel}" id="phoneNumber" name="phoneNumber" placeholder=<fmt:message key="input_phone"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3"  for="postalAddress"><fmt:message key="address"/>:</label>
                <div class="col-xs-9">
                    <input rows="3" class="form-control" value="${client.address}" id="postalAddress" name="postalAddress" placeholder="<fmt:message key="input_address"/>"></input>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3"  for="bal"><fmt:message key="balance"/>:</label>
                <div class="col-xs-9">
                    <input class="form-control" value="${client.balance}" id="bal" name="bal"  readonly>
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input type="submit" class="btn btn-primary" value=<fmt:message key="save"/>>
                </div>
            </div>
        </form>
    </div>
</div>
