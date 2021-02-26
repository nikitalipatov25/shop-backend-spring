<template>
    <div class="card" style="width: 18rem;">
        <div class="toProductPage" @click="$router.push({ name: 'product-page-product', params: { id: product.id } })">
            <img
            class="card-img-top"
            :src="product.img"
            alt="">
                <div class="card-body">
                    <h5 class="card-title">{{ product.title }}</h5>
                </div>
        </div>
            <button class="btn btn-primary" @click="$router.push({ name: 'cart', params: { id: product.id } })"> {{product.price}}</button>
    </div>
</template>

<script>
export default {
  props: {
    product: {
      type: Object
    }
  }
}
</script>

