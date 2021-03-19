import { postAPI } from "../api/api";
import {Message} from "element-ui";

export default {
    data() {
        return {
            loginForm: {
                domains: [{
                    value: ''
                }],
                email: ''
            }
        };
    },
    methods: {
        submitForm: function (formName) {
            let _this = this;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let url = "account/loginPhAndPwd"
                    let query = {
                        "mobileNum": this.loginForm.mobile,
                        "passWord": this.loginForm.password
                    }
                    postAPI(url, query).then(response => {
                        this.form = response.data;
                        console.info(">>>>>REDS>>>>>>" + response.data.code)
                        if (response.data.code == "0") {
                            _this.$router.push('HelloWorld');
                        } else {
                          Message.error("登录异常")
                        }
                    }).catch(err => {
                        console.log(err);
                    });
                    alert(JSON.stringify(this.form))
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
