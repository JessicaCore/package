// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Mint from 'mint-ui';
import 'mint-ui/lib/style.css'
import axios from 'axios'
import 'font-awesome/css/font-awesome.css'


axios.defaults.baseURL = 'http://47.94.255.16:9999'

Vue.config.productionTip = false

/* eslint-disable no-new */
Vue.use(Mint);
Vue.prototype.axios = axios
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
