// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI, {Message} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import {postAPI} from "./api/api";

Vue.use(Vuex)
Vue.use(VueAxios, axios)
Vue.use(ElementUI);

Vue.config.productionTip = false

//用来获取后台拿到的路由
var getRouter

function getBreadcrumb(callback){
  let url = "api/common/queryBreadcrumb";
  let query = {};
  postAPI(url, query).then(response => {
    console.info(">>>>>queryBreadcrumb>>>>>>" + JSON.stringify(response.data.data))
    if (response.data.code == "0") {
      getRouter = response.data.data.breadcrumbs//后台拿到路由
      saveObjArr('router', getRouter) //存储路由到localStorage
      callback();
      // this.breadcrumbs = response.data.data.breadcrumbs;
    } else {
      Message.error("获取菜单数据异常")
    }
  }).catch(err => {
    Message.error("获取菜单数据异常")
  });
}

function saveObjArr(name, data) { //localStorage 存储数组对象的方法
  localStorage.setItem(name, JSON.stringify(data))
}

function getObjArr(name) { //localStorage 获取数组对象的方法
  return JSON.parse(window.localStorage.getItem(name));

}

function routerGo(to, next) {
  getRouter = filterAsyncRouter(getRouter) //过滤路由
  router.addRoutes(getRouter) //动态添加路由
  global.antRouter = getRouter //将路由数据传递给全局变量，做侧边栏菜单渲染工作
  next({ ...to, replace: true })
}

function filterAsyncRouter(asyncRouterMap) { //遍历后台传来的路由字符串，转换为组件对象
  const accessedRouters = asyncRouterMap.filter(route => {
    if (route.component) {
      route.component = ()=> import(route.component)

    }
    if (route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })

  return accessedRouters
}

router.beforeEach(function (to, from, next) {

  let userData = localStorage.getItem('userInfo');
  /** 登录拦截（对以下 地址不做拦截） */
  let pathList = ['HomeLogin','token']
  let isLogin = localStorage.getItem('isLogin')
  if (to.path === 'token' || to.path === '/HomeLogin') {
      next()
  } else {
    if (isLogin === '0') {
      if (!getRouter) {
        if (!getObjArr('router')) {
          getBreadcrumb(()=>{
            routerGo(to, next)//执行路由跳转方法
          });
        } else {
          getRouter = getObjArr('router')//拿到路由
          routerGo(to, next)
        }
      }

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
