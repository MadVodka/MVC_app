<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset='utf-8'>
    <title>Заказ ID:${order.id}</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/info.css'>
</head>

<body>
    <c:choose>
        <c:when test="${empty order}">
            <div class="not-found">Заказ не найден</div>
        </c:when>
        <c:otherwise>
            <div class="entity-info">
                <div class="title">Заказ</div>
                <div class="info-block">
                    <span class="description-title">ID заказа:</span>
                    <span class="description-data">${order.id}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Информация о автомобиле:</span>
                    <span class="description-data">ID:${order.carInfo.carId} ${order.carInfo.brand}
                        ${order.carInfo.model}
                        ${order.carInfo.year}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Информация о клиенте:</span>
                    <span class="description-data">ID:${order.user.id} ${order.user.firstName}
                        ${order.user.secondName}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Начало аренды:</span>
                    <span class="description-data">${order.startDate}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Окончание аренды:</span>
                    <span class="description-data">${order.endDate}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Статус:</span>
                    <span class="description-data">${order.status}</span>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>

</html>