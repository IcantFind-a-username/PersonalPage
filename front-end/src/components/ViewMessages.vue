<template>
  <div class="messages-section">
    <div class="content-container">
      <h2 class="section-title">查看留言</h2>
      
      <div class="search-bar">
        <input 
          type="text" 
          v-model="searchQuery" 
          placeholder="搜索留言..."
          @input="handleSearch"
        >
        <select v-model="sortBy" @change="handleSort">
          <option value="newest">最新</option>
          <option value="oldest">最早</option>
        </select>
      </div>

      <div class="messages-grid">
        <div v-for="message in displayedMessages" 
             :key="message.id" 
             class="message-card"
             :class="{ 'fade-in': true }">
          <div class="message-header">
            <h3>{{ message.title }}</h3>
            <span class="message-date">{{ formatDate(message.timestamp) }}</span>
          </div>
          <p class="message-content">{{ message.content }}</p>
          <div class="message-footer">
            <span class="message-author">{{ message.author || '匿名用户' }}</span>
          </div>
        </div>
      </div>

      <div class="pagination">
        <button 
          @click="prevPage" 
          :disabled="currentPage === 1"
          class="page-btn"
        >
          上一页
        </button>
        <span class="page-info">{{ currentPage }} / {{ totalPages }}</span>
        <button 
          @click="nextPage" 
          :disabled="currentPage === totalPages"
          class="page-btn"
        >
          下一页
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'ViewMessages',
  setup() {
    const store = useStore()
    const searchQuery = ref('')
    const sortBy = ref('newest')
    const currentPage = ref(1)
    const itemsPerPage = 6

    // 模拟数据
    const messages = ref([
      {
        id: 1,
        title: '第一条留言',
        content: '这是一个测试留言内容...',
        author: 'User1',
        timestamp: '2024-01-01T10:00:00'
      },
      // ... 添加更多测试数据
    ])

    const filteredMessages = computed(() => {
      let result = messages.value

      // 搜索过滤
      if (searchQuery.value) {
        const query = searchQuery.value.toLowerCase()
        result = result.filter(msg => 
          msg.title.toLowerCase().includes(query) ||
          msg.content.toLowerCase().includes(query)
        )
      }

      // 排序
      result = [...result].sort((a, b) => {
        const dateA = new Date(a.timestamp)
        const dateB = new Date(b.timestamp)
        return sortBy.value === 'newest' ? 
          dateB - dateA : dateA - dateB
      })

      return result
    })

    const totalPages = computed(() => 
      Math.ceil(filteredMessages.value.length / itemsPerPage)
    )

    const displayedMessages = computed(() => {
      const start = (currentPage.value - 1) * itemsPerPage
      const end = start + itemsPerPage
      return filteredMessages.value.slice(start, end)
    })

    const handleSearch = () => {
      currentPage.value = 1
    }

    const handleSort = () => {
      currentPage.value = 1
    }

    const prevPage = () => {
      if (currentPage.value > 1) {
        currentPage.value--
      }
    }

    const nextPage = () => {
      if (currentPage.value < totalPages.value) {
        currentPage.value++
      }
    }

    const formatDate = (date) => {
      return new Date(date).toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
      })
    }

    onMounted(async () => {
      // 这里可以添加实际的API调用来获取留言数据
      // const response = await store.dispatch('fetchMessages')
      // messages.value = response
    })

    return {
      searchQuery,
      sortBy,
      currentPage,
      totalPages,
      displayedMessages,
      handleSearch,
      handleSort,
      prevPage,
      nextPage,
      formatDate
    }
  }
}
</script>

<style scoped>
.messages-section {
  min-height: 100vh;
  padding: 80px 20px;
  background: linear-gradient(to bottom, #000000, #1a1a1a);
}

.content-container {
  max-width: 1200px;
  margin: 0 auto;
}

.search-bar {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
}

.search-bar input,
.search-bar select {
  padding: 0.8rem;
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  color: var(--text-color);
}

.search-bar input {
  flex: 1;
}

.messages-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
  margin-bottom: 2rem;
}

.message-card {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 15px;
  padding: 1.5rem;
  transition: transform 0.3s ease;
}

.message-card:hover {
  transform: translateY(-5px);
}

.message-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 1rem;
}

.message-date {
  font-size: 0.9rem;
  color: var(--secondary-color);
}

.message-content {
  margin-bottom: 1rem;
  line-height: 1.6;
}

.message-footer {
  color: var(--secondary-color);
  font-size: 0.9rem;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  margin-top: 2rem;
}

.page-btn {
  padding: 0.5rem 1rem;
  background: var(--accent-color);
  border: none;
  border-radius: 8px;
  color: white;
  cursor: pointer;
  transition: all 0.3s ease;
}

.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.page-info {
  color: var(--secondary-color);
}

.fade-in {
  animation: fadeIn 0.5s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>