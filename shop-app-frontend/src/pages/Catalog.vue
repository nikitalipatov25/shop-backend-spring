<template>
    <div class="index">
        <h1>{{ text }}</h1>
            <div class="body">
                <div class="product-list">
                    <product-card
                    v-for="product in products"
                    :key="product.id"
                    :product="product"
                    />
                </div>
            </div>
    </div>
</template>

<script>
import ProductCard from '../components/ProductCard'

export default {
    name: 'Catalog',
    components: {
    ProductCard
    },
    data() {
        return {
            products: [],
            productsFromServer: {},
            text: 'Все товары'
        }
    },
    created: async function () {
        this.productsFromServer = await this.$api.catalog.getCatalog();
        this.products = this.productsFromServer.data.content;
    }
}
</script>

<style>
.product-list {
    display: flex;
    flex-direction: row;
}
.index h1 {
    margin-left: 10px;
    text-align: left;
}
</style>