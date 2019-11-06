<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>

<head>
    <meta charset='utf-8'>
    <title>Авторизация</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css">
</head>

<body>
    <div class="form">
        <div class="title">Вход</div>
        <form action="">
            <div class="fields-container">
                <label for="username">Логин</label>
                <input type="text" name="username">

                <label for="password">Пароль</label>
                <input type="password" name="password">
            </div>

            <div class="button-container">
                <button type="submit">Войти</button>
            </div>

            <div id="lower-container">
                <span>Еще не зарегистрированы?</span>
                <a href="registration">Регистрация</a>
            </div>
        </form>
    </div>
</body>

</html>