<template>
  <div class="product-page">
    <Header/>
    <div class="body">
      <div class="category-nav">
        <a href="#">{{ categoryType }}</a>
        <a href="#">{{ categoryName }}</a>
      </div>
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
            <p>Артикул товара: {{ productUUID }}</p>
            <hr>
            Описание: {{ productDescription }}
            <hr>
            <p>В наличии: {{ productKol }} шт.</p>
            <p>
              <button type="button" class="btn btn-success" @click="addCol">+</button>
              {{ addToCartKol }} шт.
              <button type="button" class="btn btn-success" @click="subCol">-</button>
            </p>
              <p v-if="!isError">
                <button class="btn btn-primary" @click="addToCart">
                  Добавить в корзину: {{productPrice}} руб.
                </button>
              </p>
            <div class="alert alert-warning" role="alert" v-else>
              Проверьте указанное кол-во!
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../components/Header'

export default {
  name: 'ProductPage',
  components: {
    Header
  },
  data() {
    return {
      itemsInCart: 0,
      searchText: '',
      addToCartKol: 1,
      isError: false,
      productUUID: '',
      productsFromServer: {},
      productName: '',
      productPrice: '',
      productPhoto: '',
      productDescription: '',
      productKol: '',
      tempPrice: 0,
      categoryType: '',
      categoryName: ''
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
    this.categoryType = this.productsFromServer.data.categoryType;
    this.categoryName = this.productsFromServer.data.categoryName;
  },
  methods: {
    async addToCart() {
      const productToCart = {
      "catalogProductName": this.productName,
      "catalogProductPrice": this.tempPrice,
      "selectedProductKol": this.addToCartKol,
      "catalogProductPhoto": this.productPhoto,
      "productCost": this.productPrice,
      }
      let productId = this.productUUID;
      this.$api.cart.addItemToCart(productId, productToCart);
      await this.$router.push({ name: 'cart', params: { id: this.productUUID } })
    },
    addCol() {
      if (this.addToCartKol >= this.productKol) {
        this.isError = true;
      } else {
        this.isError = false;
        this.addToCartKol++;
        this.productPrice = this.productPrice + this.tempPrice;
      }
    },
    subCol() {
      if (this.addToCartKol <= 1) {
        this.isError = true;
      } else {
        this.isError = false;
        this.addToCartKol--;
        this.productPrice = this.productPrice - this.tempPrice;
      }
    },
    searchProductsInCatalog() {
      console.log('Реализация аналогична поиску в каталоге')
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
.category-nav {
  text-align: left;
}
.category-nav a {
  margin-right: 25px;
}
</style>