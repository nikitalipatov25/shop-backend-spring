<template>
    <div class="personal-area">
        <h1>{{ text }}</h1>
            <div class="body">
                <div class="row">
                    <div class="col-2">
                        <div class="nav-left">
                            <ul class="list-group">
                                <li class="list-group-item active" aria-current="true">Профиль</li>
                                <li class="list-group-item">Заказы</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="personal-data">
                            <h2>Профиль</h2>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="personal-data-firstcol">
                            <p>ФИО: Иванов Иван Иванович</p>
                            <p>Телефон: 8(123)456-78-90</p>
                            <p>Адрес: Ул. Пушкина, д. №1, кв. 2</p>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="personal-data-secondcol">
                            <p>Эл. почта: test@mail.ru</p>
                            <p>Роль: покупатель</p>
                            <a href>Редактировать</a>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="orders">
                    <div class="row">
                        <div class="col-4">
                            <h2>Заказы</h2>
                        </div>
                        <div class="col-8">
                            <ul class="list-group list-group-horizontal">
                                <li class="list-group-item">Номер заказа</li>
                                <li class="list-group-item">Итоговая стоимость</li>
                                <li class="list-group-item">Тип заказа</li>
                                <li class="list-group-item">Статус заказа</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            </div>
</template>

<script>
export default {
    name: 'PersonalArea',
    data() {
        return {
            text: 'Личный кабинет'
        }
    }
}
</script>
<style>
.personal-area h1 {
    margin-left: 10px;
    text-align: left;
}
.personal-area h2 {
    text-align: right;
}
.col-2 {
    text-align: left;
}
</style>