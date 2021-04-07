<template>
    <div class="index">
      <Header/>
        <div class="body">
          <h2>Наши акции</h2>
          <index-corusel/>
          <h2>Наш каталог</h2>
          <catalog-category/>
        </div>
      <Footer/>
    </div>
</template>

<script>
import Header from '../components/Header'
import Footer from '../components/FFooter'
import CatalogCategory from "@/components/IndexSmallMenu"
import IndexCorusel from '@/components/IndexCorusel'

export default {
    name: 'Index',
  components: {
    IndexCorusel,
    Header,
    Footer,
    CatalogCategory
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