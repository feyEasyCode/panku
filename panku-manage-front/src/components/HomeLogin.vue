
<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-main>
        <el-form :model="loginForm" ref="loginForm" label-width="100px" class="login-form">
          <el-form-item label="手机号" prop="mobile">
            <el-input type="mobile" v-model.number="loginForm.mobile"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('loginForm')">登   录</el-button>
            <el-button @click="resetForm('loginForm')">重   置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import login from '../request/login.js';
  export default login;
</script>

<style>
.el-form{
  padding-top: 75px;
  padding-right: 44px;
  background-color: white;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 460px;
  margin: 170px 0px 0px 304px;
  border-radius: 4px;
  height: 237px
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-button--primary {
  margin-right: 16px;
  width: 123px
}

.el-button--default {
  width: 123px;
  margin-left: 10px;
}

.el-aside {
  background-color: #FAFBFC;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #FAFBFC;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
  height: 100%;
  width: 100%;
  position: absolute;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
