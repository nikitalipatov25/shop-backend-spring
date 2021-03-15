<template>
  <div class="personal-area">
    <div class="body">
      <h1>{{ text }}</h1>
      <div class="row">
        <div class="col-2"></div>
        <div class="col-2">
          <h2>Профиль</h2>
        </div>
        <div class="col-2">
          <p>ФИО: {{ fullName }}</p>
          <p>Телефон: {{ phoneNumber }}</p>
          <p>Адрес: {{ adress }}</p>
        </div>
        <div class="col-2">
          <p>Эл. почта: {{ email }}</p>
          <p>Роль: {{ role }}</p>
          <a href="#">Редактировать</a>
        </div>
      </div>
      <hr>
      <div class="orders">
        <div class="row">
          <div class="col-4">
            <h2>Заказы</h2>
        </div>
          <div class="col-8">
            <ul class="list-group list-group-horizontal">
              <li class="list-group-item">Номер заказа</li>
              <li class="list-group-item">Итоговая стоимость</li>
              <li class="list-group-item">Тип заказа</li>
              <li class="list-group-item">Статус заказа</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PersonalArea',
  data() {
    return {
      text: 'Личный кабинет',
      fullName: '',
      phoneNumber: '',
      email: '',
      adress: '',
      role: ''
    }
  },
  created: async function() {
    const userFromServer = await this.$api.user.getSingleUser('cd668994-a73a-4da6-8f03-e7fe7034aa17');
    console.log(userFromServer);
    this.fullName = userFromServer.data.fullName;
    this.phoneNumber = userFromServer.data.phoneNumber;
    this.email = userFromServer.data.email;
    this.adress = userFromServer.data.adress;
    this.role = userFromServer.data.role;
  }
}
</script>
<style>
.personal-area h1 {
  margin-left: 10px;
  text-align: left;
}
.personal-area h2 {
  text-align: right;
}
.col-2 {
  text-align: left;
}
</style>