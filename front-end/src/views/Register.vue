<template>
  <div class="auth-page">
    <div class="auth-container">
      <h2>注册</h2>
      <form class="auth-form" @submit.prevent="handleRegister">
        <div class="form-group">
          <label for="username">用户名</label>
          <input 
            type="text" 
            id="username"
            v-model="username" 
            placeholder="请输入用户名"
            required
          >
        </div>
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
        <div class="form-group">
          <label for="confirmPassword">确认密码</label>
          <input 
            type="password" 
            id="confirmPassword"
            v-model="confirmPassword" 
            placeholder="请再次输入密码"
            required
          >
        </div>
        <button type="submit" class="auth-button">注册</button>
        <div class="auth-links">
          <p>已有账号？ 
            <router-link to="/login" class="login-link">立即登录</router-link>
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
  name: 'Register',
  setup() {
    const router = useRouter()
    const store = useStore()
    const username = ref('')
    const email = ref('')
    const password = ref('')
    const confirmPassword = ref('')

    const handleRegister = async () => {
      if (password.value !== confirmPassword.value) {
        alert('两次输入的密码不一致')
        return
      }

      try {
        // 这里添加实际的注册逻辑
        console.log('注册信息:', {
          username: username.value,
          email: email.value,
          password: password.value
        })
        // 注册成功后跳转到登录页
        router.push('/login')
      } catch (error) {
        console.error('注册失败:', error)
      }
    }

    return {
      username,
      email,
      password,
      confirmPassword,
      handleRegister
    }
  }
}
</script>

<style scoped>
/* 使用与 Login.vue 相同的样式 */
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

.login-link {
  color: var(--accent-color);
  text-decoration: none;
  font-weight: 500;
}

.login-link:hover {
  text-decoration: underline;
}
</style> 