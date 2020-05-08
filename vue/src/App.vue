<template>
  <div>
    <el-button @click="sendRequest">发送请求</el-button>
    <el-button @click="login">登录</el-button>

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
          "password": "admin1"
        }).then(response => {
          console.log(response);
        }, error => {
          //console.log(error);
        })
      },
      sendRequest: function () {
        console.log("发送请求")
        let url = this.HOST + "/test"
        axios.get(url, {
          params: {
            param: "param123123"
          }
        }).then(response => {
          console.log(response.data);
        }, error => {
          console.log(error);
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
