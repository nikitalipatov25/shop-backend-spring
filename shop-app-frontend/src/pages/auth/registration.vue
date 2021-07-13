<template>
<div class="registration">
  <form>
    <div class="mb-3">
      <label  class="form-label">Введите имя</label>
      <input class="form-control"  v-model="userName">
    </div>
    <div class="mb-3">
      <label  class="form-label">Введите логин</label>
      <input class="form-control"  v-model="userLogin">
    </div>
    <div class="mb-3">
      <label  class="form-label">Введите пароль</label>
      <input class="form-control"  v-model="userPassword">
    </div>
    <div class="mb-3">
      <label  class="form-label">Введите телефон</label>
      <input class="form-control"  v-model="userPhone">
    </div>
    <div class="mb-3">
      <label  class="form-label">Введите адрес</label>
      <input class="form-control"  v-model="userAdress">
    </div>
  </form>
</div>
</template>

<script>
export default {
  name: 'Registration',
  data() {
    return {
      userName:"",
      userLogin:"",
      userPassword:"",
      userAdress:"",
      userPhone:""
    }
  },
  methods: {

  }
}
</script>