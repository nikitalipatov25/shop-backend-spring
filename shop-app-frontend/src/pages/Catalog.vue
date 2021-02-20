<template>
    <div class="index">

        <h1>{{ text }}</h1>

        <div class="header">

            <nav class="navbar navbar-light bg-light">
                <a class="navbar-brand" href="#">
                    <img src="../assets/logo.jpg" width="30" height="30" class="d-inline-block align-top" alt="">
                        Название сайта
                </a>

                Бургер меню "Каталог"

                <form class="form-inline">
                    <input class="form-control mr-sm-2" type="search" placeholder="Поиск" aria-label="Поиск">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Поиск</button>
                </form>
            
            Личный кабинет
            Корзина
            </nav>

        </div>

        <div class="body">

             <products-list :products="products" />
        </div>

        <div class="footer">

            <div class="alert alert-success" role="alert">
                Контактная информация: телефон, режим работы, ссылки на соц. сети
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-sm-4">
                        Catalog
                        <ul class="list-group">
                            <li class="list-group-item">Собаки</li>
                            <li class="list-group-item">Кошки</li>
                            <li class="list-group-item">Птицы</li>
                            <li class="list-group-item">Рыбы</li>
                            <li class="list-group-item">Грызуны</li>
                        </ul>
                    </div>
                    <div class="col-sm-4">
                        Информация покупателям 
                    </div>
                    <div class="col-sm-4">
                        О магазине
                    </div>
                </div>
            </div>

        &copy; Зоомагазин Пользовательское соглашение Политика конфиденциальности
        </div>

    </div>
</template>

<script>
import ProductsList from '../components/ProductsList'
import products from '../mocks/products'

export default {
    name: 'Catalog',
    components: { ProductsList },
    data() {
        return {
            products,
            text: 'This is catalog page'
        }
    }
}
</script>