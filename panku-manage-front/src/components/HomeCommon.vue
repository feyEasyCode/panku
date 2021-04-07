<template>
  <el-container style="height: 100%;width: 100%;position: absolute">
    <el-header style="height: 100px">
      <div>
        <div class="loginInfo">
          <div>{{userData.name}}</div>
          <div>退出</div>
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
            <el-submenu v-for="(breadcrumb,indexs) in breadcrumbs" :index="'-'+indexs" :key="indexs">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{breadcrumb.routerName}}</span>
              </template>
              <el-menu-item-group v-for="(subBreadcrumb, index) in breadcrumb.children" :index="indexs+'-'+index" :key="indexs+'-'+index">
                <el-menu-item @click="queryAllUser(subBreadcrumb)" :index="indexs+'-'+index">{{subBreadcrumb.routerName}}</el-menu-item>
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
          <router-view />
        </el-main>
      </el-container>
    </el-container>
    <el-footer>Footer</el-footer>
  </el-container>
</template>

<script>
export default {
  name: 'HomeCommon',
  data () {
    return {
      userData: {},
      breadcrumbs: {},
      customers:{}
    }
  },
  watch: {
    // 当路由发生改变时执行
    $route(to, from){
      this.getBreadcrumb(this.breadcrumbs)
    }
  },

  mounted () {
    this.userData = JSON.parse(localStorage.getItem("userInfo"))
    this.breadcrumbs = JSON.parse(window.localStorage.getItem("router"))
  },
  methods:{
    getBreadcrumb(item) {
      debugger
      // matched 包含当前路由的所有嵌套路径片段的路由记录<Array>
      let matched = this.$route.matched.filter(item => item.name)
      const first = matched[0]
      // 默认显示Home,可根据项目自行调整
      if (!this.isHome(first)) {
        matched = [{ path: '/home', meta: { title: '首页' }}].concat(matched)
      }
      this.list = matched
    },
    isHome(route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return name.trim().toLocaleLowerCase() === 'Home'
    },
    handleLink(item) {
      const { redirect, path } = item
      // 优先执行redirect指定路径
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(this.pathCompile(path))
    },
    pathCompile(path) {
      // 解决面包屑不支持:id的方式 https://github.com/PanJiaChen/vue-element-admin/issues/561
      const { params } = this.$route
      const toPach = pathToRegexp.compile(path)
      return toPach(params)
    },
    queryAllUser(item){
      debugger
      if (this.$router.history.current.name != item.name){
        this.$router.push({
          name: item.name
        });
      }
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
