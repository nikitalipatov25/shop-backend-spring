<template>
  <div class="cart-item">
    <div class="row">
      <div class="col-3">
        <img
            :src="product.catalogProductPhoto"
            width="100px"
            height="100px"
            alt="">
      </div>
      <div class="col-2">
        {{ product.catalogProductName }}
        {{ product.productId }}
      </div>
      <div class="col-3">
        <button type="button" class="btn btn-primary" v-on:click="addCol">+</button>
        {{ product.selectedProductKol }} шт.
        <button type="button" class="btn btn-primary" v-on:click="subCol">-</button>
      </div>
      <div class="col-2">
        {{ product.productCost }} руб.
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
      count: 1
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
      await this.$api.cart.deleteItemFromCart(this.product.productId);
      eventBus.$emit('deleteItemFromCart');

    }
  }
}
</script>