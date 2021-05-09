<template>
  <div class="personal-area">
    <Header/>
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
            <div class="row">
              <div class="col-4" style="border-bottom: black">
                Nomer
              </div>
              <div class="col-4">
                Soder
              </div>
            </div>
            <orders-component
            v-for="order in orders"
            :key="order.orderId"
            :order="order"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../components/Header'
import ordersComponent from "@/components/ordersComponent";

export default {
  name: 'PersonalArea',
  components: {
    Header,
    ordersComponent
  },
  data() {
    return {
      text: 'Личный кабинет',
      fullName: '',
      phoneNumber: '',
      email: '',
      adress: '',
      role: '',
      orders: '',
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
    let a = await this.$api.orders.getOrders('cd668994-a73a-4da6-8f03-e7fe7034aa17')
    this.orders = a.data
    console.log(this.orders)
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