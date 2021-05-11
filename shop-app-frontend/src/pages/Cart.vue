<template>
  <div class="cart">
    <Header/>
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
          <p>Общее кол-во товара: {{ resultProductsCount }} шт.</p>
          <p>Общая стоимость: {{ resultProductsCost }} руб.</p>
          <p>Скидка: {{ resultProductsDiscount }} руб.</p>
          <p>Итого: {{ finalResult }} руб.</p>
          <select v-model="orderType">
            <option disabled value="">Выберите один из вариантов доставки</option>
            <option>Самовывоз из магазина</option>
            <option>Доставка по указанному в профиле адресу</option>
          </select>
          <button type="button" class="btn btn-warning" @click="orderProducts">Оформить</button>
        </div>
      </div>
    </div>
</div>
</template>

<script>
import CartItem from '../components/CartItem'
import { eventBus } from '../main'
import Header from '../components/Header'

export default {
  name: 'Cart',
  components: {
    CartItem,
    Header
  },
  data() {
    return {
      searchText: '',
      text: 'Корзина',
      products: [],
      productsFromServer: {},
      resultProductsCount: 0,
      resultProductsCost: 0,
      resultProductsDiscount: 0,
      finalResult: 0,
      orderType: 'Не выбрано'
    }
  },
  methods: {
    async getCart() {
      this.productsFromServer = await this.$api.cart.getCart('cd668994-a73a-4da6-8f03-e7fe7034aa17');
      this.products = this.productsFromServer.data.catalogPage.content;
      this.resultProductsCount = this.productsFromServer.data.cartSummary[0];
      this.resultProductsCost = this.productsFromServer.data.cartSummary[1];
      this.resultProductsDiscount = this.productsFromServer.data.cartSummary[2];
      this.finalResult = this.productsFromServer.data.cartSummary[3];
    },
    async searchProductsInCart() {
      this.productsFromServer = await this.$api.cart.getCartWithFilters(this.searchText);
      this.products = this.productsFromServer.data.catalogPage.content;
    },
    async orderProducts() {
      this.$api.orders.addOrder(this.orderType);

    }
  },
  created() {
    this.getCart();
    eventBus.$on('deleteItemFromCart', this.getCart)
    eventBus.$on('addCol', this.getCart)
    eventBus.$on('subCol', this.getCart)
  }
}
</script>

<style>
.cart h1 {
  text-align: left;
  margin-left: 10px;
}
</style>