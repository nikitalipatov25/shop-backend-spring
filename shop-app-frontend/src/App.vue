<template>
  <div id="app">
    <router-link to="/">Перейти к Index | </router-link>
    <router-link to="/catalog">Перейти к Catalog | </router-link>
    <router-link to="/productpage">Перейти к ProductPage | </router-link>
    <router-link to="/personalarea">Перейти к PersonalArea | </router-link>
    <router-link to="/cart">Перейти к Cart</router-link>
    <hr />
    <router-view></router-view>

  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
