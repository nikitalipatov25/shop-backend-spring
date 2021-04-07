<template>
  <div class="custom-nav">
    <div class="row">
      <div class="col-3">
        <h1>{{ text }}</h1>
      </div>
      <div class="col-9">
        Сортировать:
        <button type="button">по популярности</button>
        <button type="button" class="btn btn-success" v-on:click="sortResults('productPrice')">по цене</button>
        <button type="button">по рейтингу</button>
        <button type="button">по отзывам</button>
      </div>
    </div>
  </div>
</template>

<script>
import {eventBus} from "@/main";

export default {
  data() {
    return {
      text: 'Все товары',
      sort: '',
      previousSort: '',
    }
  },
  methods: {
    async sortResults(sortParameter) {
      if (this.sort === '') {
        this.sort = sortParameter;
      } else if (this.previousSort !== sortParameter) {
        this.sort = sortParameter;
      } else {
        this.sort = '';
      }
      eventBus.$emit('sortResults', this.sort);
      this.previousSort = sortParameter;
    }
  }
}
</script>

<style>
.custom-nav {
  background-color: #e96b2d;
  margin-bottom: 20px;
  text-align: left;
}

</style>