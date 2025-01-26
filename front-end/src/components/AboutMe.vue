<template>
  <div class="about-section">
    <div class="content-container">
      <h2 class="section-title" :class="{ visible: isVisible }">关于我</h2>
      <div class="profile-card" :class="{ visible: isVisible }">
        <div class="profile-header">
          <img src="https://avatars.githubusercontent.com/u/random" alt="头像" class="profile-avatar">
          <div class="profile-info">
            <h3>Franz</h3>
            <p class="title">全栈开发工程师</p>
          </div>
        </div>
        <div class="skills-section">
          <h4>技术栈</h4>
          <div class="skills-grid">
            <div v-for="(skill, index) in skills" :key="index" class="skill-item">
              <i :class="skill.icon"></i>
              <span>{{ skill.name }}</span>
              <div class="skill-level" :style="{ width: skill.level + '%' }"></div>
            </div>
          </div>
        </div>
        <div class="bio-section">
          <h4>个人简介</h4>
          <p>{{ bio }}</p>
        </div>
        <div class="contact-section">
          <a href="https://github.com" target="_blank"><i class="fab fa-github"></i></a>
          <a href="mailto:example@email.com"><i class="fas fa-envelope"></i></a>
          <a href="https://linkedin.com" target="_blank"><i class="fab fa-linkedin"></i></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'

export default {
  name: 'AboutMe',
  setup() {
    const isVisible = ref(false)
    const skills = [
      { name: 'Vue.js', level: 90, icon: 'fab fa-vuejs' },
      { name: 'React', level: 85, icon: 'fab fa-react' },
      { name: 'Node.js', level: 80, icon: 'fab fa-node-js' },
      { name: 'Python', level: 75, icon: 'fab fa-python' },
      { name: 'JavaScript', level: 90, icon: 'fab fa-js' },
      { name: 'HTML/CSS', level: 85, icon: 'fab fa-html5' }
    ]

    const bio = `作为一名充满热情的全栈开发工程师，我专注于创建高质量的Web应用。
    拥有4年的开发经验，精通前端框架Vue.js和React，以及后端技术Node.js和Python。
    热爱学习新技术，追求代码质量和用户体验的完美统一。
    目前正在研究人工智能和机器学习领域，期待能将这些技术应用到实际项目中。`

    onMounted(() => {
      setTimeout(() => {
        isVisible.value = true
      }, 500)
    })

    return {
      isVisible,
      skills,
      bio
    }
  }
}
</script>

<style scoped>
.about-section {
  min-height: 100vh;
  padding: 80px 20px;
  background: linear-gradient(to bottom, #000000, #1a1a1a);
}

.content-container {
  max-width: 1000px;
  margin: 0 auto;
}

.section-title {
  font-size: 2.5rem;
  margin-bottom: 2rem;
  opacity: 0;
  transform: translateY(30px);
  transition: all 0.8s ease;
}

.section-title.visible {
  opacity: 1;
  transform: translateY(0);
}

.profile-card {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  padding: 2rem;
  opacity: 0;
  transform: translateY(30px);
  transition: all 0.8s ease;
}

.profile-card.visible {
  opacity: 1;
  transform: translateY(0);
}

.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
}

.profile-avatar {
  width: 120px;
  height: 120px;
  border-radius: 60px;
  margin-right: 2rem;
}

.profile-info h3 {
  font-size: 1.8rem;
  margin-bottom: 0.5rem;
}

.title {
  color: var(--accent-color);
}

.skills-section {
  margin: 2rem 0;
}

.skills-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1rem;
}

.skill-item {
  position: relative;
  padding: 1rem;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 10px;
}

.skill-item i {
  margin-right: 0.5rem;
  color: var(--accent-color);
}

.skill-level {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 3px;
  background: var(--accent-color);
  transition: width 1s ease;
}

.bio-section {
  margin: 2rem 0;
  line-height: 1.6;
}

.contact-section {
  display: flex;
  gap: 1.5rem;
  justify-content: center;
  margin-top: 2rem;
}

.contact-section a {
  color: var(--text-color);
  font-size: 1.5rem;
  transition: color 0.3s ease;
}

.contact-section a:hover {
  color: var(--accent-color);
}
</style>