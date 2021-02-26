<template>
    <div class="product-page">
        <h2>{{ text }}</h2>
            <div class="body">
                <div class="container">
                    <h1>{{ product.title }}</h1>
                        <div class="row">
                            <div class="col-sm-3">
                                <img
                                :src="product.img"
                                :alt="product.title"
                                height="300px"
                                >
                            </div>
                            <div class="col-9">
                                <hr>
                                Описание: {{ product.description }}
                                <hr>
                                <button
                                class="btn btn-primary"
                                @click="$router.push({ name: 'cart', params: { id: product.id } })"
                                >
                                Добавить в корзину: {{product.price}}
                                </button>
                            </div>
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

<style>
.col-9 {
text-align: justify;
}
</style>