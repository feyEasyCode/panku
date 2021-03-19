// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(VueAxios, axios)
Vue.use(ElementUI);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

axios({
  method: "POST",
  url: "http://localhost:8080/token/getToken",
  data: {
  },
  headers: {
    'Content-Type': 'application/json'
  }
}).then(response => {
  sessionStorage.setItem("jwt",response.data.data.jwt);
  axios.defaults.headers.common['jwt'] = response.data.data.jwt
}).catch(err => {
  console.log(err);
});

