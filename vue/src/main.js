import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';

import {get,post} from './request/http'

//定义全局变量
Vue.prototype.$get = get;
//定义全局变量
Vue.prototype.$post = post;

Vue.use(ElementUI);
Vue.prototype.HOST = '/api'

new Vue({
  el: '#app',
  render: h => h(App)
});
