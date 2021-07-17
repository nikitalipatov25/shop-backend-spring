<template>

  <div class="container">
    <Header/>
      <h2>Login</h2>
      <p>
        <label>Username</label>
        <input v-model="username">
      </p>
      <p>
        <label >Password</label>
        <input v-model="password">
      </p>
      <button @click="oldlogin">Sign in</button>

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
    async login() {
      let payload = {
        "email": this.username,
        "password": this.password
      }
      let a = await this.$api.auth.login(payload);
      let token = a.data.token
      await this.$store.dispatch('loadToken', token)
      console.log(token)
    }
  }
}
</script>