<template>
  <div id="app">
<!--    <img src="./assets/logo.png">-->
<!--    <HomeLogin/>-->
    <router-view/>
  </div>
</template>

<script>
import HomeLogin from "./components/HomeLogin";

export default {
  // name: 'login',
  // components: {
  //   HomeLogin
  // }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0px;
}
</style>
