<template>
  <div class="custom-left-menu">
<!--    скрыть блок с категорией, когда отображаютсся все товары. показывать толко по стоимости и аакции-->
    <p>Категория:</p>
    <div class="c-list" v-for="item in dynamicArray" :key="item">
      <input type="checkbox" v-bind:value="item" v-model="checkboxesArray"> <label>{{ item }}</label>
    </div>
    <p>Цена:</p>
    <input type="text" placeholder="от руб." v-model="startPrice"> - <input type="text" placeholder="до руб." v-model="endPrice">
    <button @click="getCategory">Применить</button>

  </div>
</template>

<script>
import {eventBus} from "@/main";

export default {
  data() {
    return {
      startPrice: 0,
      endPrice: 999999,
      checkboxesArray: [],
      dynamicArrayParameter: '',
      dynamicArray: [],
      dogs: ['Ошейники', 'Поводки', 'Шлейки', 'Одежда', 'Игрушки', 'Миски'],
      cats: ['Домики', 'Лежанки', 'Когтеточки','Игрушки', 'Миски'],
      fish: ['Гроты, аксессуары', 'Уборка аквариума'],
      hamsters: ['Клетки', 'Поилки', 'Прогулочные сферы'],
    }
  },
  methods: {
    async getCategory() {
      let arr = [this.dynamicArrayParameter, this.checkboxesArray, this.startPrice, this.endPrice];
      eventBus.$emit('getCategory', arr)
    },
    changeCheckBoxes(dynamicArrayParameter) {
      switch (dynamicArrayParameter) {
        case 'Товары для собак': this.dynamicArray = this.dogs;
          break;
        case 'Товары для кошек': this.dynamicArray = this.cats;
          break;
        case 'Товары для рыбок': this.dynamicArray = this.fish;
          break;
        case 'Товары для грызунов': this.dynamicArray = this.hamsters;
          break;
        default: this.dynamicArrayParameter = 'empty'
      }
      eventBus.$emit('changeCheckBoxes', this.dynamicArrayParameter)
    }
  },
  created() {
    this.dynamicArrayParameter = this.$route.params.catalogParameter;
    this.changeCheckBoxes(this.dynamicArrayParameter);
  }
}
</script>

<style>
.custom-left-menu {
  background-color: #92cbf8;
}
</style>