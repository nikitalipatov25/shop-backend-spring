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
            
            <h2>Акции</h2>
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="../assets/cats.jpg" class="d-block w-100" alt="">
                    </div>
                    <div class="carousel-item">
                        <img src="../assets/puppies.jpg" class="d-block w-100" alt="">
                    </div>
                </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

            <h2>Новинки</h2>

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
    name: 'Index',
    components: { ProductsList },
    data() {
        return {
            products,
            text: 'This is main page',
            cardProductName: 'Название товара',
            cardProductPrice: 99.99
        }
    }
}
</script>