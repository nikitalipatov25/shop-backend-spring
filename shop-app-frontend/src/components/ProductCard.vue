<template>
  <div class="card" style="width: 18rem; margin-bottom: 25px; margin-right: 25px">
    <img
      style="height: 286px; width: 286px"
      class="card-img-top"
      :src="product.productPhoto"
      alt=""
      @click="$router.push({ name: 'product-page', params: { id: product.id } })"
    >
    <div class="card-body">
      <h5 class="card-title">{{ product.productName }}</h5>
      <p class="card-text">Стоимость за шт. {{product.productPrice}} руб.</p>
      <p class="card-text"> Кол-во на складе: {{product.productKol}} шт.</p>
    </div>
    <div class="card-footer">
      <button class="btn btn-primary" @click="addToCart" v-if="!isAddedToCart">Добавить в корзину</button>
      <button class="btn btn-danger" @click="deleteFromCart" v-else>Убрать из корзины</button>
    </div>
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
      isAddedToCart: false,
    }
  },
  methods: {
    async addToCart() {
      const productToCart = {
        "catalogProductName": this.product.productName,
        "catalogProductPrice": this.product.productPrice,
        "selectedProductKol": 1,
        "catalogProductPhoto": this.product.productPhoto
        }
      this.$api.cart.addItemToCart(this.product.id, productToCart);
      this.isAddedToCart = !this.isAddedToCart;
      eventBus.$emit('addToCart');
    },
    async deleteFromCart() {
      this.$api.cart.deleteItemFromCart(this.product.id);
      this.isAddedToCart = !this.isAddedToCart;
      eventBus.$emit('deleteFromCart');
    }
  },
}
</script>

<style>
/*.container {*/
/*  width: auto;*/
/*  margin-bottom: 25px;*/
/*}*/
</style>


