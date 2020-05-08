<template>
  <div>
    <el-button @click="logout">退出</el-button>
    <el-button @click="login">登录</el-button>
    <el-button @click="testmain">测试main</el-button>

    <div style="display: flex; margin-top: 20px; height: 100px;">
      {{msg}}
    </div>
  </div>
</template>

<script>
  export default {
    methods: {
      demo: function () {

      },
      login: function () {
        let url = this.HOST + "/login"
        console.log("登录", url)
        this.$post(url, {
          "username": "admin",
          "password": "admin"
        }).then(response => {
          console.log(response);
        }, error => {
          //console.log(error);
        })
      },
      logout: function () {
        console.log("发送请求")
        let url = this.HOST + "/logout"
        console.log("登出", url)
        this.$get(url, {}).then(response => {
          console.log(response);
        }, error => {
          //console.log(error);
        })
      },
      testmain:function () {
        console.log("发送请求")
        let url = this.HOST + "/main"
        console.log("testmain", url)
        this.$get(url, {}).then(response => {
          console.log(response);
        }, error => {
          //console.log(error);
        })
      }
    },
    data: () => ({
      show: true,
      msg: "消息"
    })
  }
</script>

<style>
  .transition-box {
    margin-bottom: 10px;
    width: 200px;
    height: 100px;
    border-radius: 4px;
    background-color: #409EFF;
    text-align: center;
    color: #fff;
    padding: 40px 20px;
    box-sizing: border-box;
    margin-right: 20px;
  }
</style>
