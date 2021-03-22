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
      <el-aside width="300px">Aside</el-aside>
      <el-container>
        <el-main>Main</el-main>
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
      userData: {
      }
    }
  },
  mounted () {
    // this.userData = this.$route.params.userData
    this.userData = JSON.parse(localStorage.getItem("userInfo"))
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-aside {
  background-color: #d3dce6;
}
body > .el-container {
  margin-bottom: 40px;
  height: 100%;
  width: 100%;
  position: absolute;
}
</style>
