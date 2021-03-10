<template>
    <div class="cart">
        <h1>{{ text }}</h1>
            <div class="body">
                <div class="container">
                    <div class="row">
                        <div class="col-9">
                            <cart-item
                            v-for="product in products"
                            :key="product.id"
                            :product="product"

                            />
                        </div>
                        <div class="col-3">
                            <p>Итоговая стоимость:</p>
                            <p>Общее кол-во товара:</p>
                            <p>Общая стоимость:</p>
                            <p>Скидка:</p>
                            <p>Итого:</p>
                            <button type="button" class="btn btn-warning">Оформить</button>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</template>

<script>
import CartItem from '../components/CartItem'


export default {
    name: 'Cart',
    components: {
    CartItem
    },
    data() {
        return {
            text: 'Корзина',
            count: 1,
            productUUID: '',
            products: [],
            productsFromServer: {},
            product: {},
            productsUUID: {},
            }
        },
  async mounted() {
      this.productsFromServer = await this.$api.cart.getCart();
      this.products = this.productsFromServer.data.content;
    }

}
</script>

<style>
.cart h1 {
    text-align: left;
    margin-left: 10px;
}
</style>