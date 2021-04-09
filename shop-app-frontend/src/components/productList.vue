<template>
  <div class="custom-list">
    <hr>
    <div class="row">
      <div class="col-3">
        <img
            :src="product.productPhoto"
            style="width: 286px; height: 286px"
        >
      </div>
      <div class="col-3">
        <p>{{product.productName}}</p>
        <p>Стоимость за шт. {{product.productPrice}} руб.</p>
        <p>Кол-во на складе: {{product.productKol}} шт.</p>
      </div>
      <div class="col-3">
        <button class="btn btn-primary">Добавить в корзину</button>
      </div>
    </div>

  </div>
</template>

<script>

export default {
  props: {
    product: {
      type: Object
    }
  }
}
</script>

