<template>
    <div class="cart-item">
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <img
                    :src="product.data.productPhoto"
                    height="200px"
                    >
                </div>
                <div class="col-2">
                    {{ product.data.productName }}
                </div>
                <div class="col-3">
                    <button v-on:click="addCol">Add</button>
                    {{ product.data.productKol }}
                    <button v-on:click="subCol">Sub</button>
                </div>
                    <div class="col-2">
                {{ product.data.productPrice }}
                </div>
                <div class="col-2">
                    <button class="btn-warning">DeleteFromCart</button>
                </div>
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
  },
    data() {
        return {
            count: 1
        }
    },
    methods: {
        addCol: function() {
            this.count++;
        },
        subCol: function() {
            this.count--;
        }
    }
}
</script>