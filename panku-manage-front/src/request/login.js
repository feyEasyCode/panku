import { postAPI } from "../api/api";
import {Message} from "element-ui";

export default {
    data() {
        return {
            loginForm: {
            },
          userData: {
            name:''
          }
        };
    },
    methods: {
        submitForm: function (formName) {
          let that = this;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let url = "account/loginPhAndPwd"
                    let query = {
                        "mobileNum": this.loginForm.mobile,
                        "passWord": this.loginForm.password
                    }
                    postAPI(url, query).then(response => {
                        console.info(">>>>>REDS>>>>>>" + response.data.code)
                        if (response.data.code == "0") {
                          that.userData = response.data.data;
                          if (!localStorage.getItem("userId",that.userData.userId)){
                            localStorage.setItem("userId",that.userData.userId);
                            localStorage.setItem("isLogin",0);
                            localStorage.setItem('userInfo', JSON.stringify(that.userData));
                          }
                          that.$router.push({
                            //path:'/HomeCommon',
                            name: 'HomeCommon',
                            params: {
                              userData:that.userData
                            }
                          });
                        } else {
                          Message.error("登录异常")
                        }
                    }).catch(err => {
                      Message.error("登录异常")
                    });
                } else {
                    Message.error("登录异常")
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
