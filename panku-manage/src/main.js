import { createApp } from 'vue'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import App from './App.vue';
import router from "./router/index";
import axios from 'axios'
import VueAxios from 'vue-axios'

const app = createApp(App)
app.use(ElementPlus)
app.use(VueAxios, axios)
app.use(router)
app.mount('#app')

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
