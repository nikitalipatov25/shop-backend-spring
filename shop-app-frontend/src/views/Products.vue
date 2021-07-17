<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
  </div>
</template>

<script>
import ProductService from '../services/product.service';
export default {
  name: 'Products',
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    ProductService.getProducts().then(
        response => {
          this.content = response.data.content;
        },
        error => {
          this.content =
              (error.response && error.response.data && error.response.data.message) ||
              error.message ||
              error.toString();
        }
    );
  }
};
</script>