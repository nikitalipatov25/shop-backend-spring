import Vue from 'vue'
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import 'jquery/dist/jquery.min.js'

import VueRouter from 'vue-router'
import router from './router/routerconfig'

Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
