<template>
    <div class="cart-item">
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <img
                    :src="product.catalogProductPhoto"
                    width="100px"
                    height="100px"
                    alt="error">
                </div>
                <div class="col-2">
                    {{ product.catalogProductName }}
                </div>
                <div class="col-3">
                    <button type="button" class="btn btn-primary" v-on:click="addCol">+</button>
                    {{ product.selectedProductKol }} шт.
                    <button type="button" class="btn btn-primary" v-on:click="subCol">-</button>
                </div>
                    <div class="col-2">
                {{ product.catalogProductPrice }} руб.
                </div>
                <div class="col-2">
                    <button type="button" class="btn btn-danger" @click="deleteItemFromCart">Удалить</button>
                </div>
            </div>
            <hr>
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
            this.product.selectedProductKol++;
        },
        subCol: function() {
            this.product.selectedProductKol--;
        },
        async deleteItemFromCart() {
            console.log(this.product.id);
            this.$api.cart.deleteItemFromCart(this.product.id);
        }
    }
}
</script>