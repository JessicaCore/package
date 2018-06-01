<template>
  <div>
    <header>
      <span @click="goback">
        <i class="fa fa-angle-left" aria-hidden="true"></i> 返回
      </span>
    </header>
    <div class="label">
    <span>数量：{{count}}</span>
    <span >得分：{{score}}</span>
    </div>
    <img :src='src' height="70" width=90%/>
    <textarea name="" id="" cols="30" rows="10" v-model="introduction" class="shuru" placeholder="内容" ></textarea>
    <div class="btn">
    <mt-button type="primary" @click.native="handleClick" size="large" class="button">跳过</mt-button>

    <mt-button type="primary" @click.native="handleClick1" size="large"  class="button">提交</mt-button>
    </div>
  </br>
    <mt-button type="primary" @click.native="handleClick2" size="large" class="button">今日任务已完成</mt-button>

  </div>
</template>

<script>
    export default {
      name: "Label",
      data() {
        return {
          introduction: '',
          count: '',
          src:'',
          id:'',
          content:'',
          score:''
        }
      },
      methods: {
        handleClick: function () {
          var that = this;
          this.axios.get('/label/getRandom?uid='+that.uid).then(function (res) {
            if (res.data.success){
              console.log(res)
              that.src = res.data.data.image
              that.id = res.data.data.id
              that.introduction = res.data.data.init
            }else{
              that.$router.push({path:'/'})
              that.$toast({
                message: '图片不足请联系管理员',
                iconClass: 'icon icon-success',
                position: 'bottom'
              });
            }
          })
        },
        handleClick2:function(){
          //that.$router.push({path:'/zhengquelv'})
          var  that = this
          that.$messagebox({
            title: '提示',
            message: '确定执行此操作?',
            showCancelButton: true
          }).then(action => {
            if (action == 'confirm') {
              //that.$router.push({path:'/zhengquelv'})
              that.axios.get('/label/zhengquelv?uid='+that.uid).then(function (res) {
                console.log(res)
                that.$messagebox.alert(`正确率：${res.data.data} %`).then(action=>{
                  if (action == 'confirm'){
                    that.$router.push({path:`/navigation/${that.uid}`})

                  }
                })
              })
            }
          });
        },
        handleClick1: function () {
          var that = this;
          console.log(that.introduction)
          this.axios.post('/label/save?uid='+that.uid, {
            id: that.id,
            content: that.introduction,

          }).then(function (res) {
            console.log(res)
            if (res.data.success){
              that.$toast({
                message: `得分：+${res.data.data.jifen}`,
                iconClass: 'icon icon-success'
              });
              that.axios.get('/label/getRandom?uid='+that.uid).then(function (res) {
                if (res.data.success){
                  that.src = res.data.data.image
                  that.id = res.data.data.id
                  that.introduction = res.data.data.init
                }else{
                  that.$router.push({path:'/'})
                  that.$toast({
                    message: '图片不足请联系管理员',
                    iconClass: 'icon icon-success',
                    position: 'bottom'
                  });
                }
              })
              that.introduction = ''
              that.axios.get('/label/count?uid='+that.uid).then(function (res) {
                console.log(res.data.data)
                that.count = res.data.data
              })
              that.axios.get('/account/myScore?uid='+that.uid).then(function (res) {
                console.log(res.data.data)
                that.score = res.data.data
              })
            }
          })
        },
        goback:function () {
          this.$router.push({path:`/navigation/${this.uid}`})
        }
      },
      created:function () {
        var that = this;
        that.uid = that.$route.params.id;
        this.axios.get('/label/getRandom?uid='+that.uid).then(function (res) {
          if (res.data.success){
            that.src = res.data.data.image
            that.id = res.data.data.id
            that.introduction = res.data.data.init
          }else{
            that.$router.push({path:'/'})
            that.$toast({
              message: '图片不足请联系管理员',
              iconClass: 'icon icon-success',
              position: 'bottom'
            });
          }
        })
        this.axios.get('/label/count?uid='+that.uid).then(function (res) {
          console.log(res.data.data)
          that.count = res.data.data
        })
        this.axios.get('/account/myScore?uid='+that.uid).then(function (res) {
          console.log(res.data.data)
          that.score = res.data.data
        })
      },
    }
</script>

<style scoped>
  .shuru{
    width: 80%;
    height: 50px;
    margin-bottom: 20px;
    font-size: 24px;
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
    text-align: left;
    padding-left: 20px;
  }
  .label{
    display: flex;
    width: 80%;
    padding-left: 20px;
    justify-content: space-between;
    margin-bottom: 10px;
    margin-top: -10px;
    font-size: 16px;
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
