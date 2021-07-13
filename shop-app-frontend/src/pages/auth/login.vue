<template>

  <div class="container">
    <form class="form-signin">
      <h2 class="form-signin-heading">Login</h2>
      <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Username" required v-model="username">
      </p>
      <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required v-model="password">
      </p>
      <button class="btn btn-lg btn-primary btn-block" type="submit" @click="testLogin">Sign in</button>
    </form>
  </div>

</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    async testLogin() {
      await this.$api.auth.login(this.username, this.password)
    }
  }
}
</script>