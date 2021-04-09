<template>
  <div class="custom-left-menu">
    <p>Категория:</p>
    <div class="c-list" v-for="item in dynamicArray" :key="item">
      <input type="checkbox" v-bind:value="item" v-model="checkboxesArray"> <label>{{ item }}</label>
    </div>
    <p>Цена:</p>
    <input type="text"> - <input type="text">
    <button @click="getCategory">Применить</button>

  </div>
</template>

<script>
import {eventBus} from "@/main";

export default {
  data() {
    return {
      checkboxesArray: [],
      dynamicArrayParameter: '',
      dynamicArray: ['Товары для собак','Товары для кошек','Товары для рыбок','Товары для грызунов'],
      dogs: ['Ошейники', 'Поводки', 'Шлейки', 'Одежда', 'Игрушки', 'Миски'],
      cats: ['Домики', 'Лежанки', 'Когтеточки','Игрушки', 'Миски'],
      fish: ['Гроты, аксессуары', 'Уборка аквариума'],
      hamsters: ['Клетки', 'Поилки', 'Прогулочные сферы'],
    }
  },
  methods: {
    async getCategory() {
      let arr = [this.dynamicArrayParameter, this.checkboxesArray];
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
  background-color: cadetblue;
}
</style>