<template>
  <div>
    用户名:<input type="text" v-model="loginForm.username" placeholder="请输入用户名"/>
    <br><br>
    密码： <input type="password" v-model="loginForm.password" placeholder="请输入密码"/>
    <br><br>
    <button v-on:click="test">登录</button>
  </div>
</template>

<script>
export default {
  name: 'Test',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    test () {
      this.$axios
        .post('/test', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$router.replace({path: '/index3'})
          } else {
            this.$router.replace({path: '/index4'})
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>
