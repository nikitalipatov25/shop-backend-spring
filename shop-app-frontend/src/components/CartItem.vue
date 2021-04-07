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
      console.log(this.product);
      let payload = {
        "productId": this.product.productId,
        "userId": this.product.userId,
        "catalogProductName": this.product.catalogProductName,
        "catalogProductPhoto": this.product.catalogProductPhoto,
        "selectedProductKol": this.product.selectedProductKol,
        "catalogProductPrice": this.product.catalogProductPrice,
        "productCost": this.product.productCost
      };
      console.log(payload);

      let a = await this.$api.cart.modifyCartItem(this.product.productId, payload);
      console.log(a);
    },
    subCol: function() {
      this.product.selectedProductKol--;
    },
    async deleteItemFromCart() {
      await this.$api.cart.deleteItemFromCart(this.product.productId);
      eventBus.$emit('deleteItemFromCart');

    }
  }
}
</script>