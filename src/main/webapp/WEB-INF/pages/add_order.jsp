<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset='utf-8'>
    <title>Оформление автомобиля</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order_form.css">
</head>

<body>
    <div class="form">
        <div id="title">Оформление автомобиля</div>
        <form:form action="add_order/process" method="POST" modelAttribute="order">
            <div class="fields-container">
                <label for="cars">Выберите автомобиль</label>
                <form:select id="cars" path="carId">
                    <c:forEach items="${cars}" var="car">
                        <form:option value="${car.id}">${car.carSpecification.brand} ${car.carSpecification.model}
                            ${car.carSpecification.yearMade} (${car.pricePerDay})</form:option>
                    </c:forEach>
                </form:select>

                <label for="startDate">Начало использования</label>
                <form:input id="startDate" type="date" path="startDate" />

                <label for="endDate">Окончание использования</label>
                <form:input id="endDate" type="date" path="endDate" />
            </div>

            <div class="button-container">
                <button type="submit">Оформить</button>
            </div>
        </form:form>
    </div>
</body>

</html>