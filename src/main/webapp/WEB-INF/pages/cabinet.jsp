<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset='utf-8'>
    <title>Личный кабинет</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cabinet.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/cabinet.js"></script>
</head>

<body>
    <div id="header">
        <span id=title>
            Cars rent
        </span>
        <div id="buttons">
            <a href="#user">${username}</a>
            <a href="logout">ВЫЙТИ</a>
        </div>
    </div>

    <div class="row">
        <div class="sidebar">
            <div class="block">
                <div class="title">Меню</div>
                <a href="/mvc/cabinet/orders">Заказы</a>
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
                    <input type="radio" id="searchByBrand" name="searchBy" value="brand"><label for="searchByBrand">Бренд</label>
                    <input type="radio" id="searchByModel" name="searchBy" value="model"><label for="searchByModel">Модель</label>
                    <input type="radio" id="searchByYear" name="searchBy" value="year_made"><label for="searchByYear">Год</label>
                    <button>Поиск</button>
                </form>
            </div>

            <div class="block" id="orders" hidden>
                <table>
                    <tr>
                        <th>ID заказа</th>
                        <th>Автомобиль</th>
                        <th>Клиент</th>
                        <th>Статус</th>
                    </tr>
                    <tr onclick="" style="cursor: pointer">
                        <td>373</td>
                        <td>Hyundai Santa Fe 2016</td>
                        <td>Роман Костромин</td>
                        <td>Ожидает одобрения</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer">
                        <td>374</td>
                        <td>Opel Astra</td>
                        <td>Кир Меняйло</td>
                        <td>В прокате</td>
                    </tr>
                </table>

                <!-- <div class="buttons">
                    <a href="/addorder">Оформить автомобиль</a>
                </div> -->
            </div>

            <div class="block" id="users" hidden>
                <table>
                    <tr>
                        <th>Логин</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Статус</th>
                    </tr>
                    <c:forEach items="${userList}" var="user">
                        <tr>
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

            <div class="block" id="cars" hidden>
                <table>
                    <tr>
                        <th>ID автомобиля</th>
                        <th>Бренд</th>
                        <th>Модель</th>
                        <th>Статус</th>
                    </tr>
                    <!-- <tr onclick="" style="cursor: pointer">
                        <td>451</td>
                        <td>Hyundai</td>
                        <td>Santa Fe</td>
                        <td>Активен</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer">
                        <td>17</td>
                        <td>Opel</td>
                        <td>Astra</td>
                        <td>Активен</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer">
                        <td>32</td>
                        <td>Lexus</td>
                        <td>RX350</td>
                        <td>В ремонте</td>
                    </tr> -->
                    <c:forEach items="${carList}" var="car">
                        <tr>
                            <td>
                                <c:out value="${car.id}" />
                            </td>
                            <td>
                                <c:out value="${car.carSpecification.brand}" />
                            </td>
                            <td>
                                <c:out value="${car.carSpecification.model}" />
                            </td>
                            <td>
                                <c:out value="${car.carStatus}" />
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