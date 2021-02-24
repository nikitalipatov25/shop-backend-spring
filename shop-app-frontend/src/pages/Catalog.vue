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
import products from '../mocks/products'

export default {
    name: 'Catalog',
    components: { ProductsList },
    data() {
        return {
            products,
            text: 'This is catalog page'
        }
    }
}
</script>