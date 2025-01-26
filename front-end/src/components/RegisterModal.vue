<template>
  <div class="modal" @click.self="$emit('close')">
    <div class="modal-content">
      <button class="modal-close" @click="$emit('close')">&times;</button>
      <h2>注册</h2>
      <form class="auth-form" @submit.prevent="handleRegister">
        <input type="text" v-model="username" placeholder="用户名" required>
        <input type="email" v-model="email" placeholder="邮箱" required>
        <input type="password" v-model="password" placeholder="密码" required>
        <button type="submit">注册</button>
      </form>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'RegisterModal',
  emits: ['close'],
  setup() {
    const username = ref('')
    const email = ref('')
    const password = ref('')

    const handleRegister = () => {
      console.log('注册:', username.value, email.value, password.value)
    }

    return {
      username,
      email,
      password,
      handleRegister
    }
  }
}
</script>

<style scoped>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: #1d1d1f;
  padding: 2rem;
  border-radius: 15px;
  width: 90%;
  max-width: 400px;
  position: relative;
}

.modal-close {
  position: absolute;
  right: 1rem;
  top: 1rem;
  background: none;
  border: none;
  font-size: 1.5rem;
  color: var(--secondary-color);
  cursor: pointer;
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-top: 1rem;
}

.auth-form input {
  padding: 0.8rem;
  border-radius: 8px;
  border: 1px solid rgba(255, 255, 255, 0.1);
  background: rgba(255, 255, 255, 0.05);
  color: var(--text-color);
}

.auth-form button {
  padding: 0.8rem;
  border-radius: 8px;
  border: none;
  background: var(--accent-color);
  color: white;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.auth-form button:hover {
  background: #45a7ff;
}
</style> 