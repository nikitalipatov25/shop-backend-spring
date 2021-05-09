<template>
  <div class="orders-c">
    <div class="row">
      <div class="col-4">
        {{order.orderId}}
      </div>
      <div class="col-4">
        {{order.productsInfo}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    order: {
      type: Object
    }
  }
}
</script>