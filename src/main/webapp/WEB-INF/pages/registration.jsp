<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>

<head>
    <meta charset='utf-8'>
    <title>Регистрация</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css">
</head>

<body>
    <div class="form">
        <div class="title">Регистрация</div>
        <form action="">
            <div class="fields-container">
                <label for="username">Логин</label>
                <input type="text" name="username">

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