import Vue from 'vue'
import App from './App.vue'

import BootstrapVue from "bootstrap-vue"
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"

Vue.use(BootstrapVue)

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import 'jquery/dist/jquery.min.js'

import VueRouter from 'vue-router'
import router from './router/routerconfig'

import ApiPlugin from './plugins/api'
Vue.use(ApiPlugin)

Vue.use(VueRouter)

export const eventBus = new Vue();

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
