<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset='utf-8'>
    <title>Личный кабинет - Пользователи - Cтраница ${currentPage}</title>
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
                <a>
                    <sec:authentication property="principal.username" /></a>
            </sec:authorize>
            <a href="${pageContext.request.contextPath}/logout">ВЫЙТИ</a>
        </div>
    </div>

    <div class="row">
        <div class="sidebar">
            <div class="block">
                <div class="title">Меню</div>
                <a href="${pageContext.request.contextPath}/cabinet/orders">Заказы</a>
                <sec:authorize access="hasRole('ADMIN')">
                    <a class="active">Пользователи</a>
                    <a href="${pageContext.request.contextPath}/cabinet/cars">Автомобили</a>
                </sec:authorize>
            </div>
        </div>

        <div class="content">

            <div id="search">
                <form action="${pageContext.request.contextPath}/cabinet/users/search">
                    <input type="text" name="text" placeholder="Вводите текст">
                    <input type="radio" id="searchById" name="searchBy" value="id"><label for="searchById">Id
                        пользователя</label>
                    <input type="radio" id="searchByUserName" name="searchBy" value="user_name"><label
                        for="searchByUserName">Username</label>
                    <input type="radio" id="searchByName" name="searchBy" value="first_name"><label
                        for="searchByName">Имя</label>
                    <input type="radio" id="searchBySecondName" name="searchBy" value="second_name"><label
                        for="searchBySecondName">Фамилия</label>
                    <button>Поиск</button>
                </form>
            </div>

            <div class="block" id="users">
                <table>
                    <tr>
                        <th>Логин</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Статус</th>
                    </tr>
                    <c:forEach items="${userList}" var="user">
                        <tr onclick="document.location = '${pageContext.request.contextPath}/user?id=${user.id}';">
                            <td>
                                <c:out value="${user.userName}" />
                            </td>
                            <td>
                                <c:out value="${user.firstName}" />
                            </td>
                            <td>
                                <c:out value="${user.secondName}" />
                            </td>
                            <td>
                                <c:out value="${user.userStatus}" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>
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