<template>
  <div>
    <mt-header title="用户列表" fixed>
      <router-link to="/adminnavigation" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>
    <ul>
      <li v-for="item in userlist" @click="todetail(item.uid,item.name)">
        <!--<mt-button type="primary" ">{{item.name}}</mt-button>-->
        <span class="icon">
          <i class="fa fa-user" aria-hidden="true"></i>
        </span>
        <span class="name">
          {{item.name}}
        </span>
        <span class="count">
          数量：{{item.count}}
        </span>
        <span class="icon1">
          <i class="fa fa-angle-right" aria-hidden="true"></i>
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
    export default {
        name: "userlist",
      data() {
        return {
          userlist:[]
        };
      },
      methods:{
        todetail:function (uid,name) {
          console.log(uid)
          this.$router.push({path:`/admin/${uid}/${name}`})
        }
      },
      created(){
        var that = this;
        this.axios.get('/account/users/').then(function (res) {
          that.userlist = res.data.data;
          console.log(res.data.data)

        })
      }
    }
</script>

<style scoped>
  ul{
    list-style: none;
    padding: 0;
    width: 100%;
    margin: 0 auto;
  }
  li{
    margin-top: 10px;
    text-align: left;
    padding-left: 20px;
    border-bottom: 1px solid #ccc;
    padding-bottom: 10px;
    position: relative;
  }
  .icon{
    display: inline-block;
    width: 30px;
    color: cornflowerblue;
    font-size: 18px;
    font-weight: bold;
  }
  .icon1{
    position: absolute;
    right: 20px;
  }
  .count{
    margin-left: 20px;
  }
  .name{
    display: inline-block;
    width: 100px;
  }
</style>
