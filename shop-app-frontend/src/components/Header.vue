<template>
<header>
<div class="container">
{{ text }}
<nav class="navbar navbar-light bg-light">
                <a class="navbar-brand" href="#">
                    <img src="../assets/logo.jpg" width="30" height="30" class="d-inline-block align-top" alt="">
                        Название сайта
                </a>

                Бургер меню "Каталог2"

                <form class="form-inline">
                    <input class="form-control mr-sm-2" type="search" placeholder="Поиск" aria-label="Поиск">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Поиск</button>
                </form>

            Личный кабинет
            Корзина
            </nav>
</div>
</header>
</template>

<script>
export default {
  data() {
    return {
      text: 'false'
    }
  }
}
</script>
