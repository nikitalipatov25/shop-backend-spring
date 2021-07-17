<template>
  <div class="success">
    <h1>Congrats!!! You did it, {{userName}}!</h1>
    <button type="button">Logout</button>
  </div>
</template>

<script>
import {eventBus} from "@/main";

export default {
  name: 'Success',
  data() {
    return {
      userName : 'null'
    }
  },
  created() {
    eventBus.$emit('testLogin', data => {
      this.userName = data;
    })
  }
}
</script>