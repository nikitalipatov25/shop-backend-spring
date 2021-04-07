<template>
  <div class="catalog">
    <Header/>
    <div class="body">
      <div class="row">
        <div class="col-3">
          <CatalogMenu/>
        </div>
        <div class="col-9">
          <CatalogNavbar/>
          <div class="product-list">
            <product-card
                v-for="product in products"
                :key="product.id"
                :product="product"
            />
          </div>
        </div>
      </div>
      <pagination-component/>
    </div>
    <Footer/>
  </div>
</template>

<script>
import ProductCard from '../components/ProductCard'
import CatalogMenu from '../components/CatalogMenu'
import Header from '../components/Header'
import Footer from '../components/FFooter'
import CatalogNavbar from '../components/CatalogNavbar'
import paginationComponent from "@/components/paginationComponent";
import { eventBus } from '../main'

export default {
  name: 'Catalog',
  components: {
    ProductCard,
    CatalogMenu,
    Header,
    CatalogNavbar,
    Footer,
    paginationComponent
  },
  data() {
    return {
      products: [],
      productsFromServer: {},
    }
  },
  methods: {
    async getCatalog(currentPage) {
      this.productsFromServer = await this.$api.catalog.getCatalog(currentPage);
      this.products = this.productsFromServer.data.content;
    },
    async getCatalogWithFilters(sort) {
      this.productsFromServer = await this.$api.catalog.getCatalog(sort);
      console.log('sorting');
      this.products = this.productsFromServer.data.content;
    },
    async searchProductsInCatalog(searchText) {
      this.productsFromServer = await this.$api.catalog.getCatalogWithFilters('ddfdf',searchText);
      this.products = this.productsFromServer.data.content;
    },
    async countItemsInCart() {
      const userId = 'cd668994-a73a-4da6-8f03-e7fe7034aa17'
      const cart = await this.$api.cart.getCart(userId);
      let itemsInCart = cart.data.catalogPage.numberOfElements;
      eventBus.$emit('addCountedItemsToBadge', itemsInCart)
    },
    async checkState() {
      const arr = await this.$api.cart.getCart('cd668994-a73a-4da6-8f03-e7fe7034aa17');
       console.log(arr);
    }
  },
  created() {
    this.getCatalog();
    eventBus.$on('addToCart', this.countItemsInCart);
    eventBus.$on('deleteFromCart', this.countItemsInCart);
    eventBus.$on('searchProductsInCatalog', this.searchProductsInCatalog);
    eventBus.$on('changePage', this.getCatalog);
    eventBus.$on('sortResults', this.getCatalogWithFilters);
    this.countItemsInCart();
    this.checkState();
  }
}
</script>

<style>
/*body {*/
/*  background: url("../assets/background.jpg") ;*/
/*}*/
.product-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
}
</style>