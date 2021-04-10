<template>
  <div class="header">
    <div class="row">
      <div class="col-3">
        <a href="/"><strong>Hand-made магазин "Любимый питомец"</strong></a>
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
        <button type="button" class="btn btn-primary" @click="searchProducts">Поиск</button>
      </div>
    </div>
  </div>
</template>

<script>
import { eventBus } from '../main'

export default {
  data() {
    return {
      searchText: '',
      itemsInCart: '',
    }
  },
  methods : {
    async searchProducts() {
      eventBus.$emit('searchProducts', this.searchText)
    },
    addCountedItemsToBadge(itemsInCart) {
      this.itemsInCart = itemsInCart;
    }
  },
  created() {
    eventBus.$on('addCountedItemsToBadge', this.addCountedItemsToBadge)
  }
}
</script>

<style>
.header {
  background-color: #92cbf8;
  margin-bottom: 30px;
}
</style>