<template>

  <div class="container">
    <Header/>
    <form>
      <h2>Login</h2>
      <p>
        <label>Username</label>
        <input v-model="username">
      </p>
      <p>
        <label >Password</label>
        <input v-model="password">
      </p>
      <button @click="testLogin">Sign in</button>
    </form>
    <Footer/>
  </div>

</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Foter";


export default {
  name: 'Login',
  components: {
    Header,
    Footer
  },
  data() {
    return {
      username: 'a@mail.ru',
      password: 'admin'
    }
  },
  methods: {
    async testLogin() {
      let payload = {
        "email": this.username,
        "password": this.password
      }
      let uid = await this.$api.auth.login(payload);
      console.log(uid)
    }
  }
}
</script>