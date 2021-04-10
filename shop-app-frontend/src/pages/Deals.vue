<template>
  <div class="deals">
    <Header/>
    <div class="body">
      <h1>{{text}}</h1>
      Work in progress...
    </div>
    <Footer/>
  </div>

</template>

<script>
import Header from '../components/Header'
import Footer from '../components/Foter'

export default {
  name: 'Deals',
  components: {
    Header,
    Footer
  },
  data() {
    return {
      text: 'Скидки и акции'
    }
  }
}
</script>