import Vue from 'vue';
import Router from 'vue-router';

import Index from '@/views/Index'
import Catalog from '@/views/Catalog'
import ProductPage from '@/views/ProductPage'
import PersonalArea from '@/views/PersonalArea'
import Cart from '@/views/Cart.vue'
import Deals from "@/views/Deals";
import QandA from "@/views/QandA";
import About from "@/views/About";
import registration from "@/views/registration";
import success from "@/views/success";
import Home from "@/views/Home";
import Login from "@/views/Login";
import Register from "@/views/Register";

Vue.use(Router);

export const router = new Router({
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
          path: '/success',
          name: 'Success',
          component: success
      },
      {
          path: '/home',
          component: Home
      },
      {
          path: '/login',
          component: Login
      },
      {
          path: '/register',
          component: Register
      },
      {
          path: '/profile',
          name: 'profile',
          // lazy-loaded
          component: () => import('./views/Profile.vue')
      },
      {
          path: '/admin',
          name: 'admin',
          // lazy-loaded
          component: () => import('./views/BoardAdmin.vue')
      },
      {
          path: '/mod',
          name: 'moderator',
          // lazy-loaded
          component: () => import('./views/BoardModerator.vue')
      },
      {
          path: '/user',
          name: 'user',
          // lazy-loaded
          component: () => import('./views/BoardUser.vue')
      },
      {
          path: '/test',
          name: 'test',
          // lazy-loaded
          component: () => import('./views/Test')
      },
  ]  
})
