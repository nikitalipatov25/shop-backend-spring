<template>
  <div class="cart">
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
          <input type="text" placeholder="Поиск по корзине" v-model="searchText">
          <button type="button" class="btn btn-primary" @click="searchProductsInCart">Поиск</button>
        </div>
      </div>
    </div>
    <div class="body">
      <div class="row">
        <div class="col-9">
          <cart-item
              v-for="product in products"
              :key="product.id"
              :product="product"
          />
        </div>
        <div class="col-3">
          <p>Итоговая стоимость:</p>
          <p>Общее кол-во товара:</p>
          <p>Общая стоимость:</p>
          <p>Скидка:</p>
          <p>Итого:</p>
          <button type="button" class="btn btn-warning">Оформить</button>
        </div>
      </div>
    </div>
</div>
</template>

<script>
import CartItem from '../components/CartItem'

export default {
  name: 'Cart',
  components: {
    CartItem
  },
  data() {
    return {
      searchText: '',
      text: 'Корзина',
      count: 1,
      productUUID: '',
      products: [],
      productsFromServer: {},
      product: {},
      productsUUID: {},
    }
  },
  methods: {
    async searchProductsInCart() {
      let temp = await this.$api.cart.getCartWithFilters(this.searchText);
      console.log(temp);
    }
  },
  async mounted() {
    this.productsFromServer = await this.$api.cart.getCart();
    this.products = this.productsFromServer.data.content;
  }
}
</script>

<style>
.cart h1 {
  text-align: left;
  margin-left: 10px;
}
</style>