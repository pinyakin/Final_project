<%@ page import="entity.Race" %>
<%@ page import="java.util.ArrayList" %>
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
<div>
    <img class="back" src="/image/back.jpg">
</div>
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
        <li class="pull-right">
            <ul class="nav pull-righ nav-pills">
              <li><a href="/page.jsp">Вход</a></li>
              <li class="divider-vertical"></li>
              <li> <ul class="nav pull-righ nav-pills">
                    <li><a href="#">EN</a></li>
                    <li><a href="#">RU</a></li>
                   </ul>
              </li>
            </ul>
        </li>
      </ul>
  </div>
</div>
</div>
</div>
<div class="text">
    <h5>Приумножай свои капиталы с нами. Регистрируйся и присоединяйся к первой сотне списка Forbes.</h5>
    <table class="table">
        <thead>
        <tr>
            <th>Дата</th>
            <th>Событие</th>
            <th>Ипподром</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="race_item" items="${races}">
        <tr>
            <td>${race_item.date}</td>
            <td>${race_item.racename}</td>
            <td>${race_item.ippodrom}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>