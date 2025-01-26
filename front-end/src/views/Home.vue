<template>
  <div class="home">
    <section class="hero" ref="hero">
      <div class="hero-content">
        <h1 class="hero-title" :class="{ visible: isVisible }">
          欢迎访问菜鸟程序员Franz的个人主页
        </h1>
        <p class="hero-subtitle" :class="{ visible: isVisible }">
          热爱编程 · 永远学习 · 追求进步
        </p>
      </div>
    </section>
    
    <section class="features">
      <div class="feature-card" v-for="(feature, index) in features" 
           :key="index" 
           :class="{ visible: isFeatureVisible }"
           :style="{ transitionDelay: `${index * 0.2}s` }">
        <i :class="feature.icon"></i>
        <h3>{{ feature.title }}</h3>
        <p>{{ feature.description }}</p>
      </div>
    </section>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'

export default {
  name: 'Home',
  setup() {
    const isVisible = ref(false)
    const isFeatureVisible = ref(false)
    
    const features = [
      {
        icon: 'fas fa-code',
        title: '技术栈',
        description: '前端 Vue/React，后端 Node.js/Python'
      },
      {
        icon: 'fas fa-project-diagram',
        title: '项目经验',
        description: '多个完整项目开发经验'
      },
      {
        icon: 'fas fa-graduation-cap',
        title: '持续学习',
        description: '保持学习，追求技术进步'
      }
    ]
    
    onMounted(() => {
      setTimeout(() => {
        isVisible.value = true
      }, 500)
      
      const observer = new IntersectionObserver(
        (entries) => {
          entries.forEach(entry => {
            if (entry.isIntersecting) {
              isFeatureVisible.value = true
            }
          })
        },
        { threshold: 0.3 }
      )
      
      const featuresSection = document.querySelector('.features')
      if (featuresSection) {
        observer.observe(featuresSection)
      }
    })
    
    return {
      isVisible,
      isFeatureVisible,
      features
    }
  }
}
</script>

<style scoped>
.home {
  min-height: 200vh;
}

.hero {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  background: linear-gradient(to bottom, #000000, #1a1a1a);
}

.hero-content {
  max-width: 1000px;
  padding: 0 2rem;
}

.hero-title {
  font-size: 56px;
  font-weight: 600;
  margin-bottom: 1.5rem;
  opacity: 0;
  transform: translateY(30px);
  transition: all 1s ease;
}

.hero-subtitle {
  font-size: 24px;
  color: var(--secondary-color);
  opacity: 0;
  transform: translateY(30px);
  transition: all 1s ease;
  transition-delay: 0.2s;
}

.hero-title.visible,
.hero-subtitle.visible {
  opacity: 1;
  transform: translateY(0);
}

.features {
  padding: 100px 0;
  display: flex;
  justify-content: center;
  gap: 2rem;
  background: #000000;
}

.feature-card {
  width: 300px;
  padding: 2rem;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 15px;
  text-align: center;
  backdrop-filter: blur(10px);
  opacity: 0;
  transform: translateY(30px);
  transition: all 0.8s ease;
}

.feature-card.visible {
  opacity: 1;
  transform: translateY(0);
}

.feature-card i {
  font-size: 2.5rem;
  color: var(--accent-color);
  margin-bottom: 1.5rem;
}

.feature-card h3 {
  font-size: 1.5rem;
  margin-bottom: 1rem;
}

.feature-card p {
  color: var(--secondary-color);
  line-height: 1.6;
}
</style>