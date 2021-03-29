<template>
  <el-container style="height: 100%;width: 100%;position: absolute">
    <el-header style="height: 100px">
      <div>
        <div class="loginInfo">
          <h2> 欢迎 ~ {{userData.name}}</h2>
        </div>
      </div>
    </el-header>
    <el-container>
      <el-aside width="300px">
        <el-row class="300px">
        <el-col :span="12">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-submenu v-for="(breadcrumb,indexs) in breadcrumbs" :index="indexs" :key="breadcrumb">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{breadcrumb.breadcrumbName}}</span>
              </template>
              <el-menu-item-group v-for="(subBreadcrumb,index) in breadcrumb.subBreadcrumb" :key="index">
                <el-menu-item @click="queryAllUser(subBreadcrumb.url)" :index="indexs+'-'+index">{{subBreadcrumb.breadcrumbName}}</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
        </el-row>
      </el-aside>
      <el-container>
        <el-main>
          <div class="breadcrumb">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>活动管理</el-breadcrumb-item>
              <el-breadcrumb-item>活动列表</el-breadcrumb-item>
              <el-breadcrumb-item>活动详情</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
        </el-main>
      </el-container>
    </el-container>
    <el-footer>Footer</el-footer>
  </el-container>
</template>

<script>
import { postAPI } from "../api/api";
import {Message} from "element-ui";

export default {
  name: 'HomeCommon',
  data () {
    return {
      userData: {},
      breadcrumbs: {},
      customers:{}
    }
  },
  mounted () {
    // this.userData = this.$route.params.userData
    this.userData = JSON.parse(localStorage.getItem("userInfo"))
    this.getBreadcrumb()
  },
  methods:{
    getBreadcrumb(){
      let url = "api/common/queryBreadcrumb";
      let query = {};
      postAPI(url, query).then(response => {
        console.info(">>>>>queryBreadcrumb>>>>>>" + JSON.stringify(response.data.data))
        if (response.data.code == "0") {
          this.breadcrumbs = response.data.data.breadcrumbs;
        } else {
          Message.error("获取菜单数据异常")
        }
      }).catch(err => {
        Message.error("获取菜单数据异常")
      });
    },
    queryAllUser(urls){
      let url = urls;
      let query = {};
      postAPI(url, query).then(res => {
        console.info(">>>>>query All user>>>>>>" + JSON.stringify(res.data.data))
        if (res.data.code == "0") {
          this.customers = res.data.data;
        } else {
          Message.error("获取用户数据异常")
        }
      }).catch(err => {
        Message.error("获取用户数据异常")
      });
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-header, .el-footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-aside {
  background-color: #d3dce6;
}
.el-aside .el-row{
  height: 100%;
}
.el-aside .el-row .el-menu{
  height: 100%;
}
body > .el-container {
  margin-bottom: 40px;
  height: 100%;
  width: 100%;
  position: absolute;
}
.el-col-12 {
  width: 100%;
  height: 100%;
  text-align: left;
}
</style>
