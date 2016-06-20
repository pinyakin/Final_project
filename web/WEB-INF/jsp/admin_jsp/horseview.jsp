<%--
  Created by IntelliJ IDEA.
  User: ww
  Date: 02.06.2016
  Time: 4:35
  To change this template use File | Settings | File Templates.
--%>
<div class="mybody">
    <div class="myclass">
        <form id="form1" method="post" action="/horsecontrol?update=${horse.id}">
        <h2><fmt:message key="horse"/> id${horse.id}</h2>
        <div class="form-group">
            <label class="control-label col-xs-3" for="name"><fmt:message key="name"/>:</label>
            <div class="col-xs-9">
                <input type="text" class="form-control"  id="name" name="name" value="${horse.name}" placeholder=<fmt:message key="input_name"/>>
            </div>
        </div>
        </form>
        <div class="form-group form-horizontal">
            <div class="col-xs-offset-3 col-xs-9">
                <button form="form1" type="submit" name="save" class="btn btn-primary"><fmt:message key="save"/></button>
            </div>
        </div>
        <form action="/horsecontrol?delete=${horse.id}" method="post">
        <div class="form-group form-horizontal">
            <div class="col-xs-offset-3 col-xs-9">
                <button type="submit" name="delete" class="btn btn-primary" ><fmt:message key="delete"/></button>
            </div>
        </div>
        </form>
    </div>
</div>