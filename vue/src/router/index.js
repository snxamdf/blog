import Vue from 'vue'
import Router from 'vue-router'
import admin from './admin/admin'
import web from './web/web'

Vue.use(Router)

const routes = []
const baseRoutes = routes.concat(admin, web);

export default new Router({
  routes: baseRoutes
})
