import VueRouter from 'vue-router'

import Index from '../pages/Index.vue'
import Catalog from '../pages/Catalog.vue'
import ProductPage from '../pages/ProductPage.vue'
import PersonalArea from '../pages/PersonalArea.vue'
import Cart from '../pages/Cart.vue'
import Deals from "@/pages/Deals";
import QandA from "@/pages/QandA";
import About from "@/pages/About";
import registration from "@/pages/auth/registration";
import login from "@/pages/auth/login";

export default new VueRouter({
    mode: 'history',
  routes: [
      {
          path: '/',
          name: 'main-page',
          component: Index
      },
      {
          path: '/catalog',
          name: 'catalog-page',
          component: Catalog
      },
      {
          path: '/productpage/:id',
          name: 'product-page',
          component: ProductPage
      },
      {
          path: '/personalarea',
          name: 'personal-area',
          component: PersonalArea
      },
      {
          path: '/cart',
          name: 'cart',
          component: Cart
      },
      {
          path: '/deals',
          name: 'deals',
          component: Deals
      },
      {
          path: '/questions',
          name: 'questions',
          component: QandA
      },
      {
          path: '/about',
          name: 'about',
          component: About
      },
      {
          path: '/registration',
          name: 'Registration',
          component: registration
      },
      {
          path: '/login',
          name: 'Login',
          component: login
      },
  ]  
})
