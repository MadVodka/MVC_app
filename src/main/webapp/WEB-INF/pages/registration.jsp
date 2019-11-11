<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

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
        <form action="registration">
            <div class="fields-container">
                <label for="username">Логин</label>
                <input type="text" id="username" name="username">
                <div id="username_check_response"></div>

                <label for="firstName">Имя</label>
                <input type="text" name="firstName">

                <label for="secondName">Фамилия</label>
                <input type="text" name="secondName">

                <label for="password">Пароль</label>
                <input type="password" name="password">

                <label for="repeatPassword">Повторите пароль</label>
                <input type="password" name="repeatPassword">
            </div>

            <div class="button-container">
                <button type="submit">Зарегистрироваться</button>
            </div>
        </form>
    </div>
</body>

</html>