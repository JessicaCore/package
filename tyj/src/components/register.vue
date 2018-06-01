<template>
  <div>
  <mt-field label="用户名" placeholder="请输入真实姓名" type="tel" v-model="username" class="button"></mt-field>
  <mt-field label="密码" placeholder="请输入密码" type="password"  v-model.lazy="passwordModel" class="button" ></mt-field>
  <span class="wrong">{{passwordValidate}}</span>
  <mt-field label="确认密码" placeholder="请确认密码" type="password" v-model.lazy="passwordcheckModel" class="button"></mt-field>
  <span class="wrong">{{passwordCheckValidate}}</span>
  <mt-field label="手机号" placeholder="请输入手机号" type="phone" v-model.lazy="phone1" class="button"></mt-field>
  <span class="wrong">{{phone}}</span>
  <mt-field label="邮箱" placeholder="请输入邮箱" type="email" v-model.lazy="email1" class="button"></mt-field>
  <span class="wrong" v-if="email1">{{email}}</span>
  <mt-button type="primary" @click.native="handleClick" size="large" class="button" v-if="flag">注册</mt-button>
  <mt-button type="primary" @click.native="handleClick" size="large" class="button" v-else disabled>注册</mt-button>
  <span v-on:click="ToLogin">已有账号？马上登录</span>
</div>
</template>

<script>
  import md5 from 'js-md5';
  export default {
    data() {
      return {
        showLogin: true,
        showRegister: false,
        showTishi: false,
        tishi: '',
        username: '',
        password: '',
        newUsername: '',
        newPassword: '',
        phone1: '',
        email1: '',
        passwordModel: '',
        passwordcheckModel: '',
        passwordFlag: '',
        flag: false,
      }
    },
    methods: {
      handleClick: function () {
        let that = this;
        this.axios.get('/account/isRepeat/' + this.username).then(function (res) {
          // console.log(res)
          md5("passwordModel");
          if (res.data.success) {
            that.axios.post('/account/register', {
              name: that.username,
              pwd: md5(that.passwordModel),
              email: that.email1,
              phone: that.phone1
            }).then(function (res) {
              console.log(res)
              if (res.data.success) {
                that.$toast({
                  message: '注册成功',
                  iconClass: 'icon icon-success'
                });
                that.$router.push({path: '/login'})
              } else {
                alert('false')
              }
            }).catch(function (error) {
              console.log(error);
            });
          } else {
            alert('账号重复')
          }
        })
      },
      CalcuMD5 : function (pwd) {
        pwd = pwd.toUpperCase();
        pwd = md5(pwd);
        return pwd;
      },
      ToLogin: function () {
        this.$router.push({path: '/login'})
      }
    },
    computed: {
      passwordValidate: function () {
        var errorText;
        if (!/^[0-9A-Za-z]{6,15}$/.test(this.passwordModel)) {
          errorText = '密码少于6位'
          this.flag = false
        } else {
          errorText = ''
        }
        if (!this.passwordFlag) {
          errorText = ''
          this.passwordFlag = true
          this.flag = true
        }
        return errorText
      },
      passwordCheckValidate: function () {
        var errorText;
        if (this.passwordcheckModel !== this.passwordModel) {
          errorText = '两次密码不匹配'
          this.flag = false
        }
        else {
          errorText = ''
        }

        if (!this.passwordFlag) {
          errorText = ''
          this.passwordFlag = true
          this.flag = true
        }
        return errorText
      },
      phone: function () {
        var errorText;
        if (!(11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/.test(this.phone1))) {
          errorText = '手机号格式不正确'
          this.flag = false
        } else {
          errorText = ''
        }

        if (!this.phoneFlag) {
          errorText = ''
          this.phoneFlag = true
          this.flag = true
        }
        return errorText
      },
      email: function () {
        var errorText;
        if (!/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.email1)) {
          errorText = '邮箱格式不正确'
          this.flag = false
        } else {
          errorText = ''
          this.flag = true
          this.phoneFlag = true

        }
        /*if (!this.phoneFlag) {
          errorText = ''
          this.phoneFlag = true
          this.flag = true
        }*/
        return errorText
      }
    },


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
  .wrong{
    color: #DC143C;
  }
</style>
