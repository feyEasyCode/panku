import Vue from 'vue'
import Router from 'vue-router'
import HomeCommon from '../components/HomeCommon'
import HomeLogin from '../components/HomeLogin'
import HelloWorld from '../components/TestPage'

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
      path: '/getToken',
      name: 'token'
    },
    {
      path: '/HomeCommon',
      name: 'HomeCommon',
      component: HomeCommon
    },
    {
      path: '/',
      name: 'HomeLogin',
      component: HomeLogin
    }
  ]
})
