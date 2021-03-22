/****   request.js   ****/
// 导入axios
import axios from 'axios'
// 使用element-ui Message做消息提醒
import { Message} from 'element-ui';
//1. 创建新的axios实例，
const service = axios.create({
    // 公共接口--这里注意后面会讲
    // baseURL: process.env.BASE_API,
    baseURL: 'http://localhost:8080/',
    // 超时时间 单位是ms，这里设置了3s的超时时间
    timeout: 3 * 1000
})

// 添加超时后的处理（axios中需要你根据error信息来进行判断）
/*axios().catch(error => {
    const { message } = error;
    if (error.code === 'ECONNABORTED' && message.indexOf('timeout')> -1){
        // 超时处理，可以直接弹出错误或者重新发起一次请求
        alert("请求超时！请检查网络问题");
        //  let newHttp= new Promise(function (resolve){
        //      resolve()
        //  })
        //  newHttp实例执行完成后会再次执行
        //  返回一个promise实例，同时重新发起请求，config请求配置，包扩请求头和请求参数
        //  return newHttp.then(function (){
        //      return  axios.create({baseURL: 'https://some-domain.com/api/',timeout: 5000});
        //  })

    }
    // 若不是超时,则返回未错误信息
    return Promise.reject(error);
})*/

// 2.请求拦截器
service.interceptors.request.use(config => {
    //发请求前做的一些处理，数据转化，配置请求头，设置token,设置loading等，根据需求去添加
    config.data = JSON.stringify(config.data); //数据转化,也可以使用qs转换
    config.headers = {
        'Content-Type':'application/json' //配置请求头
    }
    //注意使用token的时候需要引入cookie方法或者用本地localStorage等方法，推荐js-cookie
    // const jwt = getCookie('名称');//这里取token之前，你肯定需要先拿到token,存一下
    const jwt = sessionStorage.getItem("jwt");
    console.info(">>>>>>JWT>>>>>" + jwt);
    if(jwt){
        // config.params = {'jwt':jwt} //如果要求携带在参数中
        config.headers.jwt= jwt; //如果要求携带在请求头中
    }
    return config
}, error => {
    Promise.reject(error)
})

// 3.响应拦截器
service.interceptors.response.use(response => {
    //接收到响应数据并成功后的一些共有的处理，关闭loading等

    return response
}, error => {
    /***** 接收到异常响应的处理开始 *****/
    if (error && error.response) {
        // 1.公共错误处理
        // 2.根据响应码具体处理
        switch (error.response.status) {
            case 400:
              Message.error = '错误请求'
                break;
            case 401:
              Message.error = '未授权，请重新登录'
                break;
            case 403:
              Message.error = '拒绝访问'
                break;
            case 404:
              Message.error = '请求错误,未找到该资源'
                window.location.href = "/NotFound"
                break;
            case 405:
              Message.error = '请求方法未允许'
                break;
            case 408:
              Message.error = '请求超时'
                break;
            case 500:
              Message.error = '服务器端出错'
                break;
            case 501:
              Message.error = '网络未实现'
                break;
            case 502:
              Message.error = '网络错误'
                break;
            case 503:
              Message.error = '服务不可用'
                break;
            case 504:
              Message.error = '网络超时'
                break;
            case 505:
              Message.error = 'http版本不支持该请求'
                break;
            default:
              Message.error = `连接错误${error.response.status}`
        }
    } else {
        // 超时处理
        if (JSON.stringify(error).includes('timeout')) {
            Message.error('服务器响应超时，请刷新当前页')
        }
      Message.error('连接服务器失败')
    }

    Message.error(error.message)
    /***** 处理结束 *****/
    //如果不需要错误处理，以上的处理过程都可省略
    return Promise.resolve(error.response)
})
//4.导入文件
export default service
