<template>
  <div class="catalog">
    <div class="footer">
      <div class="row">
        <div class="col-3">
          Hand-made магазин "Любимый питомец"
        </div>
        <div class="col-2">
          <a href="/catalog">Каталог</a>
        </div>
        <div class="col-2">
          <a href="/cart">Корзина</a>
          <span class="badge badge-success">{{ itemsInCart }}</span>
        </div>
        <div class="col-2">
          <a href="/personalarea">Личный кабинет</a>
        </div>
        <div class="col-3">
          <input type="text" placeholder="Поиск товаров в каталоге" v-model="searchText">
          <button type="button" class="btn btn-primary" @click="searchProductsInCatalog">Поиск</button>
        </div>
      </div>
    </div>
    <div class="body">
      <div class="row">
        <div class="col-3">
          <div class="accordion" id="accordionMenu">
            <div class="card">
              <div class="card-header" id="headingOne">
                <h2 class="mb-0">
                  <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                    Товары для собак
                  </button>
                </h2>
              </div>
              <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionMenu">
                <div class="card-body">
                  <p>Ошейники</p>
                  <p>Поводки</p>
                  <p>Шлейки</p>
                  <p>Одежда</p>
                  <p>Игрушки</p>
                  <p>Миски</p>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="card-header" id="headingTwo">
                <h2 class="mb-0">
                  <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    Товары для кошек
                  </button>
                </h2>
              </div>
              <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionMenu">
                <div class="card-body">
                  <p>Домики</p>
                  <p>Лежанки</p>
                  <p>Когтеточки</p>
                  <p>Игрушки</p>
                  <p>Миски</p>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="card-header" id="headingThree">
                <h2 class="mb-0">
                  <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    Товары для рыбок
                  </button>
                </h2>
              </div>
              <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionMenu">
                <div class="card-body">
                  <p>Гроты, аксессуары</p>
                  <p>Уборка аквариума</p>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="card-header" id="headingFour">
                <h2 class="mb-0">
                  <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                    Товары для грызунов
                  </button>
                </h2>
              </div>
              <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionMenu">
                <div class="card-body">
                  <p>Клетки</p>
                  <p>Поилки</p>
                  <p>Прогулочные сферы</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-9">
          <h1>{{ text }}</h1>
          <div class="product-list">
            <product-card
                v-for="product in products"
                :key="product.id"
                :product="product"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from '../components/ProductCard'

export default {
  name: 'Catalog',
  components: {
    ProductCard
  },
  data() {
    return {
      itemsInCart: 0,
      searchText: '',
      products: [],
      productsFromServer: {},
      text: 'Все товары'
    }
  },
  methods: {
    async searchProductsInCatalog() {
      this.productsFromServer = await this.$api.catalog.getCatalogWithFilters(this.searchText);
      this.products = this.productsFromServer.data.content;
    },
    async countItemsInCart() {
      const userId = 'cd668994-a73a-4da6-8f03-e7fe7034aa17'
      const cart = await this.$api.cart.getCart(userId);
      this.itemsInCart = cart.data.numberOfElements;
      console.log(this.itemsInCart);
    }
  },
  created: async function () {
    this.productsFromServer = await this.$api.catalog.getCatalog();
    this.products = this.productsFromServer.data.content;
    await this.countItemsInCart();
  }
}
</script>

<style>
.product-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.catalog h1 {
  margin-left: 10px;
  margin-bottom: 20px;
  text-align: left;
}
.footer {
  background-color: aquamarine;
  margin-bottom: 30px;
}
</style>