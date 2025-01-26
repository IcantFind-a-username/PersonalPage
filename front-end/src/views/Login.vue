<template>
  <div class="auth-page">
    <div class="auth-container">
      <h2>登录</h2>
      <form class="auth-form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="email">邮箱</label>
          <input 
            type="email" 
            id="email"
            v-model="email" 
            placeholder="请输入邮箱"
            required
          >
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input 
            type="password" 
            id="password"
            v-model="password" 
            placeholder="请输入密码"
            required
          >
        </div>
        <div class="form-options">
          <label class="remember-me">
            <input type="checkbox" v-model="rememberMe">
            记住我
          </label>
          <a href="#" class="forgot-password">忘记密码？</a>
        </div>
        <button type="submit" class="auth-button">登录</button>
        <div class="auth-links">
          <p>还没有账号？ 
            <router-link to="/register" class="register-link">立即注册</router-link>
          </p>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default {
  name: 'Login',
  setup() {
    const router = useRouter()
    const store = useStore()
    const email = ref('')
    const password = ref('')
    const rememberMe = ref(false)

    const handleLogin = async () => {
      try {
        // 这里添加实际的登录逻辑
        console.log('登录信息:', {
          email: email.value,
          password: password.value,
          rememberMe: rememberMe.value
        })
        // 登录成功后跳转到首页
        router.push('/')
      } catch (error) {
        console.error('登录失败:', error)
      }
    }

    return {
      email,
      password,
      rememberMe,
      handleLogin
    }
  }
}
</script>

<style scoped>
.auth-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2rem;
  background: linear-gradient(to bottom, #000000, #1a1a1a);
}

.auth-container {
  width: 100%;
  max-width: 400px;
  padding: 2rem;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 15px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.auth-container h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: var(--primary-color);
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.form-group label {
  color: var(--primary-color);
  font-size: 0.9rem;
}

.form-group input {
  padding: 0.8rem;
  border-radius: 8px;
  border: 1px solid rgba(255, 255, 255, 0.1);
  background: rgba(255, 255, 255, 0.05);
  color: var(--text-color);
  transition: all 0.3s ease;
}

.form-group input:focus {
  outline: none;
  border-color: var(--accent-color);
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 0.9rem;
}

.remember-me {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  color: var(--secondary-color);
}

.forgot-password {
  color: var(--accent-color);
  text-decoration: none;
}

.auth-button {
  padding: 0.8rem;
  border-radius: 8px;
  border: none;
  background: var(--accent-color);
  color: white;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.auth-button:hover {
  background: #45a7ff;
  transform: translateY(-1px);
}

.auth-links {
  text-align: center;
  color: var(--secondary-color);
}

.register-link {
  color: var(--accent-color);
  text-decoration: none;
  font-weight: 500;
}

.register-link:hover {
  text-decoration: underline;
}
</style> 