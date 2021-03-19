import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import HomeLogin from '../components/HomeLogin'

Vue.use(Router)

export default new Router({
  mode:"history",
  routes: [
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'HomeLogin',
      component: HomeLogin
    }
  ]
})
