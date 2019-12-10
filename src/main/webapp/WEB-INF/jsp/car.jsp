<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset='utf-8'>
    <title>Автомобиль ID:${car.id}</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/info.css">
</head>

<body>
    <c:choose>
        <c:when test="${empty car}">
            <div class="not-found">Автомобиль не найден</div>
        </c:when>
        <c:otherwise>
            <div class="entity-info">
                <div class="title">Автомобиль</div>
                <div class="info-block">
                    <span class="description-title">ID автомобиля:</span>
                    <span class="description-data">${car.id}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Марка:</span>
                    <span class="description-data">${car.carSpecification.brand}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Модель:</span>
                    <span class="description-data">${car.carSpecification.model}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Год выпуска:</span>
                    <span class="description-data">${car.carSpecification.yearMade}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Стоимость сутки:</span>
                    <span class="description-data">${car.pricePerDay}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Регистрационный номер:</span>
                    <span class="description-data">${car.registrationNumber}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Статус:</span>
                    <span class="description-data">${car.carStatus}</span>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>

</html>