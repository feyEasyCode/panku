// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(Vuex)
Vue.use(VueAxios, axios)
Vue.use(ElementUI);

Vue.config.productionTip = false

router.beforeEach(function (to, from, next) {

  let userData = localStorage.getItem('userInfo');
  /** 登录拦截（对以下 地址不做拦截） */
  let pathList = ['HomeLogin','token']
  let isLogin = localStorage.getItem('isLogin')
  if (to.path === 'token' || to.path === '/HomeLogin') {
      next()
  } else {
    if (isLogin === '0') {
      next()
    } else {
      next({
        path: '/HomeLogin'
      })
    }
  }
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
