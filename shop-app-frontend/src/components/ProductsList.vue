<template>
  <div class="product-list">
    <product-card
      v-for="product in products"
      :key="product.id"
      :product="product"
    />
  </div>
</template>

<script>
import ProductCard from './ProductCard'

export default {
  props: {
    products: {
      type: Array,
      default: () => []
    }
  },
  components: { ProductCard }
}
</script>

<style>
.product-list {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
</style>
