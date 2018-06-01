<template>
<div>
  <img src="../assets/logo.jpeg" height="200" width=90%/>
     <mt-field label="用户名" placeholder="请输入真实姓名" v-model="username" class="button"></mt-field>
     <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password" class="button" ></mt-field>
    <mt-button type="primary" @click.native="handleClick" size="large" class="button" >登录</mt-button>
  <span v-on:click="ToRegister">没有账号？马上注册</span>
  </div>
</template>

<script>
  import md5 from 'js-md5';
  export default {
    name: 'login',
    data () {
      return {
        username:'',
        password:'',
        $toast:''
      }
    },
    created (){
      console.log(this);
    },
    methods:{
      handleClick:function () {
        var that = this;
        this.axios.post('/account/login', {
          name: that.username,
          pwd: md5(that.password)
        }).then(function (res) {
          console.log(res);
          if (res.data.success == false){
            that.$toast({
              message: '用户名与密码不匹配',
              iconClass: 'icon icon-success'
            });
          } else{
            let uid = res.data.data;
            that.$toast({
              message: '登录成功',
              iconClass: 'icon icon-success'
            });
            if(that.username == 'admin'){
              that.$router.push({path:`/adminnavigation`})}
            else{that.$router.push({path:`/navigation/${uid}`})}
            // that.$router.push({path:`/label/${uid}`})
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      ToRegister:function () {
        this.$router.push({path:'/register'})
      }
    }
  }
</script>

<style scoped>
  span{cursor:pointer;}
  span:hover{color:#41b883;}
  .button{
    width: 80%;
    margin-left:auto;
    margin-right:auto;
  }
</style>
