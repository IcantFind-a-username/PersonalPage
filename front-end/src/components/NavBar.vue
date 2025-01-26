<template>
  <nav class="nav-bar" :class="{ scrolled: isScrolled }">
    <router-link to="/" class="logo">Franz</router-link>
    <ul class="nav-menu">
      <li><router-link to="/about">关于我</router-link></li>
      <li><router-link to="/message-board">留言板</router-link></li>
      <li><router-link to="/view-messages">查看留言</router-link></li>
      <li><router-link to="/settings">设置</router-link></li>
    </ul>
    <div class="user-menu">
      <button class="btn-login" @click="openLoginModal">登录</button>
      <button class="btn-register" @click="openRegisterModal">注册</button>
    </div>
  </nav>
</template>

<script>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

export default {
  name: 'NavBar',
  setup() {
    const router = useRouter()
    const isScrolled = ref(false)
    
    const handleScroll = () => {
      isScrolled.value = window.scrollY > 50
    }
    
    onMounted(() => {
      window.addEventListener('scroll', handleScroll)
    })
    
    onUnmounted(() => {
      window.removeEventListener('scroll', handleScroll)
    })
    
    return {
      isScrolled,
      openLoginModal: () => router.push('/login'),
      openRegisterModal: () => router.push('/register')
    }
  }
}
</script>

<style scoped>
.nav-bar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  height: 70px;
  display: flex;
  align-items: center;
  padding: 0 5rem;
  background: rgba(0, 0, 0, 0.8);
  backdrop-filter: saturate(180%) blur(20px);
  z-index: 1000;
  transition: all 0.3s ease;
}

.logo {
  font-size: 1.5rem;
  font-weight: 600;
  color: var(--primary-color);
  text-decoration: none;
  min-width: 100px;
}

.nav-menu {
  display: flex;
  justify-content: space-evenly;
  flex: 1;
  list-style: none;
  margin: 0 200px;
  padding: 0;
}

.nav-menu li {
  padding: 0 20px;
}

.nav-menu a {
  color: var(--primary-color);
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  position: relative;
  padding: 8px 0;
  white-space: nowrap;
}

.nav-menu a::after {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 0;
  height: 2px;
  background: var(--accent-color);
  transition: width 0.3s ease;
}

.nav-menu a:hover::after {
  width: 100%;
}

.user-menu {
  display: flex;
  gap: 1rem;
  min-width: 100px;
}

.btn-login, .btn-register {
  padding: 8px 20px;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-login {
  background: transparent;
  color: #ffffff;
  border: 1px solid rgba(255, 255, 255, 0.5);
}

.btn-login:hover {
  border-color: #ffffff;
  background: rgba(255, 255, 255, 0.1);
}

.btn-register {
  background: #ffffff;
  color: #000000;
  border: none;
}

.btn-register:hover {
  transform: translateY(-1px);
  background: rgba(255, 255, 255, 0.9);
}

.nav-bar.scrolled {
  background: rgba(0, 0, 0, 0.95);
}
</style>