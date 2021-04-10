<template>
  <div class="footer">
    <div class="alert alert-success" role="alert">
      Режим работы магазина: ежедневно с 10 до 20, тел.: 8(951)525-91-32
    </div>
    <div class="row">
      <div class="col-4">
        <ul class="list-group">
          <li class="list-group-item" @click="openCatalogCategory('empty')">Все товары</li>
          <li class="list-group-item" @click="openCatalogCategory('Товары для собак')">Товары для собак</li>
          <li class="list-group-item" @click="openCatalogCategory('Товары для кошек')">Товары для кошек</li>
          <li class="list-group-item" @click="openCatalogCategory('Товары для рыбок')">Товары для рыбок</li>
          <li class="list-group-item" @click="openCatalogCategory('Товары для грызунов')">Товары для грызунов</li>
        </ul>
      </div>
      <div class="col-4">
        <ul class="list-group">
          <li class="list-group-item" @click="$router.push({name: 'questions'})">Информация покупателям(ЧаВо)</li>
          <li class="list-group-item" @click="$router.push({name: 'deals'})">Скидки и акции</li>
        </ul>
      </div>
      <div class="col-4">
        <ul class="list-group">
          <li class="list-group-item" @click="$router.push({name: 'about'})">О магазине</li>
        </ul>
        <div class="social">
          <div class="row">
            <div class="col-6">
              <img src="../assets/icons/vk.png" style="height: 64px; width: 64px">
            </div>
            <div class="col-6">
              <img src="../assets/icons/instagram.png" style="height: 64px; width: 64px">
            </div>
          </div>
        </div>
      </div>
    </div>
    &copy; Hand-made магазин "Любимый питомец"
  </div>
</template>

<script>
import {eventBus} from "@/main";

export default {
  methods: {
    openCatalogCategory(catalogParameter) {
      if (this.$route.name === 'catalog-page') {
        eventBus.$emit('openCatalogCategory', catalogParameter)
      } else {
        this.$router.push({name: 'catalog-page', params: {catalogParameter: catalogParameter}});
      }
    }
  }
}
</script>

<style>
.footer {
  background-color: #92cbf8;
  text-align: center;
}
.social {
  margin-top: 15px;
  display: flex;
  justify-content: flex-start;
}
</style>