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
            <a href="#user">${username}</a>
            <a href="/mvc/logout">ВЫЙТИ</a>
        </div>
    </div>

    <div class="row">
        <div class="sidebar">
            <div class="block">
                <div class="title">Меню</div>
                <a class="active">Заказы</a>
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="/mvc/cabinet/users">Пользователи</a>
                    <a href="/mvc/cabinet/cars">Автомобили</a>
                </sec:authorize>
            </div>
        </div>

        <div class="content">

            <div id="search">
                <form action="/mvc/cabinet/cars/search" >
                    <input type="text" name="text" placeholder="Вводите текст">
                    <input type="radio" id="searchById" name="searchBy" value="id"><label for="searchById">Id заказа</label>
                    <input type="radio" id="searchByCar" name="searchBy" value="car"><label for="searchByCar">Автомобиль</label>
                    <button>Поиск</button>
                </form>
            </div>

            <div class="block" id="orders">
                <table>
                    <tr>
                        <th>ID заказа</th>
                        <th>Автомобиль</th>
                        <th>Клиент</th>
                        <th>Статус</th>
                    </tr>
                    <c:forEach items="${orderList}" var="order">
                        <tr onclick="document.location = '/mvc/order?id=${order.id}';">
                            <td>
                                <c:out value="${order.id}" />
                            </td>
                            <td>
                                <c:out value="${order.carInfo.brand} ${order.carInfo.model} ${order.carInfo.year}" />
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

                <div class="buttons">
                    <a href="/addorder">Оформить автомобиль</a>
                </div>
            </div>

            <div id="pagination">
                    <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPage != 1}">
                        <td><a href="${sectionUrl}${currentPage - 1}">Previous</a></td>
                    </c:if>
                 
                    <%--For displaying Page numbers. 
                    The when condition does not display a link for the current page--%>
                    <table border="1" cellpadding="5" cellspacing="5">
                        <tr>
                            <c:forEach begin="1" end="${numberOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="${sectionUrl}${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>
                     
                    <%--For displaying Next link --%>
                    <c:if test="${currentPage lt numberOfPages}">
                        <td><a href="${sectionUrl}${currentPage + 1}">Next</a></td>
                    </c:if>
            </div>

        </div>
    </div>
</body>

</html>