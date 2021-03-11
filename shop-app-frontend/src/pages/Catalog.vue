<template>
  <div class="catalog">
    <div class="footer">
      <div class="row">
        <div class="col-3">
          "Одежда для всей семьи"
        </div>
        <div class="col-2">
          <a href="/catalog">Каталог</a>
        </div>
        <div class="col-2">
          <a href="/cart">Корзина</a>
        </div>
        <div class="col-2">
          <a href="/personalarea">Личный кабинет</a>
        </div>
        <div class="col-3">
          <input type="text" placeholder="Поиск товаров в каталоге" v-model="searchText">
          <button type="button" class="btn btn-primary" @click="searchProductsInCatalog">Поиск</button>
        </div>
      </div>
    </div>
    <div class="body">
      <h1>{{ text }}</h1>
      <div class="product-list">
        <product-card
            v-for="product in products"
            :key="product.id"
            :product="product"
        />
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from '../components/ProductCard'

export default {
  name: 'Catalog',
  components: {
    ProductCard
  },
  data() {
    return {
      searchText: '',
      products: [],
      productsFromServer: {},
      text: 'Все товары'
    }
  },
  methods: {
    async searchProductsInCatalog() {
      let a = await this.$api.catalog.getCatalogWithFilters(this.searchText);
      console.log(a)
    }
  },
  created: async function () {
    this.productsFromServer = await this.$api.catalog.getCatalog();
    this.products = this.productsFromServer.data.content;
  }
}
</script>

<style>
.product-list {
  display: flex;
  flex-direction: row;
}
.catalog h1 {
  margin-left: 10px;
  text-align: left;
}
.footer {
  background-color: aquamarine;
}
</style>