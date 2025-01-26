<template>
  <div class="message-section">
    <div class="content-container">
      <h2 class="section-title">留言板</h2>
      <div class="message-form">
        <div class="form-group">
          <label>标题</label>
          <input 
            type="text" 
            v-model="messageTitle" 
            placeholder="请输入标题"
            required
          >
        </div>
        <div class="form-group">
          <label>内容</label>
          <textarea 
            v-model="messageContent" 
            placeholder="请输入留言内容..."
            required
          ></textarea>
        </div>
        <div class="form-footer">
          <span class="char-count" :class="{ 'near-limit': isNearLimit }">
            {{ remainingChars }} 字
          </span>
          <button @click="submitMessage" :disabled="!isValidMessage">
            发送留言
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'MessageBoard',
  setup() {
    const store = useStore()
    const messageTitle = ref('')
    const messageContent = ref('')
    const maxLength = 500

    const remainingChars = computed(() => 
      maxLength - messageContent.value.length
    )

    const isNearLimit = computed(() => 
      remainingChars.value < 50
    )

    const isValidMessage = computed(() => 
      messageTitle.value.trim() && 
      messageContent.value.trim() && 
      messageContent.value.length <= maxLength
    )

    const submitMessage = async () => {
      if (!isValidMessage.value) return

      try {
        await store.dispatch('addMessage', {
          title: messageTitle.value,
          content: messageContent.value,
          timestamp: new Date().toISOString()
        })
        messageTitle.value = ''
        messageContent.value = ''
        alert('留言发送成功！')
      } catch (error) {
        alert('留言发送失败，请稍后重试')
      }
    }

    return {
      messageTitle,
      messageContent,
      remainingChars,
      isNearLimit,
      isValidMessage,
      submitMessage
    }
  }
}
</script>

<style scoped>
.message-section {
  min-height: 100vh;
  padding: 80px 20px;
  background: linear-gradient(to bottom, #000000, #1a1a1a);
}

.content-container {
  max-width: 800px;
  margin: 0 auto;
}

.section-title {
  font-size: 2.5rem;
  margin-bottom: 2rem;
}

.message-form {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  padding: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  color: var(--text-color);
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.8rem;
  background: rgba(255, 255, 255, 0.05);
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  color: var(--text-color);
  transition: all 0.3s ease;
}

.form-group textarea {
  height: 200px;
  resize: vertical;
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: var(--accent-color);
}

.form-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.char-count {
  color: var(--secondary-color);
}

.char-count.near-limit {
  color: #ff6b6b;
}

button {
  padding: 0.8rem 2rem;
  background: var(--accent-color);
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
}

button:hover:not(:disabled) {
  background: #45a7ff;
  transform: translateY(-1px);
}

button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>