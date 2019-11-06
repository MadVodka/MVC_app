<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>Оформление автомобиля</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='css/order_form.css'>
</head>

<body>
    <div class="form">
        <div id="title">Оформление автомобиля</div>
        <form action="">
            <div class="fields-container">
                <label for="auto">Выберите автомобиль</label>
                <input type="text" name="auto">

                <label for="startDate">Начало использования</label>
                <input type="date" name="startDate">

                <label for="endDate">Окончание использования</label>
                <input type="date" name="endDate">
            </div>

            <div class="button-container">
                <button type="submit">Оформить</button>
            </div>
        </form>
    </div>
</body>

</html>