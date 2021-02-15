import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import UserFiles from '../views/UserFiles'
import AfterLogout from '../views/AfterLogout'
import PageNotFound from '../views/PageNotFound'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/files',
    name: 'UserFiles',
    component: UserFiles
  },
  {
    path: '/afterLogout',
    name: 'AfterLogout',
    component: AfterLogout
  },
  {
    path: '*',
    name: 'PageNotFound',
    component: PageNotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
