<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <meta charset='utf-8'>
    <title>Регистрация</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/registration.js"></script>
</head>

<body>
    <div class="form">
        <div class="title">Регистрация</div>
        <form:form action="registration" method="POST" modelAttribute="user">
            <div class="fields-container">
                <label for="username">Логин</label>
                <form:input type="text" id="username" path="userName"/>
                <div id="username_check_response"></div>

                <label for="firstName">Имя</label>
                <form:input type="text" path="firstName"/>

                <label for="secondName">Фамилия</label>
                <form:input type="text" path="secondName"/>

                <label for="password">Пароль</label>
                <form:input type="password" id="password" path="password"/>
                <div id="password_error"></div>

                <label for="repeatPassword">Повторите пароль</label>
                <form:input type="password" id="checkPassword" path="matchingPassword"/>
                <div id="check_password_error"></div>
            </div>

            <div class="button-container">
                <button id="registerButton" type="submit" disabled="true">Зарегистрироваться</button>
            </div>
        </form:form>
    </div>
</body>

</html>