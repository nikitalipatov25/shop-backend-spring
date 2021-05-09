<template>
  <div class="custom-list">
    <hr>
    <div class="row">
      <div class="col-3">
        <img
            :src="product.productPhoto"
            style="width: 286px; height: 286px"
            @click="$router.push({ name: 'product-page', params: { id: product.id } })"
        >
      </div>
      <div class="col-3">
        <p>{{product.productName}}</p>
        <p>Стоимость за шт. {{product.productPrice}} руб.</p>
        <p>Кол-во на складе: {{product.productKol}} шт.</p>
      </div>
      <div class="col-3">
        <button class="btn btn-primary" @click="addToCart">Добавить в корзину</button>
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

