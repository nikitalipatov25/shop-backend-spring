<template>
  <div class="test">
    <div class="custom-card">
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
          <p class="card-text" style="color: crimson" v-if="product.productKol === 1"> Поторопитесь! Осталась одна штука</p>
          <p class="card-text" v-else-if="product.productKol === 0"> Товар распродан</p>
          <p v-else class="card-text"> Кол-во на складе: {{product.productKol}} шт.</p>
        </div>
        <div class="card-footer">
          <button v-if="product.productKol === 0" class="btn btn-secondary">Добавить в корзину</button>
          <button v-else class="btn btn-primary" @click="addToCart">Добавить в корзину</button>
        </div>
      </div>
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
      byDefault: 'list',
      list: false,
      card : true,
    }
  },
  methods: {
    async addToCart() {
      const payload = await this.$api.catalog.getCatalogItemByUUID(this.product.id);
      console.log(payload)
      this.$api.cart.addItemToCart(this.product.id, payload);
      eventBus.$emit('addToCart');
    },
    async deleteFromCart() {
      this.$api.cart.deleteItemFromCart(this.product.id);
      eventBus.$emit('deleteFromCart');
    },
  }
}
</script>

<style>
</style>


