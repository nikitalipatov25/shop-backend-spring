<template>
  <div class="orders-c">
    <div class="row">
      <div class="col-2">
        {{order.orderId}}
      </div>
      <div class="col-2">
      {{order.finalPrice}}
      </div>
      <div class="col-2">
        {{order.productsInfo}}
      </div>
      <div class="col-2">
        {{order.orderDate}}
      </div>
      <div class="col-2">
        {{order.orderType}}
      </div>
      <div class="col-2">
        {{order.orderStatus}}
      </div>
    </div>
    <hr>
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