<template>
  <div class="catalog">
    <Header/>
    <div class="body">
      <div class="row">
        <div class="col-2">
          <CatalogMenu/>
        </div>
        <div class="col-10">
          <div class="catalog-nav">
            <div class="row">
              <div class="col-3">
                <h1>{{text}}</h1>
              </div>
              <div class="col-4">
              </div>
              <div class="col-3">
                <select class="form-select" aria-label="Default select example" @change="sortProducts($event)">
                  <option selected value="default">По умолчанию</option>
                  <option value="priceLess">Сначала дешевле</option>
                  <option value="priceMore">Сначала дороже</option>
                  <option value="popular">Сначала популярные</option>
                  <option value="highRating">Сначала с высоким рейтингом</option>
                </select>
              </div>
              <div class="col-2">
                <button class="btn btn-primary" @click="changeCatalogStyle('card')">Карточки</button>
                <button class="btn btn-primary" @click="changeCatalogStyle('list')">Список</button>
              </div>
            </div>
          </div>
          <div class="product-list" v-show="list">
            <product-list
                v-for="product in products"
                :key="product.id"
                :product="product"
            />
          </div>
          <div class="product-card" v-show="card">
            <product-card
                v-for="product in products"
                :key="product.id"
                :product="product"
            />
          </div>
        </div>
      </div>
      <hr>
      <nav aria-label="Page navigation">
        <ul class="pagination">
          <li @click="changePageNoIndex('first')" class="page-item"><a class="page-link" >&laquo;</a></li>
          <li @click="changePageNoIndex('previous')" class="page-item"><a class="page-link" >Предыдущая</a></li>
          <li @click="changePage(pageIndex)" class="page-item" v-for="pageIndex in totalPages" :key="pageIndex"><a class="page-link">{{ pageIndex }}</a></li>
          <li @click="changePageNoIndex('next')" class="page-item"><a class="page-link" >Следующая</a></li>
          <li @click="changePageNoIndex('last')" class="page-item"><a class="page-link" >&raquo;</a></li>
        </ul>
      </nav>
    </div>
    <Footer/>
  </div>
</template>

<script>
import ProductCard from '../components/ProductCard'
import productList from '../components/productList'
import CatalogMenu from '../components/CatalogMenu'
import Header from '../components/Header'
import Footer from '../components/Foter'
import { eventBus } from '../main'

export default {
  name: 'Catalog',
  components: {
    ProductCard,
    productList,
    CatalogMenu,
    Header,
    Footer,
  },
  data() {
    return {
      list: true,
      card: false,
      products: [],
      productsFromServer: {},
      searchFor: '',
      searchText: '',
      sortBy: 'productName,asc',
      inCategory: 'empty',
      withChekboxes: [],
      startPrice: 0,
      endPerice: 999999,
      text: 'Все товары',
      currentPage: 0,
      pageSize: 4,
      totalPages: ''
    }
  },
  methods: {
    async getCatalog() {
      if (this.inCategory !== 'empty') {
        await this.getCategory()
      } else {
        console.log(this.inCategory )
        this.searchFor = this.searchText + this.startPrice + '-' + this.endPerice
        console.log(this.searchFor)
        this.productsFromServer = await this.$api.catalog.getCatalog(this.currentPage, this.pageSize, this.inCategory, this.withChekboxes, this.searchFor, this.sortBy);
        this.products = this.productsFromServer.data.content;
        this.totalPages = this.productsFromServer.data.totalPages
        console.log(this.totalPages)
      }
    },
    async getCategory() {
      this.searchFor = this.searchText + this.startPrice + '-' + this.endPerice
      console.log(this.searchFor)
      this.productsFromServer = await this.$api.catalog.getCategory(this.currentPage, this.pageSize, this.inCategory, this.withChekboxes, this.searchFor, this.sortBy);
      this.products = this.productsFromServer.data.content;
      this.totalPages = this.productsFromServer.data.totalPages
      console.log(this.totalPages)
    },
    async countItemsInCart() {
      const userId = 'cd668994-a73a-4da6-8f03-e7fe7034aa17'
      const cart = await this.$api.cart.getCart(userId);
      let itemsInCart = cart.data.catalogPage.numberOfElements;
      eventBus.$emit('addCountedItemsToBadge', itemsInCart)
    },
    sortProducts(event) {
      switch (event.target.value) {
        case 'priceLess':
          this.sortBy = 'productPrice,asc';
          break;
        case 'priceMore':
          this.sortBy = 'productPrice,desc';
          break;
        case 'default':
          this.sortBy = '';
          break
      }
      this.getCatalog()
    },
    changeCatalogStyle(viewParameter) {
      switch (viewParameter) {
        case 'list':
          this.list = true;
          this.card = false;
          break;
        case 'card':
          this.list = false;
          this.card = true;
      }
    },
    async changePage(pageIndex) {
      this.currentPage = pageIndex - 1;
      await this.getCatalog()
    },
    async changePageNoIndex(parameter) {
      switch(parameter) {
        case 'first':
          this.currentPage = 0;
          break;
        case 'previous':
          if (this.currentPage > 0 ) {
            this.currentPage = this.currentPage - 1;
          }
          break;
        case 'next':
          if (this.currentPage !== this.totalPages - 1) {
            this.currentPage = this.currentPage + 1;
          }
          break;
        case 'last':
          this.currentPage = this.totalPages - 1;
          break;
      }
      await this.getCatalog()
    }
  },
  created() {
    this.countItemsInCart();
    eventBus.$on('addToCart', this.countItemsInCart);
    eventBus.$on('deleteFromCart', this.countItemsInCart);

    eventBus.$on('searchProducts', data => {
      this.searchText = data;
      this.getCatalog()
    });
    eventBus.$on('changePage', data => {
      this.currentPage = data
      this.getCatalog()
    });
    eventBus.$on('sortProducts', data => {
      this.sortBy = data;
      this.getCatalog()
    });
    eventBus.$on('getCategory', data => {
      this.inCategory = data[0];
      this.withChekboxes = data[1];
      this.startPrice = data[2];
      this.endPerice = data[3];
      this.getCategory()
    });
    eventBus.$on('changeCheckBoxes', data => {
      this.inCategory = data;
      if (this.inCategory === 'empty') {
        this.getCatalog();
      } else {
        this.text = this.inCategory
        this.getCategory();
      }
    });
    eventBus.$on('openCatalogCategory', data => {
      this.inCategory = data;
      if (this.inCategory === 'empty') {
        this.getCatalog();
      } else {
        this.text = this.inCategory
        this.getCategory();
      }
    })
  }
}
</script>

<style>
nav {
  display: flex;
  justify-content: center;
}
/*body {*/
/*  background: url("../assets/background.jpg") ;*/
/*}*/
.product-card {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
}
</style>