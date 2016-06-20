<div class="mybody">
    <div class="myclass">
        <h2><fmt:message key="profile"/> id${client.id}</h2>
            <div class="form-group">
                <label class="control-label col-xs-3" for="lastName"><fmt:message key="surname"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" readonly id="lastName" name="lastName" value="${client.lastName}" placeholder=<fmt:message key="input_surname"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="firstName"><fmt:message key="name"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" readonly id="firstName" value="${client.firstName}" name="firstName" placeholder=<fmt:message key="input_name"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="fatherName"><fmt:message key="patronymic"/>:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" readonly value="${client.patronymic}" id="fatherName" name="fatherName" placeholder=<fmt:message key="input_patronymic"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="inputEmail">Email:</label>
                <div class="col-xs-9">
                    <input type="email" class="form-control" readonly value="${client.email}" id="inputEmail" name="inputEmail" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="phoneNumber"><fmt:message key="phone"/>:</label>
                <div class="col-xs-9">
                    <input type="tel" class="form-control" readonly value="${client.tel}" id="phoneNumber" name="phoneNumber" placeholder=<fmt:message key="input_phone"/>>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3"  for="postalAddress"><fmt:message key="address"/>:</label>
                <div class="col-xs-9">
                    <input rows="3" class="form-control" value="${client.address}" id="postalAddress" readonly name="postalAddress" placeholder="<fmt:message key="input_address"/>"></input>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3"  for="bal"><fmt:message key="balance"/>:</label>
                <div class="col-xs-9">
                    <input class="form-control" value="${client.balance}" id="bal" name="bal"  readonly>
                </div>
            </div>
            <br />
            <form action="/profControle?id=${client.id}" method="post">
                <div class="form-group">
                    <label class="control-label col-xs-3" for="money"><fmt:message key="sum"/>:</label>
                    <div class="col-xs-9">
                        <input type="number" step="0.01" min="0" class="form-control" id="money" name="money">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-3" for="role"><fmt:message key="role"/>:</label>
                    <div class="col-xs-9">
                        <select name="role" id="role">
                            <c:if test="${client.role.equals('client')}">
                            <option value="client"><fmt:message key="client"/></option>
                            <option value="admin"><fmt:message key="admin"/></option>
                            <option value="bookmaker"><fmt:message key="bookmaker"/></option>
                            </c:if>
                            <c:if test="${client.role.equals('bookmaker')}">
                                <option value="bookmaker"><fmt:message key="bookmaker"/></option>
                                <option value="client"><fmt:message key="client"/></option>
                                <option value="admin"><fmt:message key="admin"/></option>
                            </c:if>
                            <c:if test="${client.role.equals('admin')}">
                                <option value="admin"><fmt:message key="admin"/></option>
                                <option value="client"><fmt:message key="client"/></option>
                                <option value="bookmaker"><fmt:message key="bookmaker"/></option>
                            </c:if>
                            </select>
                        </select>
                    </div>
                </div>
            <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-9">
                        <input type="submit"  class="btn btn-primary" value=<fmt:message key="put"/>>
                    </div>
            </div>
        </form>
        <div class="form-group form-horizontal">
            <div class="col-xs-offset-3 col-xs-9">
                <button href="/delete_client?id=${client.id}" name="delete" class="btn btn-primary"><fmt:message key="delete"/></button>
            </div>
        </div>
    </div>
</div>
