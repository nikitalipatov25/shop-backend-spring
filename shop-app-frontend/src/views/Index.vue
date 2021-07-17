<template>
    <div class="index">
      <Header/>
        <div class="body">
          <h2>Наши акции</h2>
          <index-corusel/>
          <h2>Наш каталог</h2>
          <catalog-category/>
          <h2>Новинки</h2>
          Work in progress...
          <h2>Популярные товары</h2>
          Work in progress...
        </div>
      <Footer/>
    </div>
</template>

<script>
import Header from '../components/Header'
import Footer from '../components/Foter'
import CatalogCategory from "@/components/IndexSmallMenu"
import IndexCorusel from '@/components/IndexCorusel'

export default {
    name: 'Index',
  components: {
    IndexCorusel,
    Header,
    Footer,
    CatalogCategory,
  }
}
</script>

<style>
.catalog-category {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
</style>