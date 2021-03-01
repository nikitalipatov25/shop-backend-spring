<template>
    <div class="index">
        <h1>{{ text }}</h1>
            <div class="body">
                <products-list :products="products" />
            </div>
    </div>
</template>

<script>
import ProductsList from '../components/ProductsList'

export default {
    name: 'Catalog',
    components: { ProductsList },
    data() {
        return {
            products: [],
            productsFromServer: {},
            text: 'This is catalog page'
        }
    },
    created: async function () {
        this.productsFromServer = await this.$api.catalog.getCatalog();
        this.products = this.productsFromServer.data.content;
    }
}
</script>