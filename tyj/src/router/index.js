import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/login'
import Label from '@/components/label'
import Register from '@/components/register'
import Admin from '@/components/admin'
import crypto from 'crypto'
import md5 from 'js-md5'
import Navigation from '@/components/navigation'
import adminNavigation from '@/components/adminnavigation'
import Userpage from'@/components/userpage'
import Userlist from'@/components/userlist'

Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },{
      path: '/login',
      name: 'Login',
      component: Login
    },{
      path: '/register',
      name: 'Register',
      component: Register
    },{
      path: '/label/:id',
      name: 'Label',
      component: Label
    },{
      path: '/admin/:id/:name',
      name: 'admin',
      component: Admin
    },{
      path:'/adminnavigation',
      name:'adminnavigation',
      component:adminNavigation
    },{
      path: '/navigation/:id',
      name: 'navigation',
      component: Navigation
    },{
      path: '/userlist',
      name: 'userlist',
      component: Userlist
    }
  ],
})
