<template>
        <div class="card" style="width: 18rem;">
    <img  
    class="card-img-top"
    :src="product.img" 
    alt="">
      <div class="card-body">
          <h5 class="card-title">{{ product.title }}</h5>
          <a href="#" class="btn btn-primary" @click="$router.push({ name: 'product-page-product', params: { id: product.id } })">{{product.price}}</a>
      </div>
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

