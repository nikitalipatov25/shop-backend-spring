<template>
    <div class="cart">
        <h1>{{ text }}</h1>
            <div class="body">
                <cart-item :product="product"/>
            </div>

    </div>
</template>

<script>
import CartItem from '../components/CartItem'

export default {
    name: 'Cart',
    components: { CartItem },
    data() {
        return {
            text: 'This is cart page',
            count: 1,
            productUUID: '',
            product: {}
            }
        },
        created: async function() {
        this.productUUID = this.$route.params.id;
              this.product = await this.$api.catalog.getCatalogItemByUUID(this.productUUID);
              console.log(this.product.data);

        }
}
</script>