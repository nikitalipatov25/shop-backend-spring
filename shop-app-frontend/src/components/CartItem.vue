<template>
  <div class="cart-item">
    <div class="row">
      <div class="col-1">
      </div>
      <div class="col-3">
        <img
            :src="product.catalogProductPhoto"
            width="200px"
            height="200px"
            alt="">
      </div>
      <div class="col-2">
        {{ product.catalogProductName }}
      </div>
      <div class="col-3">
        <p>{{ product.productCost }} руб.</p>
        <button type="button" class="btn btn-primary" v-on:click="addCol">+</button>
        {{ product.selectedProductKol }} шт.
        <button type="button" class="btn btn-primary" v-on:click="subCol">-</button>
      </div>
      <div class="col-2">
        <button type="button" class="btn btn-danger" @click="deleteItemFromCart">Удалить</button>
      </div>
    </div>
    <hr>
  </div>
</template>

<script>
import { eventBus } from '../main'

export default {
  props: {
    product: {
      type: Object
    }
  },
  data() {
    return {
      count: 1,
      token:'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQG1haWwucnUiLCJyb2xlIjoiQURNSU4iLCJpYXQiOjE2MjYyNTYzNTIsImV4cCI6MTYyNjg2MTE1Mn0.MBbfdwjDStIFvIyClbRSLgHOCSY6P7kkbpWoOjuZkMM',
    }
  },
  methods: {
    async addCol() {
      this.product.selectedProductKol++;
      let payload = {
        "selectedProductKol": this.product.selectedProductKol,
      };
      await this.$api.cart.modifyCartItem(this.product.productId, payload);
      eventBus.$emit('addCol')
    },
    async subCol() {
      this.product.selectedProductKol--;
      let payload = {
        "selectedProductKol": this.product.selectedProductKol,
      };
      await this.$api.cart.modifyCartItem(this.product.productId, payload);
      eventBus.$emit('subCol')
    },
    async deleteItemFromCart() {
      await this.$api.cart.deleteItemFromCart(this.product.productId, this.token);
      eventBus.$emit('deleteItemFromCart');

    }
  }
}
</script>