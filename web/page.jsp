<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <style>
        <%@include file='/bootstrap/css/bootstrap.css' %>
        <%@include file='/css/mycss.css' %>
    </style>
</head>
<body class="mybody">
<jsp:useBean id="loginpass" class="shared.LoginPass" type="java.lang.Object" scope="request"/>
<c:if test="${loginpass.login!=null}">
    ${loginpass.login}
</c:if>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container ">
            <a class="btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="nav-collapse">
                <ul class="nav nav-pills">
                    <li class="active"><a href="/index.jsp">Главная</a></li>
                    <li><a href="#">Скачки</a></li>
                    <li><a href="#">Стратегии</a></li>
                    <li><a href="#">История</a></li>
                    <li><a href="#">Профиль</a></li>
                    <li><a href="/registration.jsp">Регистрация</a></li>
                    <li class="pull-right"> <ul class="nav pull-righ nav-pills">
                        <li><a href="/page.jsp">Вход</a></li>
                        <li class="divider-vertical"></li>
                        <li> <ul class="nav pull-righ nav-pills">
                            <li><a href="#">EN</a></li>
                            <li><a href="#">RU</a></li>
                        </ul>
                        </li>
                    </ul></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="alert alert-warning  myclassformt">
<form action="LoginPassServlet" class="form-horizontal" method="post">
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Email</label>
        <div class="col-xs-10">
            <input type="text" class="form-control" name="login" id="inputEmail" placeholder="Введите login">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2">Пароль</label>
        <div class="col-xs-10">
            <input type="text" class="form-control" name="password" id="inputPassword" placeholder="Пароль">
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
            <button type="submit" class="btn btn-primary">Войти</button>
        </div>
    </div>
</form>
</div>
</body>
</html>
<%--
--%>
