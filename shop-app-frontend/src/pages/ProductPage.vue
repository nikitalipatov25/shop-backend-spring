<template>
    <div class="product-page">
        <div class="body">
            <div class="container">
                <h1>{{ productName }}</h1>
                    <div class="row">
                        <div class="col-5">
                            <img
                            :src="productPhoto"
                            :alt="productName"
                            height="300px"
                            >
                        </div>
                        <div class="col-7">
                            <hr>
                            Описание: {{ productDescription }}
                            <hr>
                            <button
                            class="btn btn-primary"
                            @click="$router.push({ name: 'cart', params: { id: productUUID } })"
                            >
                            Добавить в корзину: {{productPrice}}
                            </button>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
  data() {
    return {
    productUUID: '',
        productsFromServer: {},
        productName: '',
        productPrice: '',
        productPhoto: '',
        productDescription: '',
      text: 'This is product page',
    }
  },
  created: async function() {
      this.productUUID = this.$route.params.id;
      this.productsFromServer = await this.$api.catalog.getCatalogItemByUUID(this.productUUID);
      this.productName = this.productsFromServer.data.productName;
      this.productPrice = this.productsFromServer.data.productPrice;
      this.productPhoto = this.productsFromServer.data.productPhoto;
      this.productDescription = this.productsFromServer.data.productDescription;
  }
}
</script>

<style>
    .container h1 {
        text-align: left;
    }
    .col-7 {
        text-align: left;
    }
</style>