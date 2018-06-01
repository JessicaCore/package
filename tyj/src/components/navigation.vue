<template>
  <div class="listBox" @click="handleClick">
    <img src="../assets/label.jpeg" height="150" width=90%  />
    <!--<span class="word">我是图片类啦啦啦</span>-->

  </div>
</template>

<script>
  export default {
    name: "navigation",
    data(){
      return{
      }
    },
    methods:{
      handleClick:function () {
        console.log(1)
        var that = this;
        that.uid = that.$route.params.id;
        that.$router.push({path:`/label/${that.uid}`})
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
