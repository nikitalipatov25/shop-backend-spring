<template>
  <div class="catalog-category">
    <div class="col-3">
      <div class="card" @click="openCatalogCategory('Товары для собак')">
        <img src='../assets/puppies.jpg' class="card-img-top" alt="dogs">
        <div class="card-body">
          <p class="card-text" >Товары для собак</p>
        </div>
      </div>
    </div>
    <div class="col-3">
      <div class="card" @click="openCatalogCategory('Товары для кошек')">
        <img src='../assets/cats.jpg' class="card-img-top" alt="dogs">
        <div class="card-body">
          <p class="card-text" >Товары для кошек</p>
        </div>
      </div>
    </div>
    <div class="col-3">
      <div class="card" @click="openCatalogCategory('Товары для рыбок')">
        <img src='../assets/fish.jpg' class="card-img-top" alt="dogs">
        <div class="card-body">
          <p class="card-text" >Товары для рыбок</p>
        </div>
      </div>
    </div>
    <div class="col-3">
      <div class="card" @click="openCatalogCategory('Товары для грызунов')">
        <img src='../assets/humsters.jpg' class="card-img-top" alt="dogs">
        <div class="card-body">
          <p class="card-text" >Товары для грызунов</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      param: ''
    }
  },
  methods: {
    openCatalogCategory(catalogParameter) {
      this.$router.push({ name: 'catalog-page', params: {catalogParameter: catalogParameter} });
    }
  }
}
</script>

<style>
.catalog-category img{
  height: 300px;
}
</style>