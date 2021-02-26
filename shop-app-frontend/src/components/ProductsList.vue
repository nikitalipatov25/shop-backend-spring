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
      type: Array
    }
  },
  components: { ProductCard }
}
</script>
