<template>
    <div class="cart">
        <h1>{{ text }}</h1>
            <div class="body">
                <cart-item />
            </div>
            <button type="button" v-on:click="check">CheckApi</button>
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
            temp: 0
            }
        },
        methods: {
        async check() {
        const data = await this.$api.catalog.getCatalog();
        console.log(data)
        }
        }
}
</script>