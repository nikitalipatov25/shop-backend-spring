<template>
  <div class="cart">
    <Header/>
    <div class="body">
      <div class="row">
        <div class="col-7">
          <cart-item
              v-for="product in products"
              :key="product.id"
              :product="product"
          />
        </div>
        <div class="col-5">
          <p>Товаров в корзине: {{ resultProductsCount }}</p>
          <p>Цена с учетом скидки: {{ finalResult }} руб.</p>
          <b-button variant="primary" @click="$bvModal.show('order-modal')" >Оформить покупку</b-button>
          <div>
            <b-modal id="order-modal" hide-footer title="Оформить покупку">
              <div class="d-block text-left">
                <form>
                  <select class="form-select" v-model="typeToServer">
                    <option
                    v-for="type in orderType"
                    :value="type.value"
                    :key="type"
                    >
                      {{type.label}}
                    </option>
                  </select>
                  <div class="mb-3" v-if="typeToServer === 'Delivery'">
                    <label  class="form-label">Адрес</label>
                    <input type="email" class="form-control"  v-model="userAdress">
                  </div>
                  <div class="mb-3">
                    <label  class="form-label">ФИО покупателя</label>
                    <input type="email" class="form-control"  v-model="userName">
                  </div>
                  <div class="mb-3">
                    <label  class="form-label">Телефон</label>
                    <input type="email" class="form-control"  v-model="userPhone">
                  </div>
                  <p>*Оплата производится наличными и только при получении товара</p>
                </form>
              </div>
              <b-button @click="orderProducts" variant="success">Оформить</b-button>
              <b-button @click="$bvModal.hide('add-modal')" variant="danger">Закрыть</b-button>
            </b-modal>
          </div>
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
      userName:"",
      userAdress:"",
      userPhone:"",
      orderType: [
          {
            label: 'Самовывоз из магазина',
            value: 'Pickup'
          },
          {
            label: 'Доставка по указанномму адресу',
            value: 'Delivery'
          }
      ],
      typeToServer: 'Pickup'
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
    orderProducts() {
      this.$api.orders.addOrder(this.typeToServer);
      this.$router.push({name: 'personal-area'})
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