<template>
  <div class="custom-nav">
    {{ arrIndex }}
    <div class="c-list" v-for="item in dynamicArray" :key="item">
      <input type="checkbox" v-bind:value="item" v-model="arrIndex"> <label>{{ item }}</label>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      arrIndex: [],
      dynamicArrayParameter: '',
      dynamicArray: [],
      dogs: ['Ошейники', 'Поводки', 'Шлейки', 'Одежда', 'Игрушки', 'Миски'],
      cats: ['Домики', 'Лежанки', 'Когтеточки','Игрушки', 'Миски'],
      fish: ['Гроты, аксессуары', 'Уборка аквариума'],
      hamsters: ['Клетки', 'Поилки', 'Прогулочные сферы'],
    }
  },
  methods: {
    changeCheckBoxes(dynamicArrayParameter) {
      switch (dynamicArrayParameter) {
        case 'dogs': this.dynamicArray = this.dogs;
          break;
        case 'cats': this.dynamicArray = this.cats;
          break;
        case 'fish': this.dynamicArray = this.fish;
          break;
        case 'hamsters': this.dynamicArray = this.hamsters;
          break;
      }
      console.log(this.dynamicArray);
    },
    check(item) {
      console.log(item)
    }
  },
  created() {
    this.dynamicArrayParameter = this.$route.params.catalogParameter;
    this.changeCheckBoxes(this.dynamicArrayParameter);
  }
}
</script>