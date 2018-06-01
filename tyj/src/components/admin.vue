<template>
  <div>
    <mt-header :title="username" fixed="">
      <router-link to="/userlist" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
      <!--<mt-button icon="more" slot="right"></mt-button>-->
    </mt-header>

    <p>日期：{{time}}</p>
    <p>正确率：{{accuracy}}%</p>
    <img :src='src' height="70" width=90%/>
    <textarea name="" id="" cols="30" rows="10" v-model="introduction" class="shuru" placeholder="内容" ></textarea>
    <div class="btn">
      <mt-button type="primary" @click.native="handleClick1" size="large"  class="button">Corrcet</mt-button>
      <mt-button type="primary" @click.native="handleClick" size="large" class="button">Error</mt-button>
    </div>

  </div>
</template>

<script>
    export default {
        name: "admin",
      data() {
        return {
          introduction: '',
          count: '',
          src:'',
          id:'',
          content:'',
          username:'',
          time:'',
          updatetime:'',
          accuracy:''
        }
      },
      methods: {
        handleClick: function () {
          var that = this;
          console.log(that.introduction)
          this.axios.post('/label/error?uid='+that.uid, {
            id: that.id,
            content: that.introduction,
            time:that.time
          }).then(function (res) {
            console.log(res)
            if (res.data.success){
              that.$toast({
                message: '重置成功',
                iconClass: 'icon icon-success'
              });
              that.axios.get('/label/admingetRandom?uid='+that.uid).then(function (res) {
                console.log(res)
                if (res.data.success){
                  that.src = res.data.data.image
                  that.id = res.data.data.id
                  that.introduction = res.data.data.content
                  that.time=res.data.data.updatetime
                  //  that.username=res.data.data.name
                }else{
                  that.$router.push({path:`/userlist/${that.uid}`})
                  that.$toast({
                    message: '图片已审核完毕',
                    iconClass: 'icon icon-success',
                    position: 'bottom'
                  });
                }
              })
              that.introduction = ''
              that.axios.get('/label/admincount?uid='+that.uid).then(function (res) {
                console.log(res.data.data)
                that.count = res.data.data
              })
              that.axios.get('/label/accuracy?uid='+that.uid).then(function (res) {
                console.log(res.data.data)
                that.accuracy = res.data.data
              })
            }
          })
        },
        handleClick1: function () {
          var that = this;
          console.log(that.introduction)
          this.axios.post('/label/adminsave?uid='+that.uid, {
            id: that.id,
            content: that.introduction,
            time:that.time
          }).then(function (res) {
            console.log(res)
            if (res.data.success){
              that.$toast({
                message: '操作成功',
                iconClass: 'icon icon-success'
              });
              that.axios.get('http:///label/admingetRandom?uid='+that.uid).then(function (res) {
                console.log(res)
                if (res.data.success){
                  that.src = res.data.data.image
                  that.id = res.data.data.id
                  that.introduction = res.data.data.content
                  that.time=res.data.data.updatetime
                //  that.username=res.data.data.name
                }else{
                  that.$router.push({path:`/adminnavigation`})
                  that.$toast({
                    message: '图片已审核完毕',
                    iconClass: 'icon icon-success',
                    position: 'bottom'
                  });
                }
              })
              that.introduction = ''
              that.axios.get('/label/admincount?uid='+that.uid).then(function (res) {
                console.log(res.data.data)
                that.count = res.data.data
              })
              that.axios.get('/label/accuracy?uid='+that.uid).then(function (res) {
                console.log(res.data.data)
                that.accuracy = res.data.data
              })
            }
          })
        },
      },
      created:function () {
        var that = this;
        that.uid = that.$route.params.id;
        that.username = that.$route.params.name;
        this.axios.get('/label/admingetRandom?uid='+that.uid).then(function (res) {
          console.log(res)
          if (res.data.success){
            that.src = res.data.data.image
            that.id = res.data.data.id
            that.introduction = res.data.data.content
            that.time=res.data.data.updatetime
           // that.username=res.data.data.name
          }else{
            that.$router.push({path:`/adminnavigation`})
            that.$toast({
              message: '图片已审核完毕',
              iconClass: 'icon icon-success',
              position: 'bottom'
            });
          }
        })
        this.axios.get('/label/admincount?uid='+that.uid).then(function (res) {
          console.log(res.data.data)
          that.count = res.data.data
        })
        this.axios.get('/label/accuracy?uid='+that.uid).then(function (res) {
          console.log(res.data.data)
          that.accuracy = res.data.data
        })
      },

    }

</script>

<style scoped>
  .shuru{
    width: 80%;
    height: 60px;
    margin-bottom: 20px;
    font-size: 16px;
    padding: 5px 15px;
    margin-left:auto;
    margin-right:auto;
  }
  .button{
    width: 80%;
    margin-left:auto;
    margin-right:auto;
  }
  header{
    width: 100%;
    height: 40px;
    background: cornflowerblue;
    text-align: center;
    position: fixed;
    top: 0;
    line-height: 40px;
    color: #fff;
  }
  .btn{
    display: flex;
    width: 90%;
    margin: 0 auto;
  }
  .btn button{
    width: 40%;
  }
</style>
