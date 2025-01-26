<template>
  <div class="settings-section">
    <div class="content-container">
      <h2 class="section-title">{{ t('settings.title') }}</h2>
      
      <div class="settings-card">
        <div class="settings-group">
          <h3>{{ t('settings.language') }}</h3>
          <select v-model="currentLocale" @change="changeLanguage">
            <option value="zh">中文</option>
            <option value="en">English</option>
          </select>
        </div>

        <div class="settings-group">
          <h3>{{ t('settings.theme') }}</h3>
          <select v-model="theme">
            <option value="dark">{{ t('settings.darkTheme') }}</option>
            <option value="light">{{ t('settings.lightTheme') }}</option>
          </select>
        </div>

        <div class="settings-group">
          <h3>{{ t('settings.notifications') }}</h3>
          <label class="toggle">
            <input type="checkbox" v-model="notifications">
            <span class="slider"></span>
            {{ t('settings.enableNotifications') }}
          </label>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useStore } from 'vuex'

// 语言包
const messages = {
  zh: {
    settings: {
      title: '设置',
      language: '语言',
      theme: '主题',
      darkTheme: '深色主题',
      lightTheme: '浅色主题',
      notifications: '通知',
      enableNotifications: '启用通知'
    }
  },
  en: {
    settings: {
      title: 'Settings',
      language: 'Language',
      theme: 'Theme',
      darkTheme: 'Dark Theme',
      lightTheme: 'Light Theme',
      notifications: 'Notifications',
      enableNotifications: 'Enable Notifications'
    }
  }
}

export default {
  name: 'Settings',
  setup() {
    const store = useStore()
    const currentLocale = ref('zh')
    const theme = ref('dark')
    const notifications = ref(false)

    const t = (key) => {
      const keys = key.split('.')
      let result = messages[currentLocale.value]
      for (const k of keys) {
        result = result[k]
      }
      return result
    }

    const changeLanguage = () => {
      // 更新全局语言状态
      store.commit('setLocale', currentLocale.value)
      // 这里可以添加持久化存储
      localStorage.setItem('locale', currentLocale.value)
    }

    onMounted(() => {
      // 从本地存储加载设置
      const savedLocale = localStorage.getItem('locale')
      if (savedLocale) {
        currentLocale.value = savedLocale
      }
    })

    return {
      currentLocale,
      theme,
      notifications,
      t,
      changeLanguage
    }
  }
}
</script>

<style scoped>
.settings-section {
  min-height: 100vh;
  padding: 80px 20px;
  background: linear-gradient(to bottom, #000000, #1a1a1a);
}

.content-container {
  max-width: 800px;
  margin: 0 auto;
}

.settings-card {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  padding: 2rem;
}

.settings-group {
  margin-bottom: 2rem;
}

.settings-group h3 {
  margin-bottom: 1rem;
  color: var(--text-color);
}

select {
  width: 100%;
  padding: 0.8rem;
  background: rgba(255, 255, 255, 0.05);
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  color: var(--text-color);
  cursor: pointer;
}

.toggle {
  display: flex;
  align-items: center;
  gap: 1rem;
  cursor: pointer;
}

.slider {
  position: relative;
  width: 50px;
  height: 26px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 13px;
  transition: all 0.3s ease;
}

.slider:before {
  content: '';
  position: absolute;
  width: 20px;
  height: 20px;
  left: 3px;
  top: 3px;
  background: white;
  border-radius: 50%;
  transition: all 0.3s ease;
}

input:checked + .slider {
  background: var(--accent-color);
}

input:checked + .slider:before {
  transform: translateX(24px);
}

input[type="checkbox"] {
  display: none;
}
</style>