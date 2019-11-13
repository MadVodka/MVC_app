<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset='utf-8'>
    <title>Пользователь ID:${user.id}</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/info.css'>
</head>

<body>

    <c:choose>
        <c:when test="${empty user}">
            <div class="not-found">Пользователь не найден</div>
        </c:when>
        <c:otherwise>
            <div class="entity-info">
                <div class="title">Пользователь</div>
                <div class="info-block">
                    <span class="description-title">ID пользователя:</span>
                    <span class="description-data">${user.id}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Имя:</span>
                    <span class="description-data">${user.firstName}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Фамилия:</span>
                    <span class="description-data">${user.secondName}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Username:</span>
                    <span class="description-data">${user.userName}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Статус:</span>
                    <span class="description-data">${user.userStatus}</span>
                </div>
                <div class="info-block">
                    <span class="description-title">Дополнительная информация:</span>
                    <span class="description-data">---</span>
                </div>
            </div>
        </c:otherwise>
    </c:choose>


</body>

</html>