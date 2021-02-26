<template>
    <div class="cart-item">
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <img
                    :src="product.img"
                    alt=""
                    height="200px"
                    >
                </div>
                <div class="col-2">
                    {{ product.title }}
                </div>
                <div class="col-3">
                    <button v-on:click="addCol">Add</button>
                    {{ count }}
                    <button v-on:click="subCol">Sub</button>
                </div>
                    <div class="col-2">
                {{ product.price }}
                </div>
                <div class="col-2">
                    <button class="btn-warning">DeleteFromCart</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import products from '../mocks/products'

export default {
    data() {
        return {
            product: null,
            count: 1,
            temp: 0
        }
    },
    methods: {
        addCol: function() {
            this.count++;
        },
        subCol: function() {
            this.count--;
        }
    },
    created() {
                this.product = products.find(product => product.id == this.$route.params.id);
                this.temp = this.product.price
            }
}
</script>