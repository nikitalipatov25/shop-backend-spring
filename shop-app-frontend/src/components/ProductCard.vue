<template>
    <div class="container">
        <div class="card" style="width: 18rem;">
            <div class="toProductPage" @click="$router.push({ name: 'product-page', params: { id: product.id } })">
                <img
                class="card-img-top"
                :src="product.productPhoto"
                alt="">
                    <div class="card-body">
                        <h5 class="card-title">{{ product.productName }}</h5>
                    </div>
            </div>
                <button class="btn btn-primary" @click="addToCart"> {{product.productPrice}} руб.</button>
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
    methods: {
    async addToCart() {
    const serverProduct = await this.$api.catalog.getCatalogItemByUUID(this.product.id);
    console.log(serverProduct.data);
    const productToCart = {
    "catalogProductName": serverProduct.data.productName,
    "catalogProductPrice": serverProduct.data.productPrice,
    "selectedProductKol": serverProduct.data.productKol,
    "catalogProductPhoto": serverProduct.data.productPhoto
    }
    console.log(productToCart);
    this.$api.cart.addItemToCart(productToCart);
    }
    }
}
</script>

<style>
.container {
width: auto;
}
</style>


