<template>
    <div class="card" style="width: 18rem;">
        <div class="toProductPage" @click="$router.push({ name: 'product-page', params: { id: product.id } })">
            <img
            class="card-img-top"
            :src="product.productPhoto"
            alt="">
                <div class="card-body">
                    <h5 class="card-title">{{ product.productName }}</h5>
                </div>
        </div>
            <button class="btn btn-primary" @click="$router.push({ name: 'cart', params: { id: product.id } })"> {{product.productPrice}}</button>
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

