import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld';

const router = new Router({
    mode:'history',
    routes: [
        // 下面的对象就是路由记录
        {
            path: '/HelloWorld',
            name:'HelloWorld',
            component: HelloWorld
        }
    ]
})

router.beforeEach((to, from, next) => {
    /* 必须调用 `next` */
})

router.beforeResolve((to, from, next) => {
    /* 必须调用 `next` */
})

router.afterEach((to, from) => {})