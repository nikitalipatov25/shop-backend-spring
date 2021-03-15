<template>
  <div class="container">
    <div class="card" style="width: 18rem;">
      <img
        class="card-img-top"
        :src="product.productPhoto"
        alt=""
        @click="$router.push({ name: 'product-page', params: { id: product.id } })"
      >
      <div class="card-body">
        <h5 class="card-title">{{ product.productName }}</h5>
        <p>Стоимость за шт. {{product.productPrice}} руб.</p>
        <p> Кол-во на складе: {{product.productKol}} шт.</p>
        <button class="btn btn-primary" @click="addToCart" v-if="!isAddedToCart">Добавить в корзину</button>
        <button class="btn btn-danger" @click="deleteFromCart" v-else>Убрать из корзины</button>
      </div>
    </div>
  </div>
</template>

<script>
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
      console.log(productToCart);
      this.$api.cart.addItemToCart(this.product.id, productToCart);
      this.isAddedToCart = !this.isAddedToCart;
    },
    async deleteFromCart() {
      this.$api.cart.deleteItemFromCart(this.product.id)
      this.isAddedToCart = !this.isAddedToCart;
    }
  }
}
</script>

<style>
.container {
  width: auto;
  margin-bottom: 25px;
}
</style>


