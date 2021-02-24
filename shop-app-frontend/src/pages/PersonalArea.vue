<template>
    <div class="personal-area">

        <h1>{{ text }}</h1>



        <div class="body">
            
            Личные данные
            ФИО
            Телефон
            Почта
            Адрес
            Роль
            ----------
            Заказы
            Номер заказа
            Итоговая стоимость
            Доставка/Самовывоз
            Статус заказа (оформлен/ожидает/завершен)
            
        </div>



    </div>
</template>

<script>
export default {
    name: 'PersonalArea',
    data() {
        return {
            text: 'This is personal area page'
        }
    }
}
</script>