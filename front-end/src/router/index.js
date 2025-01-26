import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import AboutMe from '../components/AboutMe.vue'
import MessageBoard from '../components/MessageBoard.vue'
import ViewMessages from '../components/ViewMessages.vue'
import Settings from '../components/Settings.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/about',
    name: 'About',
    component: AboutMe
  },
  {
    path: '/message-board',
    name: 'MessageBoard',
    component: MessageBoard
  },
  {
    path: '/view-messages',
    name: 'ViewMessages',
    component: ViewMessages
  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router