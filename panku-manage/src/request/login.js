import { postAPI } from "../api/api";

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
                        "mobileNum": "13913888765",
                        "passWord": "admin"
                    }
                    postAPI(url, query).then(response => {
                        this.form = response.data;
                        console.info(">>>>>REDS>>>>>>" + response.data.code)
                        if (response.data.code == "0") {
                            _this.$router.push('HelloWord');
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