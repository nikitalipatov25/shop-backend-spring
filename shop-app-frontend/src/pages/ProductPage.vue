<template>
    <div class="product-page">

      <h1>{{ text }}</h1>




        <div class="body">

          <div class="container" v-if="product">
    <h1>{{ product.title }}</h1>
    <img
      :src="product.img"
      :alt="product.title"
    >
    <div class="section">
      <p class="section__title">Цена:</p>
      <p class="section__description">{{ product.price }}</p>
    </div>
    <div class="section">
      <p class="section__title">Описание:</p>
      <p class="section__description">{{ product.description }}</p>
    </div>
  </div>

        </div>

        



</div>
  
</template>

<script>
import products from '../mocks/products'

export default {
  data() {
    return {
      text: 'This is product page',
      product: null
    }
  },
  created() {
    this.product = products.find(product => product.id == this.$route.params.id)
  }
}
</script>
