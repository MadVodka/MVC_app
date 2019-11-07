<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

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
            <a href="#user">USERNAME</a>
            <a href="logout">ВЫЙТИ</a>
        </div>
    </div>

    <div class="row">
        <div class="sidebar">
            <div class="block">
                <div class="title">Меню</div>
                <a href="#orders">Заказы</a>
                <a href="#users" class="active">Пользователи</a>
                <a href="#cars">Автомобили</a>
            </div>

        </div>

        <div class="content">
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

                <div class="buttons">
                    <a href="/addorder">Оформить автомобиль</a>
                </div>
            </div>

            <div class="block" id="users">
                <table>
                    <tr>
                        <th>Логин</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Статус</th>
                    </tr>
                    <tr onclick="" style="cursor: pointer">
                        <td>golem</td>
                        <td>Порфирий</td>
                        <td>Гогуа</td>
                        <td>Активен</td>
                    </tr>
                </table>
            </div>

            <div class="block" id="cars" hidden>
                <table>
                    <tr>
                        <th>ID автомобиля</th>
                        <th>Модель</th>
                        <th>Бренд</th>
                        <th>Статус</th>
                    </tr>
                    <tr onclick="" style="cursor: pointer">
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
                    </tr>
                </table>
            </div>

        </div>
    </div>
</body>

</html>