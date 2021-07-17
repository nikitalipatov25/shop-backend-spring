<template>
  <div class="about">
    <Header/>
    <div class="body">
      <h1>{{text}}</h1>
      <div class="project-info">
        <p>Интернет-магазин "Hand-made магазин "Любый питомец" реализует товары ручного производства для собак, кошек, рыбок и гызунов и является проектом выданным
        во производственной практики на предприятии ООО "Рексофт".</p>
        <p>Стек технологий:</p>
          <ul>
            <li>ЯП Java</li>
            <li>Spring Framework</li>
            <li>Vue.js</li>
            <li>Bootstrap</li>
            <li>СУБД PostgreSQL</li>
          </ul>
        <p>Автор проекта: Липатов Никита Николаевич, студент группы 4-15ПКС</p>
        <p>Соавтор: Байков Станислав Эдуардович, студент группы 4-15ПКС (c 12.07.21)</p>
        <p>Под руководством:</p>
        <p>gitHub проекта: https://github.com/nikitalipatov25/shop-app</p>
      </div>
    </div>
    <Footer/>
  </div>

</template>

<script>
import Header from '../components/Header'
import Footer from '../components/Foter'

export default {
  name: 'About',
  components: {
    Header,
    Footer
  },
  data() {
    return {
      text: 'О магазине'
    }
  }
}
</script>