<template>
    <div class="product-page">
        <div class="body">
            <div class="container">
                    <div class="row">
                        <div class="col-5">
                            <img
                            :src="productPhoto"
                            :alt="productName"
                            height="300px"
                            >
                        </div>
                        <div class="col-7">
                                <h1>{{ productName }}</h1>
                                <hr>
                                Описание: {{ productDescription }}
                                <hr>
                                <p>В наличии: {{ productKol }} шт.</p>
                                <p>
                                    <button type="button" class="btn btn-success" @click="addCol">+</button>
                                        {{ addToCartKol }} шт.
                                    <button type="button" class="btn btn-success" @click="subCol">-</button>
                                </p>
                                <div class="alert alert-warning" role="alert" v-show="error">
                                    Проверьте указанное кол-во!
                                </div>
                                <p>
                                    <button class="btn btn-primary" @click="addToCart">
                                        Добавить в корзину: {{productPrice}} руб.
                                    </button>
                                </p>
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
    addToCartKol: 1,
    error: false,
    productUUID: '',
        productsFromServer: {},
        productName: '',
        productPrice: '',
        productPhoto: '',
        productDescription: '',
        productKol: '',
        tempPrice: 0,
      text: 'This is product page',
    }
  },
  created: async function() {
      this.productUUID = this.$route.params.id;
      this.productsFromServer = await this.$api.catalog.getCatalogItemByUUID(this.productUUID);
      this.productName = this.productsFromServer.data.productName;
      this.productPrice = this.productsFromServer.data.productPrice;
      this.tempPrice = this.productsFromServer.data.productPrice;
      this.productPhoto = this.productsFromServer.data.productPhoto;
      this.productDescription = this.productsFromServer.data.productDescription;
      this.productKol = this.productsFromServer.data.productKol;
  },
  methods: {
  async addToCart() {
      const productToCart = {
      "catalogProductName": this.productName,
      "catalogProductPrice": this.tempPrice,
      "selectedProductKol": this.addToCartKol,
      "catalogProductPhoto": this.productPhoto,
      "productCost": this.productPrice
      }
      console.log(productToCart);
      this.$api.cart.addItemToCart(productToCart);
      this.$router.push({ name: 'cart', params: { id: this.productUUID } })
      },
      addCol() {
        if (this.addToCartKol >= this.productKol) {
            this.error = !this.error;
            console.log(this.error);
      } else {
        this.addToCartKol++;
        this.productPrice = this.productPrice + this.tempPrice;
      }
      },
      subCol() {
        if (this.addToCartKol <= 1) {
                  this.error = !this.error;
                  console.log(this.error);
            } else {
              this.addToCartKol--;
              this.productPrice = this.productPrice - this.tempPrice;
            }
      }
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