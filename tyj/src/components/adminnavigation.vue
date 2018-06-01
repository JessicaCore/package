<template>
<div class="listBox" @click="handleClick">

  <!--<mt-button icon="more" slot="right"></mt-button>-->
  <img src="../assets/label.jpeg" height="200" width=90% class="img" />
  <span class="word">进度:</span>
  <mt-progress :value="wancheng" :bar-height="5" class="word"></mt-progress>

</div>
</template>

<script>
    export default {
        name: "adminnavigation",
      data(){
      return{
        wancheng: 1
        }
    },
      created (){
        var that=this;
        that.axios.get('/label/wancheng').then(function (res) {

          that.wancheng = res.data.data
        })

      },
      methods:{
           handleClick:function () {
             var that = this;
             that.$router.push({path:`/userlist`})

           }
      }
    }

</script>

<style scoped>
  .listBox{
    width: 90%;
    margin-top: 5px;
    display: flex;
    justify-content: space-around;
    padding-bottom: 5px;
    border-bottom: 1px solid #ccc;
    margin-left:auto;
    margin-right:auto;
  }
  .img{
    width: 40%;
    position: relative;
    height: 80px;
  }
  .img image{
    width: 100%;
    height: 50px;
  }
  .word{
    width: 50%;
    display: flex;
    flex-direction: column;
    margin-top: 25px;
    font-size: 20px;
  }



</style>
