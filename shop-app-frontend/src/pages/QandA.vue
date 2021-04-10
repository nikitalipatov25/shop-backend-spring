<template>
  <div class="q-and-a">
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
  name: 'QandA',
  components: {
    Header,
    Footer
  },
  data() {
    return {
      text: 'Частые вопросы'
    }
  }
}
</script>