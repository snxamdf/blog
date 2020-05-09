import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router/index'

import {get, post} from './request/http'

//定义全局变量
Vue.prototype.$http = {
  get: get, post: post
}

Vue.use(ElementUI);
Vue.prototype.HOST = '/api'

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
