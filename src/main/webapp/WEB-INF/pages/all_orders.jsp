<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset='utf-8'>
    <title>Личный кабинет - Заказы - Cтраница ${currentPage}</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cabinet.css">
</head>

<body>
    <div id="header">
        <span id=title>
            Cars rent
        </span>
        <div id="buttons">
            <sec:authorize access="isAuthenticated()">
                <a><sec:authentication property="principal.username" /></a>
            </sec:authorize>
            <a href="${pageContext.request.contextPath}/logout">ВЫЙТИ</a>
        </div>
    </div>

    <div class="row">
        <div class="sidebar">
            <div class="block">
                <div class="title">Меню</div>
                <a class="active">Заказы</a>
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="${pageContext.request.contextPath}/cabinet/users">Пользователи</a>
                    <a href="${pageContext.request.contextPath}/cabinet/cars">Автомобили</a>
                </sec:authorize>
            </div>
        </div>

        <div class="content">
            <div class="block" id="orders">
                <table>
                    <tr>
                        <th>ID заказа</th>
                        <th>Автомобиль</th>
                        <th>Клиент</th>
                        <th>Статус</th>
                    </tr>
                    <c:forEach items="${orderList}" var="order">
                        <tr onclick="document.location = '${pageContext.request.contextPath}/order?id=${order.id}';">
                            <td>
                                <c:out value="${order.id}" />
                            </td>
                            <td>
                                <c:out value="${order.car.carSpecification.brand} ${order.car.carSpecification.model} ${order.car.carSpecification.yearMade}" />
                            </td>
                            <td>
                                <c:out value="${order.user.firstName} ${order.user.secondName}" />
                             </td>
                            <td>
                                <c:out value="${order.status}" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <sec:authorize access="hasRole('USER')">
                    <div class="buttons">
                        <a href="${pageContext.request.contextPath}/add_order">Оформить автомобиль</a>
                    </div>
                </sec:authorize>
            </div>
        </div>
    </div>
</body>

</html>