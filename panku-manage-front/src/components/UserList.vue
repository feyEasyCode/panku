<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="headImg"
      label="头像"
      width="100">
    </el-table-column>
    <el-table-column
      prop="userId"
      label="账号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="120">
    </el-table-column>
    <el-table-column
      prop="mobile"
      label="手机号"
      width="120">
    </el-table-column>
    <el-table-column
      prop="email"
      label="邮箱"
      width="260">
    </el-table-column>
    <el-table-column
      prop="userType"
      label="类型"
      width="60">
    </el-table-column>
    <el-table-column
      prop="userStatus"
      label="状态"
      width="60">
    </el-table-column>
    <el-table-column
      prop="createTime"
      label="注册时间"
      width="160">
    </el-table-column>
    <el-table-column
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        <el-button type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { postAPI } from "../api/api";
import {Message} from "element-ui";

export default {
  mounted () {
    this.getUserList();
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    getUserList(){
      let url = "api/account/queryAllUsers";
      let query = {};
      postAPI(url, query).then(response => {
        if (response.data.code == "0") {
          this.tableData = response.data.data;
        } else {
          Message.error("获取用户列表数据异常")
        }
      });
    }
  },
  data() {
    return {
      tableData: []
    }
  }
}
</script>
